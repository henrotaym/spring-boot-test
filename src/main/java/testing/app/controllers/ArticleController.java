package testing.app.controllers;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import testing.app.enums.ArticleStatus;
import testing.app.exceptions.repositories.ModelNotFoundException;
import testing.app.models.Article;
import testing.app.repositories.ArticleJdbcTemplateRepository;
import testing.app.repositories.ArticleRepository;
import testing.app.repositories.ArticleSpringDataRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




// GET articles -> INDEX (findAll)
// GET articles/:id -> SHOW (findById)
// POST articles -> STORE (store / save)
// PUT articles/:id -> UPDATE (update / save)
// DELETE articles/;id -> DESTROY (destroy / delete / deleteById)
 
@RestController
@RequestMapping("api/articles")
public class ArticleController {
    private ArticleJdbcTemplateRepository articleRepository;
    private ArticleSpringDataRepository articleSpringDataRepository;

    public ArticleController(
        ArticleJdbcTemplateRepository articleRepository,
        ArticleSpringDataRepository articleSpringDataRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleSpringDataRepository = articleSpringDataRepository;
    }
    
    @GetMapping("")
    public List<Article> findAll() {
        return this.articleSpringDataRepository.findByStatus(ArticleStatus.REVIEW);
    }
    
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void store(@RequestBody Article article) {
        this.articleSpringDataRepository.save(article);
    }

    @GetMapping("/{id}")
    public Optional<Article> findById(@PathVariable Integer id) {
        return this.articleSpringDataRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        this.articleSpringDataRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Article article) {
        this.articleSpringDataRepository.save(article);
    }
}
