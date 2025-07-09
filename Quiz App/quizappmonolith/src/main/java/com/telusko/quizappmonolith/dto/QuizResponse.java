package com.telusko.quizappmonolith.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizResponse {
    private Integer id;
    private String quizResponse;
}
