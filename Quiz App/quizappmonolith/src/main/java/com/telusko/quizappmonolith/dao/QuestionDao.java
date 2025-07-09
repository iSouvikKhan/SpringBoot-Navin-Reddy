package com.telusko.quizappmonolith.dao;

import com.telusko.quizappmonolith.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
}
