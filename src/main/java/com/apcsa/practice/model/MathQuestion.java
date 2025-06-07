package com.apcsa.practice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "math_questions")
public class MathQuestion {
    
    @Id
    private String id;
    private int questionNumber;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer; // 可以是 "A", "B", "C", "D"
    
    // 無參構造函數
    public MathQuestion() {
    }
    
    // 帶參數的構造函數
    public MathQuestion(int questionNumber, String questionText, String optionA, String optionB, 
                        String optionC, String optionD, String correctAnswer) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public int getQuestionNumber() {
        return questionNumber;
    }
    
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
    
    public String getQuestionText() {
        return questionText;
    }
    
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    
    public String getOptionA() {
        return optionA;
    }
    
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }
    
    public String getOptionB() {
        return optionB;
    }
    
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }
    
    public String getOptionC() {
        return optionC;
    }
    
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    
    public String getOptionD() {
        return optionD;
    }
    
    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
    
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
    @Override
    public String toString() {
        return "MathQuestion{" +
                "id='" + id + '\'' +
                ", questionNumber=" + questionNumber +
                ", questionText='" + questionText + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}