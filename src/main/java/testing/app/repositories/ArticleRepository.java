package testing.app.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import testing.app.models.Article;

@Repository
public class ArticleRepository {
    private List<Article> list = new ArrayList<>();
    public List<Article> findAll() {
        return this.list;
    }

    public void store(Article article) {
        this.list.add(article);
    }

    public Article findById(Integer id) {
        for (Article article : list) {
            if (article.id() == id) {
                return article;
            }
        }

        return null;
    }

    public void deleteById(Integer id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id() == id) {
                list.remove(i);
                break;
            }
        }
    }

    public void updateById(Integer id, Article article) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id() == id) {
                list.set(i, article);
                break;
            }
        }
    }
}
