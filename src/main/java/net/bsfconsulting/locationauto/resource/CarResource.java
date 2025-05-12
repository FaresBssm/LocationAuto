package net.bsfconsulting.locationauto.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import net.bsfconsulting.locationauto.entity.Car;
import net.bsfconsulting.locationauto.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/cars")
public class CarResource {
    private final CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Ajouter une voiture", description = "Ajoute une nouvelle voiture à la base de données")
    @ApiResponse(responseCode = "200", description = "Voiture ajoutée avec succès")
    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }
    @Operation(summary = "Modifier une voiture", description = "Met à jour les informations d'une voiture existante")
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @Operation(summary = "Modifier le statut d'une voiture", description = "Met à jour uniquement le statut de la voiture")
    @PatchMapping("/{id}")
    public Car updateStatus(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateStatus(id, car);
    }

    @Operation(summary = "Lister toutes les voitures", description = "Retourne la liste complète des voitures disponibles")
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @Operation(summary = "Rechercher des voitures", description = "Filtre les voitures selon les critères fournis")
    @PostMapping("/search")
    public List<Car> searchCarsByFiltres(@RequestBody Car car) {
        return carService.searchCarsByFiltres(car);
    }
    @Operation(summary = "Supprimer une voiture", description = "Supprime une voiture de la base de données en fonction de son identifiant")
    @DeleteMapping("/{id}")
    public void deleteCaar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
