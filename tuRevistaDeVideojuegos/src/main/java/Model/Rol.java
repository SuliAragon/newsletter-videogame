package Model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rol_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // El nombre del rol, por ejemplo: "ADMIN", "USER", etc.

    @ManyToMany(mappedBy = "rols", cascade = CascadeType.ALL)
    private Collection<User> users;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
