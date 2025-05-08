package Model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class Article {

    // id, fecha, fecha actualizacion, autor(user), titulo, contenido, img (string/ruta)

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

    @ManyToOne
    @JoinColumn (name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User author;

    @Column (nullable = false) // obligatorio
    private String title;

    @Column (columnDefinition = "TEXT")
    private String content;

    @Column (nullable = true)
    private String img;

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
}


