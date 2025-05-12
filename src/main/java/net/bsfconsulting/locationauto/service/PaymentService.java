package net.bsfconsulting.locationauto.service;

import net.bsfconsulting.locationauto.dto.CreatePaymentRequestDto;
import net.bsfconsulting.locationauto.dto.PaymentDto;
import net.bsfconsulting.locationauto.entity.Payment;
import net.bsfconsulting.locationauto.entity.Reservation;
import net.bsfconsulting.locationauto.enums.PaymentStatus;
import net.bsfconsulting.locationauto.enums.StatusCar;
import net.bsfconsulting.locationauto.repository.PaymentRepository;
import net.bsfconsulting.locationauto.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    public PaymentService(PaymentRepository paymentRepository, ReservationRepository reservationRepository) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
    }

    public PaymentDto createPayment(CreatePaymentRequestDto request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        if(reservation.getStatusCar()!= StatusCar.RESERVE){
            throw new RuntimeException("Reservation déja payéé ou invalide");
        }
        Long days = ChronoUnit.DAYS.between(
                reservation.getDateDebut(),reservation.getDateFin());
        Long amount = Math.round(days * reservation.getCar().getPricePerDay());
        Payment payment= Payment.builder()
                .reservation(reservation)
                .datePayment(LocalDate.now())
                .amount(amount)
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(PaymentStatus.REUSSI)
                .build();
        Payment saved = paymentRepository.save(payment);
        reservation.setStatusCar(StatusCar.PAYE);
        reservationRepository.save(reservation);
        return PaymentDto.builder()
                .id(saved.getId())
                .reservationId(saved.getReservation().getId())
                .datePayment(saved.getDatePayment())
                .amount(saved.getAmount())
                .paymentMethod(saved.getPaymentMethod())
                .paymentStatus(saved.getPaymentStatus())
                .build();
    }
}
