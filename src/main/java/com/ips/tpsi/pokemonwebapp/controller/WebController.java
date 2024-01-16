package com.ips.tpsi.pokemonwebapp.controller;

import com.ips.tpsi.pokemonwebapp.Exceptions.UsernameAlreadyExistsException;
import com.ips.tpsi.pokemonwebapp.bc.WebBc;
import com.ips.tpsi.pokemonwebapp.entity.PokemonCharacter;
import com.ips.tpsi.pokemonwebapp.repository.PokemonCharacterRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import java.util.List;


@Controller
public class WebController {

    @Autowired
    WebBc bc;

    @Autowired
    private PokemonCharacterRepository pokemonCharacterRepository;

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("index");
    }


    @GetMapping("/success")
    public ModelAndView loginSuccess() {
        ModelAndView mv = new ModelAndView("success");
        mv.addObject("message", "Welcome to the app!");
        return mv;
    }

    @GetMapping("/aboutme")
    public ModelAndView aboutme() {
        return new ModelAndView("aboutme");
    }

    @GetMapping("/signup")
    public ModelAndView getSignUp() {
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView signUp(String username, String password, HttpServletResponse response) {
        try {
            bc.signUpUser(username, password);
            return new ModelAndView("redirect:/login");
        } catch (UsernameAlreadyExistsException e) {
            ModelAndView mv = new ModelAndView("signup");
            mv.addObject("error", e.getMessage());
            return mv;
        }
    }

    @PostMapping("/login")
    public ModelAndView login(String uname, String psw, HttpServletResponse response) {
        try {
            bc.validateLogin(uname, psw);
            return new ModelAndView("redirect:/success");
        } catch (LoginException e) {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("error", e.getMessage());
            return mv;
        }
    }


    @GetMapping("/edit/{id}")
    public ModelAndView getEditForm(@PathVariable Integer id) {
        PokemonCharacter pokemonToEdit = bc.getPokemonById(id);
        ModelAndView mv = new ModelAndView("edit");
        mv.addObject("editedPokemon", pokemonToEdit);
        return mv;
    }

    @PostMapping("/edit")
    public String editPokemonCharacter(@ModelAttribute PokemonCharacter editedPokemon, Model model) {
        bc.editPokemonCharacter(editedPokemon);
        return "redirect:/pokemonlist";
    }

    @PostMapping("/delete")
    public String deletePokemonCharacter(@ModelAttribute PokemonCharacter pokemonToDelete) {
        bc.deletePokemonCharacter(pokemonToDelete.getPokemonId());
        return "redirect:/pokemonlist?deleteSuccess=true";
    }

    /*
    @GetMapping("/pokemonlist")
    public ModelAndView getPokemonList(@RequestParam(name = "deleteSuccess", required = false, defaultValue = "false") boolean deleteSuccess) {
        List<PokemonCharacter> pokemons = pokemonRepository.findAll();
        ModelAndView mv = new ModelAndView("pokemonlist");
        mv.addObject("pokemons", pokemons);
        mv.addObject("deleteSuccess", deleteSuccess);
        return mv;
    }
     */

    @GetMapping("/pokemonlist")
    public ModelAndView getAllPokemons(@RequestParam(name = "deleteSuccess", required = false, defaultValue = "false") boolean deleteSuccess,
                                       @RequestParam(name = "searchName", required = false) String searchName) {
        List<PokemonCharacter> pokemons = bc.getAllPokemons(searchName);
        ModelAndView mv = new ModelAndView("pokemonlist");
        mv.addObject("pokemons", pokemons);
        mv.addObject("deleteSuccess", deleteSuccess);
        mv.addObject("searchName", searchName); // Passa o nome de pesquisa para manter o valor no formulário
        return mv;
    }

    @GetMapping("/addPokemon")
    public ModelAndView getAddPokemonForm() {
        return new ModelAndView("addPokemon");
    }

    @PostMapping("/addPokemon")
    public ModelAndView addPokemon(String pokemonName, Integer pokemonTotal, Integer pokemonHp,
                                   Integer pokemonAttack, Integer pokemonDefense, Integer pokemonSp_atk,
                                   Integer pokemonSp_def, Integer pokemonSpeed, Integer pokemonGeneration,
                                   String pokemonLegendary) {
        bc.addPokemon(pokemonName, pokemonTotal, pokemonHp, pokemonAttack, pokemonDefense, pokemonSp_atk,
                pokemonSp_def, pokemonSpeed, pokemonGeneration, pokemonLegendary);
        return new ModelAndView("redirect:/pokemonlist");
    }
}
