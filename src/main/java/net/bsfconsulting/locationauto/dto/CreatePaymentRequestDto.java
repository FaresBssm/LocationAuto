package net.bsfconsulting.locationauto.dto;

import lombok.Data;

@Data
public class CreatePaymentRequestDto {
    private Long reservationId;
    private String paymentMethod;
}
