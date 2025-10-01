package com.crio.xlido.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.crio.xlido.entities.Question;

public class QuestionRepository implements IQuestionRepository{

    private final Map<Long,Question> storageId=new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);
    @Override
    public Question save(Question entity) {
       Question question=new Question(idCounter.incrementAndGet(), entity);
       storageId.putIfAbsent(question.getId(), question);
       return question;
    }
    @Override
    public Optional<Question> findById(Long id) {
        return Optional.ofNullable(storageId.get(id));
    }

    @Override
    public void deleteById(Long id){
        storageId.remove(id);
    }

    @Override
    public List<Question> findByEventId(Long eventId){
        return storageId.values().stream()
                .filter(question -> question.getEventId().equals(eventId))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, Question> findAll() {
        return new HashMap<>(storageId);
    }
}
