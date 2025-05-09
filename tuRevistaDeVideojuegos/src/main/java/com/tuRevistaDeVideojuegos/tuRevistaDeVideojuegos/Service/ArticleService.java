package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service;

import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Article save(Article article);
    List<Article> getAllArticles();
    Optional<Article> getArticleById(Long id);
    Article updateArticle(Article article);
    void deleteArticle(Long id);
}
