package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service;

import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO.UserRequestDTO;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponseDTO saveUser(UserRequestDTO userDTO);

    List<UserResponseDTO> getAllUsers();

    Optional<UserResponseDTO> getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserRequestDTO userDTO);

    void deleteUser(Long id);
}
