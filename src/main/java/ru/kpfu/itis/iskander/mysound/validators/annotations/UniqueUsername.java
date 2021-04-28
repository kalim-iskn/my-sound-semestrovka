package ru.kpfu.itis.iskander.mysound.validators.annotations;

import ru.kpfu.itis.iskander.mysound.validators.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "{validation.username.not_unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
