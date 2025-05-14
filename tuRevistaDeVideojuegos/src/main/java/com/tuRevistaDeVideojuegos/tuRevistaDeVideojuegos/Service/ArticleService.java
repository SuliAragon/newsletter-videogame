package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service;

import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.Article;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.ArticleType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticleService {
    Article save(Article article);
    List<Article> getAllArticles();
    Optional<Article> getArticleById(Long id);
    Article updateArticle(Article article);
    void deleteArticle(Long id);
    Set<ArticleType> getArticleTypes(Long articleId);
    void updateArticleTypes(Long articleId, Set<ArticleType> types);
}
