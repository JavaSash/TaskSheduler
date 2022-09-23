package ru.stepenko.taskSheduler.service;

import ru.stepenko.taskSheduler.model.User;

public interface UserService {

    User create(User user);

    User getCurrentUser();
}
