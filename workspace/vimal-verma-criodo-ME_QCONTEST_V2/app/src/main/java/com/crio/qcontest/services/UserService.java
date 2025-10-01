package com.crio.qcontest.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.crio.qcontest.entities.User;
import com.crio.qcontest.repositories.IUserRepository;

class sortAsc implements Comparator<User> {
    public int compare(User a, User b) {
        return a.getScore() > b.getScore() ? 1 : -1;
    }
}


class sortDesc implements Comparator<User> {
    public int compare(User a, User b) {
        return a.getScore() < b.getScore() ? 1 : -1;
    }
}


public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user with the specified name.
     * 
     * @param name Name of the user.
     * @return Created User object.
     */
    public User createUser(String name) {
        User user = new User(name);
        return userRepository.save(user);
    }

    /**
     * Retrieves a list of users sorted by their score.
     * 
     * @param order Sorting order ("ASC" for ascending, "DESC" for descending).
     * @return List of users sorted by score as per the specified order.
     */
    public List<User> showLeaderBoard(String order) {
        List<User> users = userRepository.findAll();
        if (order.equals("ASC"))
            Collections.sort(users, new sortAsc());
        else
            Collections.sort(users, new sortDesc());
        return users;
        // return Collections.emptyList();
    }
}
