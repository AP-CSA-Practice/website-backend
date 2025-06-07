package com.apcsa.practice.controller;

import com.apcsa.practice.model.MathQuestion;
import com.apcsa.practice.service.MathQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/math")
@CrossOrigin(origins = "http://localhost:3000")
public class MathQuestionController {
    
    private final MathQuestionService mathQuestionService;
    
    @Autowired
    public MathQuestionController(MathQuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
        
        // 初始化一些示例題目
        mathQuestionService.initSampleQuestions();
    }
    
    // 獲取所有數學題目
    @GetMapping
    public ResponseEntity<List<MathQuestion>> getAllMathQuestions() {
        List<MathQuestion> questions = mathQuestionService.findAllMathQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    
    // 根據ID獲取數學題目
    @GetMapping("/{id}")
    public ResponseEntity<MathQuestion> getMathQuestionById(@PathVariable String id) {
        Optional<MathQuestion> question = mathQuestionService.findMathQuestionById(id);
        return question.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // 獲取隨機數學題目
    @GetMapping("/random")
    public ResponseEntity<MathQuestion> getRandomMathQuestion() {
        MathQuestion question = mathQuestionService.getRandomMathQuestion();
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // 創建新數學題目
    @PostMapping
    public ResponseEntity<MathQuestion> createMathQuestion(@RequestBody MathQuestion mathQuestion) {
        MathQuestion savedQuestion = mathQuestionService.saveMathQuestion(mathQuestion);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }
    
    // 更新數學題目
    @PutMapping("/{id}")
    public ResponseEntity<MathQuestion> updateMathQuestion(@PathVariable String id, @RequestBody MathQuestion mathQuestion) {
        Optional<MathQuestion> existingQuestion = mathQuestionService.findMathQuestionById(id);
        if (existingQuestion.isPresent()) {
            mathQuestion.setId(id);
            MathQuestion updatedQuestion = mathQuestionService.updateMathQuestion(mathQuestion);
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // 刪除數學題目
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMathQuestion(@PathVariable String id) {
        Optional<MathQuestion> question = mathQuestionService.findMathQuestionById(id);
        if (question.isPresent()) {
            mathQuestionService.deleteMathQuestion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}