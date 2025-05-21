package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service.impl;

import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO.UserRequestDTO;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO.UserResponseDTO;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.Rol;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.User;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Repository.RolRepository;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Repository.UserRepository;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class    UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RolRepository rolRepository) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public UserResponseDTO saveUser(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));

        if (dto.getRoleIds() != null) {
            Set<Rol> roles = new HashSet<>(rolRepository.findAllById(dto.getRoleIds()));
            user.setRols(roles);
        }

        return toResponseDTO(userRepository.save(user));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::toResponseDTO);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());
        if (dto.getRoleIds() != null) {
            Set<Rol> roles = new HashSet<>(rolRepository.findAllById(dto.getRoleIds()));
            user.setRols(roles);
        }
        return toResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponseDTO toResponseDTO(User user) {
        List<String> roleNames = user.getRols() != null
                ? user.getRols().stream().map(Rol::getName).collect(Collectors.toList())
                : Collections.emptyList();

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roleNames
        );
    }
}
