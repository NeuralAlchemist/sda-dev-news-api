package se.devnews;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    ArticleRepository articleRepository;
    @Autowired
    public ArticleController(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Article>> listAllArticles(){
        List<Article> articles = articleRepository.findAll();
        return ResponseEntity.ok(articles);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article){
        articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){
        Article foundArticle = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(foundArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle){
        articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setId(id);
        Article article = articleRepository.save(updatedArticle);
        return ResponseEntity.ok(article);
    }
}
