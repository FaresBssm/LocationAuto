package net.bsfconsulting.locationauto.service;


import net.bsfconsulting.locationauto.dto.ReservationDto;
import net.bsfconsulting.locationauto.entity.Car;
import net.bsfconsulting.locationauto.entity.Reservation;
import net.bsfconsulting.locationauto.enums.StatusCar;
import net.bsfconsulting.locationauto.mapper.ReservationMapper;
import net.bsfconsulting.locationauto.repository.CarRepository;
import net.bsfconsulting.locationauto.repository.ReservationRepository;
import net.bsfconsulting.locationauto.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, UserRepository userRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.reservationMapper = reservationMapper;
    }

    public ReservationDto createReservation(Reservation reservation) {
        if (reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new RuntimeException("La date de fin doit être après la date de début.");
        }
        LocalDate today = LocalDate.now();
        reservation.setDateDebut(today.plusDays(2));

        Car car = carRepository.findById(reservation.getCar().getId())
                .orElseThrow(() -> new RuntimeException("La voiture avec cet id n'existe pas : " + reservation.getCar().getId()));
        if (car.getStatusCar() != StatusCar.DISPONIBLE) {
            throw new RuntimeException("La voiture n'est pas disponible a la reservation");
        }
        boolean conflit = reservationRepository.existsByDateDebutBetweenAndDateFinBetween(
                reservation.getDateDebut(),
                reservation.getDateFin(),
                reservation.getCar().getId()
        );
        if (conflit) {
            throw new RuntimeException("La voiture est deja reserve pour cette periode");
        }
        long nombreOfDays = ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin());
        double totalAmont = nombreOfDays * car.getPricePerDay();
        reservation.setTotalAmont((long) totalAmont);

        Reservation saved = reservationRepository.save(reservation);
        saved.setUser(userRepository.findById(saved.getUser().getId()).orElse(null));
        saved.setCar(carRepository.findById(saved.getCar().getId()).orElse(null));
        return reservationMapper.reservationToReservationDto(saved);
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            reservation.setUser(userRepository.findById(reservation.getUser().getId()).orElse(null));
            reservation.setCar(carRepository.findById(reservation.getCar().getId()).orElse(null));
        }
        return reservations.stream()
                .map(reservationMapper::reservationToReservationDto)
                .toList();
    }

    public ReservationDto updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La reservation avec cet id n'existe pas : " + id));
        existingReservation.setDateDebut(reservation.getDateDebut());
        existingReservation.setDateFin(reservation.getDateFin());
        existingReservation.setTotalAmont(reservation.getTotalAmont());
        existingReservation.setCar(reservation.getCar());
        existingReservation.setUser(reservation.getUser());

        Reservation updatedReservation = reservationRepository.save(existingReservation);
        updatedReservation.setUser(userRepository.findById(updatedReservation.getUser().getId()).orElse(null));
        updatedReservation.setCar(carRepository.findById(updatedReservation.getCar().getId()).orElse(null));
        return reservationMapper.reservationToReservationDto(updatedReservation);
    }

    public List<ReservationDto> getReservationsByUserId(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        for (Reservation reservation : reservations) {
            reservation.setUser(userRepository.findById(reservation.getUser().getId()).orElse(null));
        }
        return reservations.stream()
                .map(reservationMapper::reservationToReservationDto)
                .toList();
    }
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La reservation avec cet id n'existe pas : " + id));
        reservationRepository.delete(reservation);
    }
}