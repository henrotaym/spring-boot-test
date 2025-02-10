package testing.app.models;

import java.time.LocalDateTime;

import testing.app.enums.ArticleStatus;

public record Article(
    Integer id,
    String title,
    String slug,
    String description,
    ArticleStatus status,
    LocalDateTime createdAt
) {}