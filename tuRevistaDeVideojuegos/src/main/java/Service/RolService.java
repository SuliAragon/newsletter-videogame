// Service/RolService.java
package Service;

import DTO.RolRequestDTO;
import DTO.RolResponseDTO;

import java.util.List;
import java.util.Optional;

public interface RolService {
    RolResponseDTO saveRol(RolRequestDTO dto);
    List<RolResponseDTO> getAllRoles();
    Optional<RolResponseDTO> getRolById(Long id);
    RolResponseDTO updateRol(Long id, RolRequestDTO dto);
    void deleteRol(Long id);
}
