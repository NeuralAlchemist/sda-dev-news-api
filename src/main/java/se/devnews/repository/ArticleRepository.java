package se.devnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.devnews.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
