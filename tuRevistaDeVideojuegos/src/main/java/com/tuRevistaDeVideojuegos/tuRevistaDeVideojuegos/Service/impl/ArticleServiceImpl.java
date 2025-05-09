package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service.impl;

import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.Article;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Repository.ArticleRepository;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
