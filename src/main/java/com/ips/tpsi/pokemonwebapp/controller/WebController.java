package com.ips.tpsi.pokemonwebapp.controller;

import com.ips.tpsi.pokemonwebapp.bc.UsernameAlreadyExistsException;
import com.ips.tpsi.pokemonwebapp.bc.UsernameDoesntExistsException;
import com.ips.tpsi.pokemonwebapp.bc.WebBc;
import com.ips.tpsi.pokemonwebapp.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController {
    @Autowired
    WebBc bc;

    public WebController(WebBc bc) {
        this.bc = bc;
    }


    @GetMapping("/login")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping({"/name"})
    public ModelAndView getName() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("name", "Vania");
        return mv;
    }



    @GetMapping({"/success"})
    public ModelAndView loginSuccess() {
        ModelAndView mv = new ModelAndView("success");
        mv.addObject("message", "Welcome to the app!");
        return mv;
    }

    @GetMapping({"/aboutme"})
    public ModelAndView aboutme(){
        ModelAndView mv = new ModelAndView("aboutme");
        return mv;
    }

    @GetMapping({"/pokemonlist"})
    public ModelAndView pokemonlist(){
        ModelAndView mv = new ModelAndView("pokemonlist");
        return mv;
    }



    @GetMapping({"/username"})
    public ModelAndView getUserByUsername(String username) {
        User user = this.bc.getRepositoryUserInfoByUsername(username);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("user", user);
        return mv;
    }

    @GetMapping("/signup")
    public ModelAndView getSignUp() {
        ModelAndView mv = new ModelAndView("signup");
        return mv;
    }

    @PostMapping("/signup")
    public ModelAndView signUp(String username, String password, HttpServletResponse response) {

        try {

            bc.getRepositoryUserInfo(username, password);
            return new ModelAndView("redirect:/login");
        } catch (UsernameAlreadyExistsException e) {
            ModelAndView mv = new ModelAndView("signup");
            mv.addObject("error", e.getMessage());
            return mv;
        }
    }


    @PostMapping("/login")
    public ModelAndView login(String uname, String psw, HttpServletResponse response) {
        User user = bc.getRepositoryUserInfoByUsername(uname);
        ModelAndView mv = new ModelAndView("index");

        if (user != null) {
            boolean isLoginValid = bc.isLoginValid(uname, psw);
            if (isLoginValid) {
                mv.setViewName("redirect:/success");
            } else {
                // Add an error message if login is not valid
                mv.addObject("error", "Invalid username or password");
            }
        } else {
            // Add an error message if the user does not exist
            mv.addObject("error", "User does not exist");
        }
        return mv;
    }


}


