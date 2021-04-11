package se.devnews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentController {
    CommentRepository commentRepository;
    ArticleRepository articleRepository;

    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    // Create a comment for the articleId
    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long articleId, @RequestBody Comment comment) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        comment.setOwner(article);
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    //Update a given comment
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateCar(@PathVariable Long id, @Valid @RequestBody Comment updatedComment) {
        Comment comment = commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedComment.setId(id);
        commentRepository.save(updatedComment);
        return ResponseEntity.ok(updatedComment);
    }

    // Return all comments on a given article
    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByArticleId(@PathVariable Long articleId) {
        List<Comment> allComments = commentRepository
                .findAll()
                .stream()
                .filter((item) -> item.getOwner().getId().equals(articleId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(allComments);
    }

    // Return all comments made by a author
    @GetMapping(value = "/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByAuthorName(@RequestParam(value = "authorName") String authorName) {
        List<Comment> allComments = commentRepository
                .findAll()
                .stream()
                .filter((item) -> item.getAuthorName().equals(authorName))
                .collect(Collectors.toList());
        return ResponseEntity.ok(allComments);
    }
}
