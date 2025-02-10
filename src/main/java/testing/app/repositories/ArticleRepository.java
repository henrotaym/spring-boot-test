package testing.app.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import testing.app.models.Article;

@Repository
public class ArticleRepository {
    private List<Article> articleList = new ArrayList<Article>();

    public List<Article> findAll() {
        return this.articleList;
    }

    public void store(Article article) {
        this.articleList.add(article);
    }
}
