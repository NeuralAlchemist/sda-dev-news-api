package se.devnews;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByName(String name);
    boolean existsByName(String name);
}
/**/