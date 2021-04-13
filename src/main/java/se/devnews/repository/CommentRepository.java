package se.devnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.devnews.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
