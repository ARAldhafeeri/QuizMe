package com.QuizMe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuizMe.model.Question;
import com.QuizMe.repository.QuestionRepo;
import com.QuizMe.service.QuestionService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    
    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public Question createQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public Question getQuestion(Long id) {
        return questionRepo.getQuestion(id);
    }


    @Override
    public void updateQuestion(Question question) {
        questionRepo.updateQuestion(
            question.getId(), 
            question.getQuestion(), 
            question.getCorrectAnswer(), 
            question.getWrongAnswer1(), 
            question.getWrongAnswer2(), 
            question.getWrongAnswer3()
            );
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepo.findAll();
    }

}
