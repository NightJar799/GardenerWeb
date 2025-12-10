package com.example.gardener.utils.validation;

import com.example.gardener.Repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {
    private final UserRepository userRepository;

    public UniquePhoneValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.findUserByPhone(value).isEmpty();
    }
}
