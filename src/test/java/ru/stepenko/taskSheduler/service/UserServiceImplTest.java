package ru.stepenko.taskSheduler.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.stepenko.taskSheduler.model.User;
import ru.stepenko.taskSheduler.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder encoder;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        reset(repository, encoder);
    }

    @Test
    void create_Test() {
        User user = new User();
        user.setPassword("1234");
        String encodedPassw = "jf2w0jisdlfnweijdf0qjiedsolkfmwepofdjmsd";
        when(encoder.encode(any())).thenReturn(encodedPassw);

        userService.create(user);

        assertAll(
                () -> verify(repository).save(any()),
                () -> assertEquals(encodedPassw, user.getPassword())
        );
    }
}