package testing.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import testing.app.enums.ArticleStatus;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String slug;
    private String description;
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    public Article() {

    }

    public Article(
        Integer id,
        String title,
        String slug,
        String description,
        ArticleStatus status
    ) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isSaved() {
        return this.id != null;
    }
}