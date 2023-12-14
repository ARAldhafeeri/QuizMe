package com.QuizMe.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuizMe.model.Question;
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
            quiz.getQuizDescription()
            );
    }

    @Override
    public List<Quiz> getQuizs() {
        return quizRepo.findAll();
    }

    @Override
    public String generateQuiz(List<Question> questions) {
        // generate form submits to /quiz/submit 
        // of question text followed by answers as radio buttons
        // in random order
        // with a submit button
        String form = "";

        for (Question question : questions) {
            form += "<form action=\"/quiz/submit\" method=\"post\">";
            form += "<h1 class = \"card-header\">" + question.getName() + "</h1>";            // add answers in random order
            List <String> answers = Arrays.asList(
                question.getCorrectAnswer(),
                question.getWrongAnswer1(),
                question.getWrongAnswer2(),
                question.getWrongAnswer3()
            );

            Collections.shuffle(answers);

            for (String answer : answers) {
                form += "<fieldset id=\"" + question.getId() + "\">";
                form += "<input type=\"radio\" id=\"" + answer + "\" name= \"" + question.getId() + "\" value=\"" + answer + "\">"; 
                form += "<label for=\"" + answer + "\">" + answer + "</label><br>";
                form += "</fieldset>";
                form += "</br>";
            }
            
        }

        form += "<button type=\"submit\">Submit</button>";
        form += "</form>";

        return form;

    }

}
