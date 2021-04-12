package se.devnews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class TopicController {

    TopicRepository topicRepository;
    ArticleRepository articleRepository;

    public TopicController(TopicRepository topicRepository, ArticleRepository articleRepository) {
        this.topicRepository = topicRepository;
        this.articleRepository = articleRepository;
    }

    // Create a new topic
    @PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(@Valid @RequestBody Topic topic){
        topicRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    // associate the topic with the article given by articleId. If topic does not already exist, it is created.
    @PostMapping("/articles/{articleId}/topics")
    public ResponseEntity<Article> addArticleToTopic(@RequestBody Topic topicToAdd, @PathVariable Long articleId){
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        boolean topicExists = topicRepository.existsByName(topicToAdd.getName());
        if (!topicExists){
            topicRepository.save(topicToAdd);
        } else {
            topicToAdd = topicRepository.findByName(topicToAdd.getName());
        }
        article.getTopicsList().add(topicToAdd);
        articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    // return all topics
    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getAllTopics(){
        List<Topic> allTopics = topicRepository.findAll();
        return ResponseEntity.ok(allTopics);
    }

    // return all topics associated with article given by `articleId`
    @GetMapping("/articles/{articleId}/topics")
    public ResponseEntity<Set<Topic>> getAllTopicsOfArticle(@PathVariable Long articleId){
        Article givenArticle = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        Set<Topic> allTopicOfGivenArticle = givenArticle.getTopicsList();
        return ResponseEntity.ok(allTopicOfGivenArticle);
    }
}
