package testing.app.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;

import testing.app.models.Article;

@Component
public interface ArticleSpringDataRepository extends ListCrudRepository<Article, Integer> {

}
