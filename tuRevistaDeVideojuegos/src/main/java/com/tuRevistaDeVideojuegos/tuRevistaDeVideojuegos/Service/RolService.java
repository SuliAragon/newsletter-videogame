// com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service.impl.impl.Service/RolService.java
package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service;

import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO.RolRequestDTO;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO.RolResponseDTO;

import java.util.List;
import java.util.Optional;

public interface RolService {
    RolResponseDTO saveRol(RolRequestDTO dto);
    List<RolResponseDTO> getAllRoles();
    Optional<RolResponseDTO> getRolById(Long id);
    RolResponseDTO updateRol(Long id, RolRequestDTO dto);
    void deleteRol(Long id);
}
