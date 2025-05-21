package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.sun.org.apache.xml.internal.serializer.Method.TEXT;

@Entity
public class Article {


    @Id @GeneratedValue ( strategy = GenerationType.AUTO) @Column ( name = "article_id") // Autoincremental
    private Long id;

    @Temporal( TemporalType.TIMESTAMP) // fecha exacta con minutos y segundos
    @Column (name = "create_date", nullable = false, updatable = false) // obligatorio e inmodificable
    @CreationTimestamp // Se anota directamente en el instante en el que se ha creado
    private Date createDate;

    @Temporal( TemporalType.TIMESTAMP) // fecha exacta con minutos y segundos
    @Column (name = "update_date", nullable = true, updatable = true) // obligatorio e inmodificable
    @UpdateTimestamp // en el momento en el que haga una actualizacion se añade la hora
    private Date updateDate;

    // evita que sea un bucle recursivo
    @ManyToOne
    @JoinColumn (name = "user_id", referencedColumnName = "user_id", nullable = false)
    @JsonIgnore
    private User author;

    @Column (nullable = false) // obligatorio
    private String title;

    @Column (columnDefinition = TEXT)
    private String content;

    @Column (nullable = true)
    private String img;

    @ElementCollection(fetch = FetchType.EAGER) // Es importante usar ElementCollection para colecciones de tipos simples
    @CollectionTable(name = "article_types", joinColumns = @JoinColumn(name = "article_id"))
    @Enumerated(EnumType.STRING) // Para almacenar los valores de ENUM como Strings
    @Column(name = "type")
    private Set<ArticleType> types;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Set<ArticleType> getTypes() {
        return types;
    }

    public void setTypes(Set<ArticleType> types) {
        this.types = types;
    }

}


