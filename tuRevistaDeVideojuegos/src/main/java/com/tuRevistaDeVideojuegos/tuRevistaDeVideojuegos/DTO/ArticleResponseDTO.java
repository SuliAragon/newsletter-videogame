package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO;

import java.util.Date;

public class ArticleResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String img;
    private String authorName;
    private Date createDate;
    private Date updateDate;

    public ArticleResponseDTO() {}

    public ArticleResponseDTO(Long id, String title, String content, String img, String authorName, Date createDate, Date updateDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.img = img;
        this.authorName = authorName;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // Getters y Setters

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
}
