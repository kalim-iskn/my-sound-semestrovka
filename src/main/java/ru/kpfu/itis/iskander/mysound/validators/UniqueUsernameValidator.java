package ru.kpfu.itis.iskander.mysound.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;
import ru.kpfu.itis.iskander.mysound.validators.annotations.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UsersRepository userRepository;

    private boolean onePossible;

    @Override
    public void initialize(UniqueUsername params) {
        onePossible = params.onePossible();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return onePossible ? userRepository.countAllByUsername(name) <= 1 : !userRepository.existsByUsername(name);
    }

}
