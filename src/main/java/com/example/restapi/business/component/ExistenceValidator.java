package com.example.restapi.business.component;

import com.example.restapi.business.exception.NotFoundException;
import com.example.restapi.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistenceValidator {

    private final UserRepository userRepository;

    public void validateUser(Long userId) {
        boolean result = userRepository.existsUserBy(userId);

        if (!result) {
            throw new NotFoundException("User Not Found [Id: " + userId + "]");
        }
    }
}
