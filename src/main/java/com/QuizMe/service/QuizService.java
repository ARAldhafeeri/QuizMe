package com.QuizMe.service;

import com.QuizMe.model.Quiz;

import java.util.List;

public interface QuizService {
    public Quiz createQuiz(Quiz quiz);
    public Quiz getQuiz(Long id);
    public List<Quiz> getQuizs();
    public void updateQuiz(Quiz quiz);
}
