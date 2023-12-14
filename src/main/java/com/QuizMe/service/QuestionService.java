package com.QuizMe.service;

import com.QuizMe.model.Question;

import java.util.List;

public interface QuestionService {
    public Question createQuestion(Question question);
    public Question getQuestion(Long id);
    public List<Question> getQuestions();
    public void updateQuestion(Question question);
    public List<Question> getQuestionsByQuizId(Long quizId);

}
