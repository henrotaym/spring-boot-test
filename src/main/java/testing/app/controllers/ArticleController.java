package testing.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import testing.app.exceptions.repositories.ModelNotFoundException;
import testing.app.models.Article;
import testing.app.repositories.ArticleJdbcTemplateRepository;
import testing.app.repositories.ArticleRepository;

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

    public ArticleController(ArticleJdbcTemplateRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    
    @GetMapping("")
    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }
    
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void store(@RequestBody Article article) {
        this.articleRepository.store(article);
    }

    @GetMapping("/{id}")
    public Article findById(@PathVariable Integer id) {
        try {
            return this.articleRepository.findById(id);
        } catch (ModelNotFoundException notFound) {
            throw notFound.toResponse();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        try {
            this.articleRepository.deleteById(id);
        } catch (ModelNotFoundException notFound) {
            throw notFound.toResponse();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Article article) {
        try {
            this.articleRepository.save(article);
        } catch (ModelNotFoundException notFound) {
            throw notFound.toResponse();
        }
    }
}
