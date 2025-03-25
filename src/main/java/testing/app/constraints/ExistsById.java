package testing.app.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.data.repository.CrudRepository;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import testing.app.validators.ExistsByIdValidator;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsByIdValidator.class)
@Documented
public @interface ExistsById {

    String message() default "must be in uppercase.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<? extends CrudRepository<?, Integer>> repository();
}