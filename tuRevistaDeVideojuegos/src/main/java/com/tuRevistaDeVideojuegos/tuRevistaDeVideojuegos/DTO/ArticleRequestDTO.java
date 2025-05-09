package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO;

public class ArticleRequestDTO {

    // Solo necesito los no autogenerados o autoincrementales
    // autor(user), titulo, contenido, img

    private String title;
    private String content;
    private String img;
    private Long userId; // ID del autor

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
}


