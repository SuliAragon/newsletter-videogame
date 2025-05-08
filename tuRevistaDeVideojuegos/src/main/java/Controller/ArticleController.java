package Controller;

import DTO.ArticleRequestDTO;
import Model.Article;
import Model.User;
import Repository.ArticleRepository;
import Repository.UserRepository;
import Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    // Crear artículo
    @PostMapping("/")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequestDTO articleDTO){
        User author = userRepository.findById(articleDTO.getUserId()).orElse(null);
        if (author == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setImg(articleDTO.getImg());
        article.setAuthor(author);

        Article savedArticle = articleService.save(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }

    // Obtener todos
    @GetMapping("/")
    public ResponseEntity<List<Article>> getAllArticles() {
        return new ResponseEntity<>(articleRepository.findAll(), HttpStatus.OK);
    }

    //Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Long id) {
        return articleRepository.findById(id)
                .<ResponseEntity<?>>map(article -> new ResponseEntity<>(article, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND));
    }

    // Actualizar artículo
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDTO dto) {
        return articleRepository.findById(id).map(existing -> {
            if (dto.getTitle() != null) existing.setTitle(dto.getTitle());
            if (dto.getContent() != null) existing.setContent(dto.getContent());
            if (dto.getImg() != null) existing.setImg(dto.getImg());
            if (dto.getUserId() != null) {
                User user = userRepository.findById(dto.getUserId()).orElse(null);
                if (user == null)
                    return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
                existing.setAuthor(user);
            }
            Article updated = articleService.save(existing);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }).orElse(new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND));
    }

    // Eliminar artículo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        if (!articleRepository.existsById(id)) {
            return new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND);
        }
        articleRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
