package com.telusko.quiz_service.service;

import com.telusko.quiz_service.dao.QuizDao;
import com.telusko.quiz_service.dto.QuestionWrapper;
import com.telusko.quiz_service.dto.QuizResponse;
import com.telusko.quiz_service.feign.QuizInterface;
import com.telusko.quiz_service.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public Quiz createQuiz(String category, Integer numQ, String title) {
        try {
            // call generate url
            List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questions);

            return quizDao.save(quiz);
        } catch(Exception ex) {
            return null;
        }
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        try {
            Quiz quiz = quizDao.findById(id).get();
            List<Integer> questionIds = quiz.getQuestionIds();
            ResponseEntity<List<QuestionWrapper>> questionWrapper = quizInterface.getQuestionsFromId(questionIds);
            var asd = questionWrapper.getBody();
            return asd;
        } catch (Exception ex) {
            return null;
        }
    }

    public Integer calculateResult(Integer id, List<QuizResponse> quizResponse) {
        try {
            return quizInterface.getScore(quizResponse).getBody();
        } catch (Exception ex) {
            return null;
        }
    }
}
