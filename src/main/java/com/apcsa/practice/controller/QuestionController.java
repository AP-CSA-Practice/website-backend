package com.apcsa.practice.controller;

import com.apcsa.practice.model.Question;
import com.apcsa.practice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing AP CSA practice questions.
 * Provides endpoints for creating, reading, updating, and deleting questions.
 */
@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "http://localhost:3000") // Allows requests from the React frontend
public class QuestionController {
    
    private final QuestionService questionService;
    
    /**
     * Constructor for QuestionController with dependency injection.
     * Initializes sample questions when the controller is created.
     * 
     * @param questionService The service for managing question data
     */
    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
        
        // Initialize sample questions for demonstration purposes
        questionService.initSampleQuestions();
    }
    
    /**
     * Retrieves all AP CSA practice questions.
     * 
     * @return ResponseEntity containing a list of all questions with HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.findAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    
    /**
     * Retrieves a specific question by its ID.
     * 
     * @param id The unique identifier of the question
     * @return ResponseEntity containing the question if found (HTTP status 200),
     *         or HTTP status 404 (NOT_FOUND) if the question doesn't exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable String id) {
        Optional<Question> question = questionService.findQuestionById(id);
        return question.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Retrieves a random AP CSA practice question.
     * 
     * @return ResponseEntity containing a random question (HTTP status 200),
     *         or HTTP status 404 (NOT_FOUND) if no questions exist
     */
    @GetMapping("/random")
    public ResponseEntity<Question> getRandomQuestion() {
        Question question = questionService.getRandomQuestion();
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Creates a new AP CSA practice question.
     * 
     * @param question The question object to be created
     * @return ResponseEntity containing the saved question with HTTP status 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }
    
    /**
     * Updates an existing AP CSA practice question.
     * 
     * @param id The unique identifier of the question to update
     * @param question The updated question data
     * @return ResponseEntity containing the updated question (HTTP status 200),
     *         or HTTP status 404 (NOT_FOUND) if the question doesn't exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String id, @RequestBody Question question) {
        Optional<Question> existingQuestion = questionService.findQuestionById(id);
        if (existingQuestion.isPresent()) {
            question.setId(id);
            Question updatedQuestion = questionService.updateQuestion(question);
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Deletes an AP CSA practice question.
     * 
     * @param id The unique identifier of the question to delete
     * @return ResponseEntity with HTTP status 204 (NO_CONTENT) if successfully deleted,
     *         or HTTP status 404 (NOT_FOUND) if the question doesn't exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id) {
        Optional<Question> question = questionService.findQuestionById(id);
        if (question.isPresent()) {
            questionService.deleteQuestion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
