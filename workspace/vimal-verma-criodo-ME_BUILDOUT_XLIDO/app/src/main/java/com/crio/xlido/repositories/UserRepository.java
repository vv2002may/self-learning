package com.crio.xlido.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.crio.xlido.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<Long, User> storageId = new HashMap<>();
    private final Map<String, User> storageEmail = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public User save(User entity) {
        User user = new User(idCounter.incrementAndGet(),entity);
        storageId.putIfAbsent(user.getId(),user);
        storageEmail.putIfAbsent(user.getemail(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(storageId.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(storageId.get(id));
    } 

    @Override
    public Optional<User> findByEmail(String email){
        return Optional.ofNullable(storageEmail.get(email));
    }
}