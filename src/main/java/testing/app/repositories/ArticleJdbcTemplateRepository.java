package testing.app.repositories;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import testing.app.mappers.ArticleMapper;
import testing.app.models.Article;

@Repository
public class ArticleJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ArticleMapper articleMapper;

    public ArticleJdbcTemplateRepository(
        JdbcTemplate jdbcTemplate,
        ArticleMapper articleMapper
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.articleMapper = articleMapper;
    }

    public List<Article> findAll() {
        String sql = "SELECT * FROM articles";
        return this.jdbcTemplate.query(sql, this.articleMapper);
    }

    public void store(Article article) {
        String sql = "INSERT INTO articles (title, slug, description, status, created_at) VALUES (?, ?, ?, ?, NOW())";

        this.jdbcTemplate.update(sql, article.title(), article.slug(), article.description(), article.status().toString());
    }

    public Article findById(Integer id) {
        String sql = "SELECT * FROM articles WHERE id = ?";
    }
}
