package net.bsfconsulting.locationauto.resource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.bsfconsulting.locationauto.dto.UserDto;
import net.bsfconsulting.locationauto.entity.User;
import net.bsfconsulting.locationauto.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/api/v1/users")
@Tag(name = "Gestion des utilisateurs", description = "Opérations CRUD sur les utilisateurs")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Ajouter un utilisateur", description = "Crée un nouvel utilisateur dans le système.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    @Operation(summary = "Lister tous les utilisateurs")
    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }
    @Operation(summary = "Obtenir un utilisateur par ID")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }
    @Operation(summary = "Mettre à jour un utilisateur")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
    @Operation(summary = "Supprimer un utilisateur")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
