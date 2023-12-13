package com.QuizMe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuizMe.model.Quiz;
import com.QuizMe.repository.QuizRepo;
import com.QuizMe.service.QuizService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {
    
    @Autowired
    private QuizRepo quizRepo;

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Override
    public Quiz getQuiz(Long id) {
        return quizRepo.getQuiz(id);
    }


    @Override
    public void updateQuiz(Quiz quiz) {
        quizRepo.updateQuiz(
            quiz.getId(), 
            quiz.getQuizName(), 
            quiz.getQuizDescription(),
            quiz.getQuestions()
            );
    }

    @Override
    public List<Quiz> getQuizs() {
        return quizRepo.findAll();
    }

}
