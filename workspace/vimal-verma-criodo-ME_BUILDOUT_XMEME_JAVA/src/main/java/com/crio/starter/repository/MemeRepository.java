package com.crio.starter.repository;

import java.util.List;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemeRepository extends MongoRepository<MemeEntity,String> {
    
    // List<MemeEntity> findAllByOrderByCreatedAtAsc();
    // List<MemeEntity> findAllByOrderByCreatedAtDesc();
    Optional<MemeEntity> findById(String id) ;
    List<MemeEntity> findByNameAndUrlAndCaption(String name , String url , String caption);
    List<MemeEntity> findTop100ByOrderByIdDesc();

}
