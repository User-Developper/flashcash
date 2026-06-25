package org.example.controller;

import org.example.model.User;
import org.example.repository.ReportRepository;
import org.example.repository.SignalRepository;
import org.example.repository.SignalRepository;
import org.example.service.DataInitializer;
import org.example.service.SessionService;
import org.example.service.SignalService;
import org.example.service.UserService;
import org.example.service.form.ReportForm;
import org.example.service.form.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;
    private final SignalService signalService;
    private final ReportRepository reportRepository;


    @GetMapping("/debug")
    @ResponseBody
    public String debug() {
        return "reports=" + reportRepository.count();
    }

    public UserController(SignalService signalService, UserService userService, SessionService sessionService, ReportRepository reportRepository) {

        this.signalService = signalService;
        this.sessionService = sessionService;
        this.userService = userService;

        this.reportRepository = reportRepository;

    }

    @Autowired
    SignalRepository signalRepository;


//    @GetMapping("/")
//    public ModelAndView home(Model model) {
//
//        User user = sessionService.sessionUser();
//        model.addAttribute("user", user);
//
//
////        List<Integer> data = List.of(5, 10, 15, 8, 20, 12);
////
////        model.addAttribute("chartData", data);
//
//        model.addAttribute("chartData", signalService.getDataByMonth());
//
//        return new ModelAndView("home");
//    }

@GetMapping("/")
public ModelAndView home(Model model) {

//    User user = sessionService.sessionUser();
//    model.addAttribute("user", user);

    User user = sessionService.sessionUser();

    if (user == null) {
        user = new User();
    }

    model.addAttribute("user", user);

    model.addAttribute("chartData", signalService.getDataByMonth(2025));
    model.addAttribute("chartDay", signalService.getDataByDay(2025));
    model.addAttribute("chartYearData", signalService.getReportsByYear(2025));
    model.addAttribute("reports", signalService.getReportsPaca());

    System.out.println("MONTH DATA = " + signalService.getDataByMonth(2025));
    System.out.println("DAY DATA = " + signalService.getDataByDay(2025));
    System.out.println("YEAR DATA = " + signalService.getReportsByYear(2025));

    return new ModelAndView("home");
}

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

//    @PostMapping("/report")
//    public ModelAndView createReport(@ModelAttribute("reportForm")ReportForm reportForm) {
//
//        signalService.createReport(reportForm);
//        return new ModelAndView("report");
//
//    }

//    @GetMapping("/")
//    public ModelAndView home(Model model){
//
//        return new ModelAndView("home");
//
//    }

//    @GetMapping("/transfer")
//    public ModelAndView transferMoney(Model model){
//
//        User user = sessionService.sessionUser();
//        model.addAttribute("user",user);
//
//        TransferForm transferForm = new TransferForm();
//        model.addAttribute("transferForm", transferForm);
//
//        return new ModelAndView("transfer");
//
//    }

    @GetMapping("/logout")
    public ModelAndView deconnect(Model model){

        User user = sessionService.sessionUser();



        return new ModelAndView("home");
    }

//    @GetMapping("add-to-flashcahs")
//    public ModelAndView addToFlashCash(Model model){
//
//        AddToFlashCashForm addToFlashCashForm = new AddToFlashCashForm();
//        model.addAttribute("addToFlashCash", addToFlashCashForm);
//
//        return new ModelAndView("addToFlashCash");
//
//    }


//    @PostMapping("add-to-flashcash")
//    public  ModelAndView transferCashToAccount(Model model, @ModelAttribute("addToFlashForm") AddToFlashCashForm form){
//
//        transferService.transferToAccount(form);
//        User user = sessionService.sessionUser();
//        model.addAttribute("user", user);
//        return new ModelAndView("transfer");
//
//    }


}
