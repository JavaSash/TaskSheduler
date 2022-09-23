package ru.stepenko.taskSheduler.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import ru.stepenko.taskSheduler.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    //TODO починить
    @Modifying
//    @Query("SELECT u FROM users u WHERE u.login = :login")
    Optional<User> findByLogin(String login);
}
