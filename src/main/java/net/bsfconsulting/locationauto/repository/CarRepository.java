package net.bsfconsulting.locationauto.repository;

import net.bsfconsulting.locationauto.entity.Car;
import net.bsfconsulting.locationauto.enums.StatusCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("""
            SELECT c FROM Car c
            WHERE (:brand IS NULL OR c.brand ILIKE %:brand%)
            AND (:model IS NULL OR c.model ILIKE %:model%)
            AND (:registrationNumber IS NULL OR c.registrationNumber ILIKE %:registrationNumber%)
            AND (:type IS NULL OR c.type = :type)
            AND (:pricePerDay IS NULL OR c.pricePerDay <= :pricePerDay)
            AND (:statusCar IS NULL OR c.statusCar = :statusCar)
            """)
    List<Car> searchCarByFiltres( String brand, String model, String registrationNumber, String type, Double pricePerDay, StatusCar statusCar);
}
