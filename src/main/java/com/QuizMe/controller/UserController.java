package com.QuizMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.QuizMe.model.User;
import com.QuizMe.service.UserService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

            
    @Autowired
    private UserService userService;

        @PostMapping("/create")
        public RedirectView createUser(Model model, 
        @ModelAttribute("user") User user,
        HttpSession session) {

            user = userService.createUser(user);
            session.setAttribute("user", user.getId());
            
            return new RedirectView("/quiz/home");

        }

        @GetMapping("/home")
        public String home() {
            System.out.println("hello");
            return "home";
        }
    
}
