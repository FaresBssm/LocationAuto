package net.bsfconsulting.locationauto.service;


import net.bsfconsulting.locationauto.dto.UserDto;
import net.bsfconsulting.locationauto.entity.User;
import net.bsfconsulting.locationauto.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .dateOfBirth(user.getDateOfBirth())
                        .phoneNumber(user.getPhoneNumber())
                        .reservation(user.getReservation())
                        .build())
                .toList();
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User update(Long id ,User userDetails) {
        User existtingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouver avec l'id: " + id));
        existtingUser.setFirstName(userDetails.getFirstName());
        existtingUser.setLastName(userDetails.getLastName());
        existtingUser.setEmail(userDetails.getEmail());
        existtingUser.setDateOfBirth(userDetails.getDateOfBirth());
        existtingUser.setPhoneNumber(userDetails.getPhoneNumber());
        return userRepository.save(existtingUser);
    }

    public UserDto getUserById(long id) {
        return userRepository.findById(id)
                .map(user -> UserDto .builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .dateOfBirth(user.getDateOfBirth())
                        .phoneNumber(user.getPhoneNumber())
                        .reservation(user.getReservation())
                        .build()
                )
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouver avec l'id: " + id));
    }
}
