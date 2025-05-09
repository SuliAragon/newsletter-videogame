package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    // Relación uno a muchos con los artículos
    @OneToMany(mappedBy = "author") // Cambié "user" por "author" para que coincida con el nombre en el modelo de artículo
    private Collection<Article> articles;

    // Relación muchos a muchos con los roles
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Collection<Rol> rols;

    // Atributos básicos del usuario
    @Column(nullable = false, unique = true)
    private String username;  // Cambié de "user" a "username" para mayor claridad

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore // No incluir la contraseña en las respuestas JSON
    private String password;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Article> getArticles() {
        return articles;
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }

    public Collection<Rol> getRols() {
        return rols;
    }

    public void setRols(Collection<Rol> rols) {
        this.rols = rols;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
