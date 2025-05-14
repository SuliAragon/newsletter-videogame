package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO;

import java.util.Set;

public class ArticleRequestDTO {

    private String title;
    private String content;
    private String img;
    private Long userId;  // ID del autor
    private Set<String> types; // Tipos de artículo (por ejemplo, "INDIE", "NOTICIA", etc.)

    // Getters y setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<String> getTypes() {
        return types;
    }

    public void setTypes(Set<String> types) {
        this.types = types;
    }
}
