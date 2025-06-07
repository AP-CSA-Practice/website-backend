package com.apcsa.practice.service;

import com.apcsa.practice.model.MathQuestion;
import com.apcsa.practice.repository.MathQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MathQuestionService {
    
    private final MathQuestionRepository mathQuestionRepository;
    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public MathQuestionService(MathQuestionRepository mathQuestionRepository, MongoTemplate mongoTemplate) {
        this.mathQuestionRepository = mathQuestionRepository;
        this.mongoTemplate = mongoTemplate;
    }
    
    // 保存數學題目
    public MathQuestion saveMathQuestion(MathQuestion mathQuestion) {
        return mathQuestionRepository.save(mathQuestion);
    }
    
    // 批量保存數學題目
    public List<MathQuestion> saveAllMathQuestions(List<MathQuestion> mathQuestions) {
        return mathQuestionRepository.saveAll(mathQuestions);
    }
    
    // 根據ID查找數學題目
    public Optional<MathQuestion> findMathQuestionById(String id) {
        return mathQuestionRepository.findById(id);
    }
    
    // 根據題號查找數學題目
    public MathQuestion findMathQuestionByNumber(int questionNumber) {
        return mathQuestionRepository.findByQuestionNumber(questionNumber);
    }
    
    // 查找所有數學題目
    public List<MathQuestion> findAllMathQuestions() {
        return mathQuestionRepository.findAll();
    }
    
    // 隨機獲取一個數學題目
    public MathQuestion getRandomMathQuestion() {
        // 使用聚合管道隨機選擇一個文檔
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.sample(1)
        );
        
        AggregationResults<MathQuestion> results = mongoTemplate.aggregate(
            aggregation, "math_questions", MathQuestion.class
        );
        
        return results.getUniqueMappedResult();
    }
    
    // 更新數學題目
    public MathQuestion updateMathQuestion(MathQuestion mathQuestion) {
        return mathQuestionRepository.save(mathQuestion);
    }
    
    // 刪除數學題目
    public void deleteMathQuestion(String id) {
        mathQuestionRepository.deleteById(id);
    }
    
    // 初始化一些示例數學題目（如果數據庫為空）
    public void initSampleQuestions() {
        if (mathQuestionRepository.count() == 0) {
            List<MathQuestion> sampleQuestions = List.of(
                new MathQuestion(
                    1, 
                    "如果 x^2 - 5x + 6 = 0，則 x 的值是多少？",
                    "A. 2 和 3", 
                    "B. -2 和 -3", 
                    "C. 1 和 6", 
                    "D. -1 和 -6",
                    "A"
                ),
                new MathQuestion(
                    2, 
                    "計算 log₂(8) 的值",
                    "A. 2", 
                    "B. 3", 
                    "C. 4", 
                    "D. 8",
                    "B"
                ),
                new MathQuestion(
                    3, 
                    "三角形的三個內角和等於多少度？",
                    "A. 90°", 
                    "B. 180°", 
                    "C. 270°", 
                    "D. 360°",
                    "B"
                ),
                new MathQuestion(
                    4, 
                    "計算 sin(30°) 的值",
                    "A. 1/4", 
                    "B. 1/3", 
                    "C. 1/2", 
                    "D. 2/3",
                    "C"
                ),
                new MathQuestion(
                    5, 
                    "求 f(x) = 2x² + 3x - 5 的導數",
                    "A. f'(x) = 2x + 3", 
                    "B. f'(x) = 4x + 3", 
                    "C. f'(x) = 4x - 5", 
                    "D. f'(x) = 4x² + 3",
                    "B"
                )
            );
            
            mathQuestionRepository.saveAll(sampleQuestions);
        }
    }
}