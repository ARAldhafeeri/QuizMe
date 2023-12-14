package com.QuizMe.controller;

import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestBody;

import com.QuizMe.model.Question;
import com.QuizMe.model.Quiz;
import com.QuizMe.model.User;
import com.QuizMe.service.QuestionService;
import com.QuizMe.service.QuizService;
import com.QuizMe.service.UserService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/quiz")
public class QuizController {

            
    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;


        @PostMapping("/create")
        public RedirectView quiz(

            Model model, 
            @ModelAttribute("quiz") Quiz quiz,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

            quizService.createQuiz(quiz);
            
            Long id = quiz.getId();
            session.setAttribute("quiz", id);

            return new RedirectView("/question/home");
        }

        @GetMapping("/home")
        public String home() {
            return "quiz";
        }

        @GetMapping("/review")
        public String takeQuiz(

            Model model, 
            HttpSession session,
            RedirectAttributes redirectAttributes
            ) {
            Long id = (Long) session.getAttribute("quiz");
            
            Quiz quiz =   quizService.getQuiz(id);
            List<Question> questions = questionService.getQuestionsByQuizId(id);
            
            model.addAttribute("quiz", quiz);
            model.addAttribute("questions", questions);

            return "review";
            
        }

        @GetMapping("/take")
        public String takeQuiz(

            Model model, 
            @RequestParam("id") Long id,
            HttpSession session,
            RedirectAttributes redirectAttributes
            ) {
            
            Quiz quiz =   quizService.getQuiz(id);
            List<Question> questions = questionService.getQuestionsByQuizId(id);
            
            String form = quizService.generateQuiz(questions);

             model.addAttribute("quiz", quiz);
             model.addAttribute("form", form);
            return "take";
            
        }

        @PostMapping("/submit")
        public String submitQuiz(
                Model model, 
                @RequestParam java.util.Map<String, String> answers,
                HttpSession session,
                RedirectAttributes redirectAttributes
                ) {
                
                int score = 0;
                int total = 0;
                System.out.println(answers);
                for (String key : answers.keySet()) {
                    Long id = Long.parseLong(key);
                    Question question = questionService.getQuestion(id);
                    String answer = answers.get(key);
                    if (answer.equals(question.getCorrectAnswer())) {
                        score++;
                    }
                    total++;
                }
                

                redirectAttributes.addFlashAttribute("score", score);
                redirectAttributes.addFlashAttribute("total", total);
                return "redirect:/quiz/result";
                
            }

        @GetMapping("/result")
        public String result(

            Model model, 
            HttpSession session,
            RedirectAttributes redirectAttributes
            ) {
            
            return "result";
            
        }
    
}
