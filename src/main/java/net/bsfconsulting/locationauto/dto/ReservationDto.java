package net.bsfconsulting.locationauto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bsfconsulting.locationauto.enums.StatusCar;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservationDto {
    private Long id;
    private Long userId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatusCar statusCar;
    private Long totalAmont;
    private String userFullName;
    private Long carId;
    private String carModel;

}
