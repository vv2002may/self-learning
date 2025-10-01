package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.Contestant;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.services.ContestService;

public class ContestHistoryCommand implements ICommand {

    private final ContestService contestService;

    public ContestHistoryCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long contestId=Long.valueOf(tokens.get(1));
        List<Contestant> contestants=contestService.contestHistory(contestId);
        for(Contestant contestant: contestants){
            System.out.print(contestant.getUser().getName()+" : "+ contestant.getTotalScore()+" [");
            contestant.getSolvedQuestions().forEach((question)->{System.out.print(question.getId()+"");});
            System.out.println("]");
        }
    }
    
}
