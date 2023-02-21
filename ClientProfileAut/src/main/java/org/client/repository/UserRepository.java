package org.client.repository;

import org.client.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final List<User> users;

    public UserRepository() {
        this.users = List.of(
                new User("user", "userPass", "IVAN", "IVANOV"),
                new User("admin", "adminPass", "PETR", "PETROV"));
    }

    public User getByLogin(String login) {
        return this.users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAll() {
        return this.users;
    }
}