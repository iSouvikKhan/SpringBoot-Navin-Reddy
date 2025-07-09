package com.telusko.quizappmonolith.service;

import com.telusko.quizappmonolith.dao.QuestionDao;
import com.telusko.quizappmonolith.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategoryIgnoreCase(category);
    }

    public Question addQuestion(Question question) {
        try {
            return questionDao.save(question);
        } catch (Exception ex) {
            return null;
        }
    }
}
