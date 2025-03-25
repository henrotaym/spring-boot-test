package testing.app.validators;

import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import testing.app.constraints.ExistsById;


public class ExistsByIdValidator implements ConstraintValidator<ExistsById, Integer> {
    private Class<? extends CrudRepository<?, Integer>> repositoryClass;
    private ApplicationContext applicationContext;
    public ExistsByIdValidator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    @Override
    public void initialize(ExistsById constraintAnnotation) {
        this.repositoryClass = constraintAnnotation.repository();
    }
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return this.applicationContext.getBean(this.repositoryClass).existsById(value);
    }
}
