package Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class User {

    // id, user o nickname,  email, rol, articulos y contrasena

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private Collection<Article> articles;

    @ManyToMany(cascade = CascadeType.ALL) //afecta al cambio de nombre de rol a todos los user
    @JoinTable (name = "user_rol", joinColumns = @JoinColumn( name="user_id" ), inverseJoinColumns = @JoinColumn(name = "rol_id")) // nombre de la tabla intermedia
    private Collection<Rol> rols;

    @Column (nullable = false)
    @JsonIgnore // Cuando se comunica por API, no aparece (Se proteje)
    private String password;
}
