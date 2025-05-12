package net.bsfconsulting.locationauto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String registrationNumber;
    private String type;
    private Double pricePerDay;
    private String status;
}
