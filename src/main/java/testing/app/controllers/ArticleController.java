package testing.app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import testing.app.models.Article;
import testing.app.repositories.ArticleRepository;

@RestController
@RequestMapping("api/articles")
public class ArticleController {
    private ArticleRepository repository;

    public ArticleController(ArticleRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Article> findAll() {
        return this.repository.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void store(@RequestBody Article article) {
        this.repository.store(article);
    }

    // @GetMapping("{id}")
    // public Article findById(@PathVariable Integer id) {
    //     return this.repository.findById(id);
    // }
}
