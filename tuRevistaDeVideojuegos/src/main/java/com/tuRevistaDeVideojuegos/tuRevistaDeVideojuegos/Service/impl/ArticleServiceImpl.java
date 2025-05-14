package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service.impl;

import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.Article;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.ArticleType;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Repository.ArticleRepository;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public Set<ArticleType> getArticleTypes(Long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            return article.get().getTypes(); // Devuelve los tipos de artículo
        }
        return new HashSet<>(); // Devuelve un conjunto vacío si no se encuentra el artículo
    }

    @Override
    public void updateArticleTypes(Long articleId, Set<ArticleType> types) {
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            Article existingArticle = article.get();
            existingArticle.setTypes(types); // Actualiza los tipos del artículo
            articleRepository.save(existingArticle); // Guarda el artículo actualizado
        }
    }

    // Método para convertir un Set<String> a Set<ArticleType>
    private Set<ArticleType> convertToArticleTypes(Set<String> types) {
        Set<ArticleType> articleTypes = new HashSet<>();
        for (String type : types) {
            try {
                articleTypes.add(ArticleType.valueOf(type.toUpperCase())); // Convierte a ArticleType
            } catch (IllegalArgumentException e) {
                // Si se encuentra un tipo inválido, podemos loguear el error o manejarlo de alguna manera
                System.err.println("Invalid type: " + type);
            }
        }
        return articleTypes;
    }
}
