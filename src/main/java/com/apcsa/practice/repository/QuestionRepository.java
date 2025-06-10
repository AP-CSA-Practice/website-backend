package com.apcsa.practice.repository;

import com.apcsa.practice.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Question entity.
 * Provides methods to interact with the MongoDB database for question-related operations.
 * Extends MongoRepository to inherit standard CRUD operations.
 */
@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    
    /**
     * Finds a question by its question number.
     * 
     * @param questionNumber The sequential number of the question to find
     * @return The Question object with the specified question number, or null if not found
     */
    Question findByQuestionNumber(int questionNumber);
    
    /**
     * Custom query to retrieve only the IDs of all questions.
     * This is more efficient than fetching complete question objects when only IDs are needed.
     * 
     * @return A list of question IDs (strings)
     */
    @Query(value = "{}", fields = "{ '_id' : 1 }")
    List<String> findAllIds();
}