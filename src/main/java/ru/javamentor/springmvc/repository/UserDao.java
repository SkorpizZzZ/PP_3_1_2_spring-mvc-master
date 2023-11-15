package ru.javamentor.springmvc.repository;

import ru.javamentor.springmvc.model.User;

import java.util.List;

public interface UserDao {
    User save(User user);

    void delete(Long index);

    void update(User user);

    User findById(Long id);

    List<User> findAll();
}
