package com.example.gardener.utils.validation;

import com.example.gardener.Repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {
    private final UserRepository userRepository;

    @Autowired
    public UniquePhoneValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.findUserByPhone(value).isEmpty();
    }
}
