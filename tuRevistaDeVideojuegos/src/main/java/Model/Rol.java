package Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

import java.util.Collection;

public class Rol {

    // id, rol, users

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "rols")
    // El conjunto de usuarios que tiene un rol concreto
    private Collection<User> rols;
}
