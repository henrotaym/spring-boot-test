package testing.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import testing.app.constraints.ExistsById;
import testing.app.constraints.Uppercase;
import testing.app.models.Article;
import testing.app.repositories.ArticleJdbcTemplateRepository;
import testing.app.repositories.ArticleSpringDataRepository;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    private ArticleSpringDataRepository articleSpringDataRepository;

    public ArticleController(
        ArticleJdbcTemplateRepository articleRepository,
        ArticleSpringDataRepository articleSpringDataRepository
    ) {
        this.articleSpringDataRepository = articleSpringDataRepository;
    }
    
    @GetMapping("")
    public List<Article> findAll() {
        return this.articleSpringDataRepository.findAll();
    }
    
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void store(@RequestBody @Valid Article article) {
        this.articleSpringDataRepository.save(article);
    }

    @GetMapping("/{id}")
    public Optional<Article> findById(@PathVariable @ExistsById(repository = ArticleSpringDataRepository.class) Integer id) {
        return this.articleSpringDataRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable @Uppercase(repository = ArticleSpringDataRepository.class) Integer id) {
        this.articleSpringDataRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody @Valid Article article) {
        this.articleSpringDataRepository.save(article);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
    MethodArgumentNotValidException validationException) {
        Map<String, String> errors = new HashMap<>();

        validationException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
