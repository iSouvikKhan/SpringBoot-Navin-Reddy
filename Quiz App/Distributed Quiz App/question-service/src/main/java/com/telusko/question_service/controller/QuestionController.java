package com.telusko.question_service.controller;


import com.telusko.question_service.dto.QuestionWrapper;
import com.telusko.question_service.dto.QuizResponse;
import com.telusko.question_service.model.Question;
import com.telusko.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

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

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQ) {
        try {
            List<Integer> ans = questionService.getQuestionsForQuiz(categoryName, numQ);
            if(ans != null) return new ResponseEntity<>(ans, HttpStatus.OK);
            else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        try {
            List<QuestionWrapper> ans = questionService.getQuestionsFromId(questionIds);
            if(ans != null) return new ResponseEntity<>(ans, HttpStatus.OK);
            else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

//            System.out.println(environment.getProperty("local.server.port"));

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses) {
        try {
            Integer ans = questionService.getScore(responses);
            if(ans != null) return new ResponseEntity<>(ans, HttpStatus.OK);
            else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
