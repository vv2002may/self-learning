package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.services.ContestService;

public class WithdrawContestCommand implements ICommand {

    private final ContestService contestService;

    public WithdrawContestCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long contestId=Long.valueOf(tokens.get(1));
        Long userId=Long.valueOf(tokens.get(2));
        Boolean isWithdraw=contestService.withdrawContest(contestId, userId);
        if(isWithdraw)
        System.out.print("User with an id " + userId + "withdrawn from contest with an id " + contestId);
    }
}
