package com.telusko.question_service.service;

import com.telusko.question_service.dao.QuestionDao;
import com.telusko.question_service.dto.QuestionWrapper;
import com.telusko.question_service.dto.QuizResponse;
import com.telusko.question_service.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Integer> getQuestionsForQuiz(String categoryName, Integer numQ) {
        try {
            List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQ);
            return questions;
        } catch(Exception ex) {
            return null;
        }
    }

    public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for(Integer id : questionIds) {
            questions.add(questionDao.findById(id).get());
        }
        for(Question question : questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return wrappers;
    }

    public Integer getScore(List<QuizResponse> quizResponse) {
        try {
            int right = 0;
            for(QuizResponse res : quizResponse) {
                Question question = questionDao.findById(res.getId()).get();
                if(res.getQuizResponse().equals(question.getRightAnswer())) right++;
            }
            return right;
        } catch (Exception ex) {
            return null;
        }
    }
}
