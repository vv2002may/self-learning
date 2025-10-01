package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.services.QuestionService;

public class CreateQuestionCommand implements ICommand{
    private final QuestionService questionService;

    public CreateQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void invoke(List<String> tokens) {
        // return question;
        String title=tokens.get(1); 
        DifficultyLevel level= DifficultyLevel.valueOf(tokens.get(2).toUpperCase());
        Integer difficultyScore=Integer.parseInt(tokens.get(3));
        
        Question question = questionService.createQuestion(title, level, difficultyScore);
        System.out.println("Question Id: "+question.getId());
    }

}
