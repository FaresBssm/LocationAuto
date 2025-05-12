package net.bsfconsulting.locationauto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bsfconsulting.locationauto.enums.PaymentStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private Long id;
    private LocalDate datePayment;
    private Long amount;
    private String paymentMethod;
    private PaymentStatus paymentStatus;
    private Long reservationId;
}
