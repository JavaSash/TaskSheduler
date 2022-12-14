package ru.stepenko.taskSheduler.repository;

import org.springframework.data.repository.CrudRepository;
import ru.stepenko.taskSheduler.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
