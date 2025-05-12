package net.bsfconsulting.locationauto.service;


import net.bsfconsulting.locationauto.entity.Car;
import net.bsfconsulting.locationauto.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car car) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La voiture avec cet id n'existe pas : " + id));
        existingCar.setBrand(car.getBrand());
        existingCar.setModel(car.getModel());
        existingCar.setRegistrationNumber(car.getRegistrationNumber());
        existingCar.setType(car.getType());
        existingCar.setPricePerDay(car.getPricePerDay());
        existingCar.setStatusCar(car.getStatusCar());
        existingCar.setReservationList(car.getReservationList());
        return carRepository.save(existingCar);
    }

    public Car updateStatus(Long id, Car car) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La voiture avec cet id n'existe pas : " + id));
        existingCar.setStatusCar(car.getStatusCar());
        return carRepository.save(existingCar);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> searchCarsByFiltres(Car car) {
        return carRepository.searchCarByFiltres(
                car.getBrand(),
                car.getModel(),
                car.getRegistrationNumber(),
                car.getType(),
                car.getPricePerDay(),
                car.getStatusCar()
        );
    }

    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La voiture avec cet id n'existe pas : " + id));
        carRepository.delete(car);
    }
}
