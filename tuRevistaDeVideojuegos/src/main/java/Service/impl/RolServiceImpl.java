// Service/impl/RolServiceImpl.java
package Service.impl;

import DTO.RolRequestDTO;
import DTO.RolResponseDTO;
import Model.Rol;
import Repository.RolRepository;
import Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public RolResponseDTO saveRol(RolRequestDTO dto) {
        Rol rol = new Rol();
        rol.setName(dto.getName());
        return toResponseDTO(rolRepository.save(rol));
    }

    @Override
    public List<RolResponseDTO> getAllRoles() {
        return rolRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RolResponseDTO> getRolById(Long id) {
        return rolRepository.findById(id).map(this::toResponseDTO);
    }

    @Override
    public RolResponseDTO updateRol(Long id, RolRequestDTO dto) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol not found"));
        rol.setName(dto.getName());
        return toResponseDTO(rolRepository.save(rol));
    }

    @Override
    public void deleteRol(Long id) {
        rolRepository.deleteById(id);
    }

    private RolResponseDTO toResponseDTO(Rol rol) {
        return new RolResponseDTO(rol.getId(), rol.getName());
    }
}
