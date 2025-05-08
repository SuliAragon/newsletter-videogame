// DTO/RolResponseDTO.java
package DTO;

public class RolResponseDTO {
    private Long id;
    private String name;

    public RolResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
