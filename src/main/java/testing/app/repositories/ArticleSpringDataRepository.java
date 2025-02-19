package testing.app.repositories;

import org.springframework.data.repository.ListCrudRepository;

import testing.app.models.Article;

public interface ArticleSpringDataRepository extends ListCrudRepository<Article, Integer> {

}
