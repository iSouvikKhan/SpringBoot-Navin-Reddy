package com.telusko.quizappmonolith.controller;

import com.telusko.quizappmonolith.dto.QuestionWrapper;
import com.telusko.quizappmonolith.dto.QuizResponse;
import com.telusko.quizappmonolith.model.Quiz;
import com.telusko.quizappmonolith.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title) {
        try {
            Quiz quiz = quizService.createQuiz(category, numQ, title);
            if(quiz != null) return new ResponseEntity<>("Quiz created successfully", HttpStatus.OK);
            else return new ResponseEntity<>("Error occurred while creating quiz", HttpStatus.BAD_REQUEST);
        } catch(Exception ex) {
            return new ResponseEntity<>("Error occurred while creating quiz", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        try {
            List<QuestionWrapper> ans = quizService.getQuizQuestions(id);
            if(ans != null) return new ResponseEntity<>(ans, HttpStatus.OK);
            else return new ResponseEntity<>(new ArrayList<QuestionWrapper>(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ArrayList<QuestionWrapper>(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponse> quizResponse) {
        try {
            Integer ans = quizService.calculateResult(id, quizResponse);
            if(ans != null) return new ResponseEntity<>(ans, HttpStatus.OK);
            else return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
    }
}
