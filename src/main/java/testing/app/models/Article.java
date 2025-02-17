package testing.app.models;

import java.sql.Timestamp;

import testing.app.enums.ArticleStatus;

public record Article(
    Integer id,
    String title,
    String slug,
    String description,
    ArticleStatus status,
    Timestamp createdAt
) {
    public Boolean isSaved() {
        return this.id != null;
    }
}