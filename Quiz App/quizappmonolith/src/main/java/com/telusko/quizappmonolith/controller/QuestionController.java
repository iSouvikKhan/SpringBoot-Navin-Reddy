package com.telusko.quizappmonolith.controller;

import com.telusko.quizappmonolith.model.Question;
import com.telusko.quizappmonolith.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestion(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        try {
            Question ques = questionService.addQuestion(question);
            if(ques != null) return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
            else return new ResponseEntity<>("Failed to add question", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add question", HttpStatus.BAD_REQUEST);
        }
    }
}
