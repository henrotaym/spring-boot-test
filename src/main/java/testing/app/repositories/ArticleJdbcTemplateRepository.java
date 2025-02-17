package testing.app.repositories;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import testing.app.exceptions.repositories.ModelNotFoundException;
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

    public void update(Article article) throws ModelNotFoundException {
        this.findById(article.id());
        String sql = "UPDATE articles SET title = ?, slug = ?, description = ?, status = ? WHERE id = ?";

        this.jdbcTemplate.update(sql, article.title(), article.slug(), article.description(), article.status().toString(), article.id());
    }

    public void save(Article article) throws ModelNotFoundException {
        if (article.isSaved()) {
            this.update(article);
            return;
        }
        
        this.store(article);
    }

    public Article findById(Integer id) throws ModelNotFoundException{
        String sql = "SELECT * FROM articles WHERE id = ?";
        try {
            return this.jdbcTemplate.queryForObject(sql, this.articleMapper, id); 
        } catch (EmptyResultDataAccessException notFoundException) {
            throw new ModelNotFoundException();
        }
    }

    public void deleteById(Integer id) throws ModelNotFoundException {
        this.findById(id);

        String sql = "DELETE FROM articles WHERE id = ?";

        this.jdbcTemplate.update(sql, id);
    }
}
