package com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Controller;

import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO.ArticleRequestDTO;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.DTO.ArticleResponseDTO;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.Article;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.ArticleType;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Model.User;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Service.ArticleService;
import com.tuRevistaDeVideojuegos.tuRevistaDeVideojuegos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = "*")

public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserRepository userRepository;

    // Crear artículo
    @PostMapping("/")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequestDTO articleDTO){
        System.out.println("Llega petición para crear artículo");
        User author = userRepository.findById(articleDTO.getUserId()).orElse(null);
        if (author == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setImg(articleDTO.getImg());
        article.setAuthor(author);

        // Convertir los tipos de String a ArticleType
        Set<ArticleType> types = articleDTO.getTypes().stream()
                .map(ArticleType::valueOf)
                .collect(Collectors.toSet());
        article.setTypes(types);

        Article savedArticle = articleService.save(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }

    // Obtener todos
    @GetMapping("/")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    //Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getArticleById(@PathVariable Long id) {
        Optional<Article> article = articleService.getArticleById(id);
        if (article.isPresent()) {
            Article a = article.get();

            //xx` Creamos el DTO
            ArticleResponseDTO dto = new ArticleResponseDTO();
            dto.setId(a.getId());
            dto.setTitle(a.getTitle());
            dto.setContent(a.getContent());
            dto.setImg(a.getImg());
            dto.setCreateDate(a.getCreateDate());
            dto.setUpdateDate(a.getUpdateDate());
            dto.setAuthorName(a.getAuthor().getUsername()); // ✅ esto añade el nombre del autor

            // Convertimos los enums a String
            Set<String> typeStrings = new HashSet<>();
            for (ArticleType type : a.getTypes()) {
                typeStrings.add(type.name());
            }
            dto.setTypes(typeStrings);

            return new ResponseEntity<>(dto, HttpStatus.OK); // ✅ devolvemos el DTO
        } else {
            return new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND);
        }
    }


    // Actualizar artículo
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDTO dto) {
        return articleService.getArticleById(id).map(existing -> {
            if (dto.getTitle() != null) existing.setTitle(dto.getTitle());
            if (dto.getContent() != null) existing.setContent(dto.getContent());
            if (dto.getImg() != null) existing.setImg(dto.getImg());
            if (dto.getUserId() != null) {
                User user = userRepository.findById(dto.getUserId()).orElse(null);
                if (user == null)
                    return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
                existing.setAuthor(user);
            }
            // Convertir los tipos de String a ArticleType
            if (dto.getTypes() != null) {
                Set<ArticleType> types = dto.getTypes().stream()
                        .map(ArticleType::valueOf)
                        .collect(Collectors.toSet());
                existing.setTypes(types);
            }

            Article updated = articleService.updateArticle(existing);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }).orElse(new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND));
    }

    // Eliminar artículo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String folder = "public/img/";
            String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
            Path filepath = Paths.get(folder, filename);
            Files.copy(file.getInputStream(), filepath);

            String url = "/img/" + filename;
            return ResponseEntity.ok(Collections.singletonMap("url", url));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir imagen");
        }
    }
}

