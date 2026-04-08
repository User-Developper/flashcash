package org.example.controller;

import org.example.model.Transfer;
import org.example.model.User;
import org.example.service.LinkService;
import org.example.service.SessionService;
import org.example.service.TransferService;
import org.example.service.UserService;
import org.example.service.form.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private final LinkService linkService;
    private final UserService userService;
    private final TransferService transferService;
    private final SessionService sessionService;

    public UserController(LinkService linkService, UserService userService, TransferService transferService, SessionService sessionService) {


        this.sessionService = sessionService;
        this.linkService = linkService;
        this.userService = userService;
        this.transferService = transferService;

    }


//    @GetMapping("/")
//    public ModelAndView home(Model model) {
//
//        User user = sessionService.sessionUser();
//        model.addAttribute("user", user);
//        List<Transfer> transactions = transferService.findTransactions();
//        model.addAttribute("transfers", transactions);
//        return new ModelAndView("index");
//    }

    @GetMapping("/signup")
    public ModelAndView signup(Model model) {
        SignupForm signupForm = new SignupForm();
        //model.addAttribute("signUpForm", new SignupForm());
        return new ModelAndView("signup", "signUpForm", signupForm);

    }

    @PostMapping("/signup")
    public ModelAndView createUser(@ModelAttribute("signUpForm") SignupForm signupForm) {
        userService.createUser(signupForm);
        return new ModelAndView("signin");
    }

    @GetMapping("/")
    public ModelAndView home(Model model){

        return new ModelAndView("home");

    }



}
