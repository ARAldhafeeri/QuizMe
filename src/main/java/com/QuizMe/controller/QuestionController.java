package com.QuizMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.QuizMe.model.Question;
import com.QuizMe.model.User;
import com.QuizMe.service.QuestionService;
import com.QuizMe.service.UserService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/question")
public class QuestionController {

            
    @Autowired
    private QuestionService questionService;

        @PostMapping("/create")
        public RedirectView question(

            Model model, 
            @ModelAttribute("question") Question question,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
            System.out.println("question: " + question.toString());
            
            question.setQuizId((Long) session.getAttribute("quiz"));

            questionService.createQuestion(question);
            
            return new RedirectView("/question/home");
        }

        @GetMapping("/home")
        public String home() {
            return "question";
        }


    
}
