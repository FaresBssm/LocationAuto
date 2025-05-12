package net.bsfconsulting.locationauto.resource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.bsfconsulting.locationauto.dto.ReservationDto;
import net.bsfconsulting.locationauto.entity.Reservation;
import net.bsfconsulting.locationauto.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/reservations")
@Tag(name = "Gestion des réservations", description = "Opérations liées aux réservations de véhicules")
public class ReservationResource {
    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @Operation(summary = "Créer une réservation", description = "Crée une nouvelle réservation pour un véhicule.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Réservation créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ReservationDto createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }
    @Operation(summary = "Lister toutes les réservations")
    @GetMapping
    public List getAllReservations() {
        return reservationService.getAllReservations();
    }
    @Operation(summary = "Mettre à jour une réservation", description = "Met à jour les données d'une réservation existante.")
    @PutMapping("/{id}")
    public ReservationDto updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @Operation(summary = "Récupérer les réservations d’un utilisateur")
    @GetMapping("/{userId}")
    public List<ReservationDto> getReservationsByUserId(@PathVariable Long userId) {
        return reservationService.getReservationsByUserId(userId);
    }
    @Operation(summary = "Supprimer une réservation", description = "Supprime une réservation existante à partir de son identifiant.")
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
