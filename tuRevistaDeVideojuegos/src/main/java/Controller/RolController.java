// Controller/RolController.java
package Controller;

import DTO.RolRequestDTO;
import DTO.RolResponseDTO;
import Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping
    public ResponseEntity<RolResponseDTO> createRol(@RequestBody RolRequestDTO dto) {
        return ResponseEntity.ok(rolService.saveRol(dto));
    }

    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> getAllRoles() {
        return ResponseEntity.ok(rolService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDTO> getRolById(@PathVariable Long id) {
        return rolService.getRolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolResponseDTO> updateRol(@PathVariable Long id, @RequestBody RolRequestDTO dto) {
        try {
            return ResponseEntity.ok(rolService.updateRol(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }
}
