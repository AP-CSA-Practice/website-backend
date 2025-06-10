package com.apcsa.practice.service;

import com.apcsa.practice.model.Question;
import com.apcsa.practice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import org.bson.Document;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing AP CSA practice questions.
 * Provides business logic for creating, retrieving, updating, and deleting questions.
 */
@Service
public class QuestionService {
    
    private final QuestionRepository questionRepository;
    private final MongoTemplate mongoTemplate;
    
    /**
     * Constructor with dependency injection for required repositories and templates.
     * 
     * @param questionRepository Repository for question data access
     * @param mongoTemplate Template for advanced MongoDB operations
     */
    @Autowired
    public QuestionService(QuestionRepository questionRepository, MongoTemplate mongoTemplate) {
        this.questionRepository = questionRepository;
        this.mongoTemplate = mongoTemplate;
    }
    
    /**
     * Saves a single AP CSA question to the database.
     * 
     * @param question The question object to save
     * @return The saved question with generated ID
     */
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
    
    /**
     * Saves multiple AP CSA questions to the database.
     * 
     * @param questions List of question objects to save
     * @return List of saved questions with generated IDs
     */
    public List<Question> saveAllQuestions(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }
    
    /**
     * Finds an AP CSA question by its unique identifier.
     * 
     * @param id The unique identifier of the question
     * @return An Optional containing the question if found, or empty if not found
     */
    public Optional<Question> findQuestionById(String id) {
        return questionRepository.findById(id);
    }
    
    /**
     * Finds an AP CSA question by its question number.
     * 
     * @param questionNumber The sequential number of the question
     * @return The question with the specified number, or null if not found
     */
    public Question findQuestionByNumber(int questionNumber) {
        return questionRepository.findByQuestionNumber(questionNumber);
    }
    
    /**
     * Retrieves all AP CSA questions from the database.
     * 
     * @return List of all questions
     */
    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    /**
     * Checks the database connection and displays detailed information about the connection.
     * This is a diagnostic method used for troubleshooting database connectivity issues.
     */
    private void checkDatabaseConnection() {
        try {
            // Get database connection information
            String databaseName = mongoTemplate.getDb().getName();
            // Get connection string (from Spring configuration)
            String connectionString = "mongodb://localhost:27017"; // Default value, should be retrieved from configuration
            // Try to execute a simple command to check the connection
            Document pingCommand = new Document("ping", 1);
            Document result = mongoTemplate.getDb().runCommand(pingCommand);
            
            if (result != null && result.getDouble("ok") == 1.0) {
                System.out.println("=== Database Connection Information ===");
                System.out.println("Connection Status: Success");
                System.out.println("Database Name: " + databaseName);
                System.out.println("Connection URL: " + connectionString);
                
                // Get all collection names
                List<String> collectionNames = mongoTemplate.getCollectionNames()
                    .stream()
                    .collect(Collectors.toList());
                System.out.println("Collections in Database: " + String.join(", ", collectionNames));
                
                // Check questions collection
                if (collectionNames.contains("questions")) {
                    long count = mongoTemplate.getCollection("questions").countDocuments();
                    System.out.println("Number of Documents in questions Collection: " + count);
                    
                    // Display an example of the first document (if exists)
                    if (count > 0) {
                        Document firstDoc = mongoTemplate.getCollection("questions").find().first();
                        if (firstDoc != null) {
                            System.out.println("Example Document: " + firstDoc.toJson());
                        }
                    }
                } else {
                    System.out.println("questions Collection does not exist");
                }
                System.out.println("=======================================");
            } else {
                System.err.println("Database Connection Error: Unexpected response");
            }
        } catch (Exception e) {
            System.err.println("=== Database Connection Failed ===");
            System.err.println("Error Message: " + e.getMessage());
            System.err.println("Error Type: " + e.getClass().getName());
            System.err.println("=================================");
            e.printStackTrace(); // Print detailed stack trace
        }
    }
    
    /**
     * Retrieves a random AP CSA question from the database.
     * Uses MongoDB's sample aggregation to efficiently select a random document.
     * 
     * @return A randomly selected question, or null if no questions exist
     */
    public Question getRandomQuestion() {
        // Check database connection
        checkDatabaseConnection();
        
        // Use aggregation pipeline to randomly select one document
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.sample(1)
        );
        
        AggregationResults<Question> results = mongoTemplate.aggregate(
            aggregation, "questions", Question.class
        );
        
        return results.getUniqueMappedResult();
    }
    
    /**
     * Updates an existing AP CSA question.
     * If the question has an ID that exists in the database, it will be updated;
     * otherwise, a new question will be created.
     * 
     * @param question The question object with updated fields
     * @return The updated question
     */
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }
    
    /**
     * Deletes an AP CSA question by its ID.
     * 
     * @param id The unique identifier of the question to delete
     */
    public void deleteQuestion(String id) {
        questionRepository.deleteById(id);
    }
    
    /**
     * Initializes the database with sample AP CSA questions if the database is empty.
     * This method is called when the application starts to ensure there's test data available.
     */
    public void initSampleQuestions() {
        if (questionRepository.count() == 0) {
            List<Question> sampleQuestions = List.of(
                new Question(
                    1, 
                    "Which of the following is a correct variable declaration in Java?",
                    "A. int x = \"Hello\";", 
                    "B. String y = 5;", 
                    "C. boolean z = true;", 
                    "D. char c = \"A\";",
                    "C"
                ),
                new Question(
                    2, 
                    "In Java, which statement correctly describes inheritance?",
                    "A. A class can inherit from multiple classes", 
                    "B. A subclass can access the private members of its superclass", 
                    "C. A subclass inherits the public methods and properties of its superclass", 
                    "D. Static methods can be overridden",
                    "C"
                ),
                new Question(
                    3, 
                    "Which of the following is NOT a primitive data type in Java?",
                    "A. int", 
                    "B. boolean", 
                    "C. String", 
                    "D. char",
                    "C"
                ),
                new Question(
                    4, 
                    "In Java, array indices start at which number?",
                    "A. -1", 
                    "B. 0", 
                    "C. 1", 
                    "D. Depends on the array type",
                    "B"
                ),
                new Question(
                    5, 
                    "Which keyword is used to create a new instance of an object in Java?",
                    "A. this", 
                    "B. new", 
                    "C. create", 
                    "D. instance",
                    "B"
                )
            );
            
            questionRepository.saveAll(sampleQuestions);
        }
    }
}