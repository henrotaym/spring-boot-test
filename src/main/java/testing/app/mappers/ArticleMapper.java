package testing.app.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import testing.app.enums.ArticleStatus;
import testing.app.models.Article;

@Component
public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Integer id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String slug = resultSet.getString("slug");
        String description = resultSet.getString("description");
        ArticleStatus status = ArticleStatus.valueOf(resultSet.getString("status"));
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        return new Article(id, title, slug, description, status, createdAt);
    }
    
}
