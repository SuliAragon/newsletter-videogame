package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Repository;

import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository <Article, Long>{

}
