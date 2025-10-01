package com.crio.xlido.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.xlido.entities.Question;

public interface IQuestionRepository {
    Question save(Question entity);
    Optional<Question> findById(Long id);
    void deleteById(Long id);
    Map<Long, Question> findAll();
    List<Question> findByEventId(Long eventId);
}
