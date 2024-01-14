package com.ips.tpsi.pokemonwebapp.controller;

import com.ips.tpsi.pokemonwebapp.bc.UsernameAlreadyExistsException;
import com.ips.tpsi.pokemonwebapp.bc.UsernameDoesntExistsException;
import com.ips.tpsi.pokemonwebapp.bc.WebBc;
import com.ips.tpsi.pokemonwebapp.entity.PokemonCharacter;
import com.ips.tpsi.pokemonwebapp.entity.PokemonTypeLevel;
import com.ips.tpsi.pokemonwebapp.entity.User;
import com.ips.tpsi.pokemonwebapp.repository.PokemonCharacterRepository;

import com.ips.tpsi.pokemonwebapp.repository.PokemonTypeLevelRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


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


    @GetMapping({"/username"})
    public ModelAndView getUserByUsername(String username) {
        User user = this.bc.getRepositoryUserInfoByUsername(username);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("user", user);
        return mv;
    }


    @Autowired
    PokemonCharacterRepository pokemonRepository;

    @Autowired
    PokemonTypeLevelRepository pokemonTypeLevelRepository;


    @GetMapping("/pokemonlist")
    public ModelAndView getPokemonList() {
        List<PokemonCharacter> pokemons = pokemonRepository.findAll();
        ModelAndView mv = new ModelAndView("pokemonlist");
        mv.addObject("pokemons", pokemons);
        mv.addObject("deleteSuccess", false);
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit() {
        ModelAndView mv = new ModelAndView("edit");
        return mv;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deletePokemonById(@PathVariable("id") int id) {
        Optional<PokemonCharacter> pokemonOptional = pokemonRepository.findById(id);
        if (pokemonOptional.isPresent()) {
            pokemonTypeLevelRepository.deleteById(id);
            pokemonRepository.deleteById(id);
            return new ModelAndView("redirect:/pokemonlist").addObject("deleteSuccess", true);
        } else {
            return new ModelAndView("redirect:/pokemonlist").addObject("deleteError", "Pokemon not found");
        }
    }


    /*
    @GetMapping("/delete/{id}")
    public ModelAndView deletePokemonById(@PathVariable("id") int id) {
        Optional<PokemonCharacter> pokemonOptional = pokemonRepository.findById(id);
        if (pokemonOptional.isPresent()) {
            List<PokemonTypeLevel> pokemonTypeLevels = pokemonTypeLevelRepository.findByPokemonCharacterFKId(id);
            for (PokemonTypeLevel pokemonTypeLevel : pokemonTypeLevels) {
                pokemonTypeLevelRepository.delete(pokemonTypeLevel);
            }
            pokemonRepository.deleteById(id);
            return new ModelAndView("redirect:/pokemonlist").addObject("deleteSuccess", true);
        } else {
            return new ModelAndView("redirect:/pokemonlist").addObject("deleteError", "Pokemon not found");
        }
    }
    */
















}


