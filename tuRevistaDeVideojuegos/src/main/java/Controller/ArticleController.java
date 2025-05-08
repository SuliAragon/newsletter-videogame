package Controller;


import Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/")
    public ResponseEntity<> createArticle(@RequestBody DTORefencia DTODevuelve){
        DTOReference createArticle = articleService.save(DTODevuelve);
        return new ResponseEntity<>(createArticle, HttpStatus.CREATED);
    }

}
