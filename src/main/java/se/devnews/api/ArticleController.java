package se.devnews.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.devnews.model.Article;
import se.devnews.repository.ArticleRepository;
import se.devnews.ResourceNotFoundException;

import java.util.List;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    ArticleRepository articleRepository;
    @Autowired
    public ArticleController(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    // Return all articles
    @GetMapping
    public ResponseEntity<List<Article>> listAllArticles(){
        List<Article> articles = articleRepository.findAll();
        return ResponseEntity.ok(articles);
    }

    // Create a new article
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article){
        articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    // Return a specific article based on the provided id
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){
        Article foundArticle = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(foundArticle);
    }

    // Update the given article
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle){
        articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setId(id);
        Article article = articleRepository.save(updatedArticle);
        return ResponseEntity.ok(article);
    }

    // Delete the given article
    @DeleteMapping("/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id){
        Article toBeDeleted = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articleRepository.delete(toBeDeleted);
        return ResponseEntity.ok(toBeDeleted);
    }
}
