package se.devnews;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body, authorName;
    @ManyToOne
    private Article owner;

    public Comment(String body, String authorName) {
        this.body = body;
        this.authorName = authorName;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Article getOwner() {
        return owner;
    }

    public void setOwner(Article owner) {
        this.owner = owner;
    }
}
