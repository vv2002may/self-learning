package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.User;
import com.crio.qcontest.services.UserService;

public class LeaderBoardCommand implements ICommand {

    private final UserService userService;

    public LeaderBoardCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String order=String.valueOf(tokens.get(1));
        List<User> users=userService.showLeaderBoard(order);
        for(User user:users){
            System.out.println(user.getName()+" : "+user.getScore());
        }
    }  
}
