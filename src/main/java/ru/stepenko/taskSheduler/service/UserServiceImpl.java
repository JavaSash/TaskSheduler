package ru.stepenko.taskSheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.stepenko.taskSheduler.model.User;
import ru.stepenko.taskSheduler.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository repository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return repository.findByLogin(principal.getUsername()).orElseThrow(() -> new IllegalArgumentException("user not found"));
    }
}
