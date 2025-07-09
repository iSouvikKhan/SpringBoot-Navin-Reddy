package com.telusko.quizappmonolith.service;

import com.telusko.quizappmonolith.dao.QuestionDao;
import com.telusko.quizappmonolith.dao.QuizDao;
import com.telusko.quizappmonolith.dto.QuestionWrapper;
import com.telusko.quizappmonolith.dto.QuizResponse;
import com.telusko.quizappmonolith.model.Question;
import com.telusko.quizappmonolith.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    QuestionDao questionDao;

    public Quiz createQuiz(String category, Integer numQ, String title) {
        try {
            List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);

            return quizDao.save(quiz);
        } catch(Exception ex) {
            return null;
        }
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> question = quiz.get().getQuestions();
            List<QuestionWrapper> questionWrapper = new ArrayList<>();
            for(Question qs : question) {
                QuestionWrapper qw = new QuestionWrapper(qs.getId(), qs.getQuestionTitle(), qs.getOption1(), qs.getOption2(), qs.getOption3(), qs.getOption4());
                questionWrapper.add(qw);
            }
            return questionWrapper;
        } catch (Exception ex) {
            return null;
        }
    }

    public Integer calculateResult(Integer id, List<QuizResponse> quizResponse) {
        try {
            Quiz quiz = quizDao.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int right = 0;
            int i = 0;
            for(QuizResponse res : quizResponse) {
                if(res.getQuizResponse().equals(questions.get(i).getRightAnswer())) right++;
                i++;
            }
            return right;
        } catch (Exception ex) {
            return null;
        }
    }
}
