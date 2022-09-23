package ru.stepenko.taskSheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.stepenko.taskSheduler.model.User;
import ru.stepenko.taskSheduler.repository.UserRepository;

import java.util.Collections;

/**
 * Реализация основного интерфейса для загрузки пользовательских данных
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    /**
     * Получает детали пользователя
     * @param login имя пользователя, данные которого запрашиваем
     * @return основную пользовательскую информацию
     * @throws UsernameNotFoundException когда в БД нет пользователя с таким логином
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
