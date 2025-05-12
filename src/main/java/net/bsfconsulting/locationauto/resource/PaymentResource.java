package net.bsfconsulting.locationauto.resource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.bsfconsulting.locationauto.dto.CreatePaymentRequestDto;
import net.bsfconsulting.locationauto.dto.PaymentDto;
import net.bsfconsulting.locationauto.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/payments")
public class PaymentResource {
    private final PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @Operation(summary = "Créer un paiement", description = "Crée un nouveau paiement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paiement créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody CreatePaymentRequestDto request) {
        PaymentDto payment = paymentService.createPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
}
