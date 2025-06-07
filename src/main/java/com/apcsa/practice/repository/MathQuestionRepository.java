package com.apcsa.practice.repository;

import com.apcsa.practice.model.MathQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MathQuestionRepository extends MongoRepository<MathQuestion, String> {
    
    // 根據題號查詢
    MathQuestion findByQuestionNumber(int questionNumber);
    
    // 自定義查詢，獲取所有題目的 ID
    @Query(value = "{}", fields = "{ '_id' : 1 }")
    List<String> findAllIds();
}