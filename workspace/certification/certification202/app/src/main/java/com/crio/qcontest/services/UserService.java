package com.crio.qcontest.services;

 import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.crio.qcontest.entities.User;
import com.crio.qcontest.repositories.IUserRepository;

public class UserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user with the specified name.
     * @param name Name of the user.
     * @return Created User object.
     */
    public User createUser(String name) {

        User user = new User(name);

        User newUser = userRepository.save(user);

        return newUser;
    }

    /**
     * Retrieves a list of users sorted by their score.
     * @param order Sorting order ("ASC" for ascending, "DESC" for descending).
     * @return List of users sorted by score as per the specified order.
     */
    public List<User> showLeaderBoard(String order) {

        //Retrieve all users from the repository
        List<User> users = userRepository.findAll();
 
    //     // Sort users based on the score
    // if ("ASC".equalsIgnoreCase(order)) {
    //     users.sort(Comparator.comparingInt(User::getScore));
    // } else if ("DESC".equalsIgnoreCase(order)) {
    //     users.sort(Comparator.comparingInt(User::getScore).reversed());
    // } else {
    //     // If the order is neither "ASC" nor "DESC", you can handle it as an error or use a default order
    //     throw new IllegalArgumentException("Invalid order parameter: " + order);
    // }

      // Sort users based on the score using lambda expressions
      if ("ASC".equalsIgnoreCase(order)) {
        users.sort((u1, u2) -> Integer.compare(u1.getScore(), u2.getScore()));
    } else if ("DESC".equalsIgnoreCase(order)) {
        users.sort((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()));
    } else {
        // If the order is neither "ASC" nor "DESC", throw an exception
        throw new IllegalArgumentException("Invalid order parameter: " + order);
    }


        return users;
    } 
}