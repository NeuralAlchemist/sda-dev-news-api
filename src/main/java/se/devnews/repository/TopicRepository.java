package se.devnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.devnews.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByName(String name);
    boolean existsByName(String name);
}
/**/