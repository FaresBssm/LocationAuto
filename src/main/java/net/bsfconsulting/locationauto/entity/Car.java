package net.bsfconsulting.locationauto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bsfconsulting.locationauto.enums.StatusCar;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String registrationNumber;
    private String type;
    private Double pricePerDay;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCar statusCar;
    @OneToMany(mappedBy = "car")
    private List<Reservation> reservationList;
}
