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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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




    @Autowired
    PokemonCharacterRepository pokemonRepository;
    @Autowired
    PokemonTypeLevelRepository pokemonTypeLevelRepository;






    @GetMapping("/edit/{id}")
    public ModelAndView getEditForm(@PathVariable Integer id) {
        PokemonCharacter pokemonToEdit = bc.getPokemonById(id);
        ModelAndView mv = new ModelAndView("edit");
        mv.addObject("editedPokemon", pokemonToEdit);
        return mv;
    }


    @PostMapping("/edit")
    public String editPokemonCharacter(@ModelAttribute PokemonCharacter editedPokemon, Model model) {
        Optional<PokemonCharacter> optionalPokemonCharacter = pokemonRepository.findById(editedPokemon.getPokemonId());

        if (optionalPokemonCharacter.isPresent()) {
            PokemonCharacter existingPokemonCharacter = optionalPokemonCharacter.get();
            existingPokemonCharacter.setPokemonName(editedPokemon.getPokemonName());
            existingPokemonCharacter.setPokemonTotal(editedPokemon.getPokemonTotal());
            existingPokemonCharacter.setPokemonHp(editedPokemon.getPokemonHp());
            existingPokemonCharacter.setPokemonAttack(editedPokemon.getPokemonAttack());
            existingPokemonCharacter.setPokemonDefense(editedPokemon.getPokemonDefense());
            existingPokemonCharacter.setPokemonSp_atk(editedPokemon.getPokemonSp_atk());
            existingPokemonCharacter.setPokemonSp_def(editedPokemon.getPokemonSp_def());
            existingPokemonCharacter.setPokemonSpeed(editedPokemon.getPokemonSpeed());
            existingPokemonCharacter.setPokemonGeneration(editedPokemon.getPokemonGeneration());
            existingPokemonCharacter.setPokemonLegendary(editedPokemon.getPokemonLegendary());

            pokemonRepository.save(existingPokemonCharacter);
        } else {
            // handle the case where the id doesn't exist in the database
            // e.g., return an error message or redirect to an error page
        }

        return "redirect:/pokemonlist";
    }

    @PostMapping("/delete")
    public String deletePokemonCharacter(@ModelAttribute PokemonCharacter pokemonToDelete) {
        pokemonRepository.deleteById(pokemonToDelete.getPokemonId());
        return "redirect:/pokemonlist?deleteSuccess=true";
    }

    @GetMapping("/pokemonlist")
    public ModelAndView getPokemonList(@RequestParam(name = "deleteSuccess", required = false, defaultValue = "false") boolean deleteSuccess) {
        List<PokemonCharacter> pokemons = pokemonRepository.findAll();
        ModelAndView mv = new ModelAndView("pokemonlist");
        mv.addObject("pokemons", pokemons);
        mv.addObject("deleteSuccess", deleteSuccess);
        return mv;
    }






}


