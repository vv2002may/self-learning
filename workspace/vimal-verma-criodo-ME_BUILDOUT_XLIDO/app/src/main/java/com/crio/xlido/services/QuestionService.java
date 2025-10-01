package com.crio.xlido.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IEventRepository;
import com.crio.xlido.repositories.IQuestionRepository;
import com.crio.xlido.repositories.IUserRepository;

public class QuestionService {
    private final IQuestionRepository questionRepository;
    private final IEventRepository eventRepository;
    private final IUserRepository userRepository;

    public QuestionService(IQuestionRepository questionRepository, IEventRepository eventRepository,
            IUserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Question addQuestion(String content, Long userId, Long eventId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("User with an id " + userId + " does not exist");
        }
        Optional<Event> event = eventRepository.findById(eventId);
        if (!event.isPresent()) {
            throw new RuntimeException("Event with an id " + eventId + " does not exist");
        }
        Question question = new Question(content, userId, eventId);
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long questionId, Long userId) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (!question.isPresent()) {
            throw new RuntimeException("Question with an id " + questionId + " does not exist");
        }
        if (!userRepository.findById(userId).isPresent()) {
            throw new RuntimeException("User with an id " + userId + " does not exist");
        }
        if (question.get().getUserId() != userId) {
            throw new RuntimeException("User with an id " + userId
                    + " is not an author of question with an id " + questionId);
        }
        questionRepository.deleteById(questionId);
    }

    public void upvoteQuestion(Long questionId, Long userId) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (!question.isPresent()) {
            throw new RuntimeException("Question with an id " + questionId + " does not exist");
        }
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("User with an id " + userId + " does not exist");
        }
        if (question.get().hasUpvoted(userId)) {
            throw new RuntimeException("User with an id " + userId
                    + " has already upvoted a question with an id " + questionId);
        }
        question.get().addUpvote(userId);
    }

    public void replyQuestion(String content, Long questionId, Long userId) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (!question.isPresent()) {
            throw new RuntimeException("Question with an id " + questionId + " does not exist");
        }
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("User with an id " + userId + " does not exist");
        }
        question.get().setReply(content, userId);
    }

    public List<Question> listQuestions(Long eventId, String sortBy) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (!event.isPresent()) {
            throw new RuntimeException("Event with an id " + eventId + " does not exist");
        }
        List<Question> questions = questionRepository.findByEventId(eventId);

        if ("POPULAR".equalsIgnoreCase(sortBy)) {
            questions.sort(Comparator.comparingInt(Question::getVoteCount).reversed());
        } else if ("RECENT".equalsIgnoreCase(sortBy)) {
            questions.sort(Comparator.comparing(Question::getCreatedAt).reversed());
        }
        return questions;
    }
}
