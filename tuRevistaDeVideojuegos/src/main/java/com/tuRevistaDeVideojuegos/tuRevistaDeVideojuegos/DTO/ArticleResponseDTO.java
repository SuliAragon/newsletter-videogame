package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO;

import java.util.Date;
import java.util.Set;

public class ArticleResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String img;
    private String authorName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long userId;
    private Date createDate;
    private Date updateDate;
    private Set<String> types; // Agregado para devolver los tipos de artículo

    // Getters y setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getImg() { return img; }

    public void setImg(String img) { this.img = img; }

    public String getAuthorName() { return authorName; }

    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public Date getCreateDate() { return createDate; }

    public void setCreateDate(Date createDate) { this.createDate = createDate; }

    public Date getUpdateDate() { return updateDate; }

    public void setUpdateDate(Date updateDate) { this.updateDate = updateDate; }

    public Set<String> getTypes() {
        return types;
    }

    public void setTypes(Set<String> types) { this.types = types; }

}
