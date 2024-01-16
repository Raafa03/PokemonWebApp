package com.ips.tpsi.pokemonwebapp.bc;

import com.ips.tpsi.pokemonwebapp.Exceptions.PokemonNotFoundException;
import com.ips.tpsi.pokemonwebapp.Exceptions.UsernameAlreadyExistsException;
import com.ips.tpsi.pokemonwebapp.entity.PokemonCharacter;
import com.ips.tpsi.pokemonwebapp.entity.User;
import com.ips.tpsi.pokemonwebapp.repository.PokemonCharacterRepository;
import com.ips.tpsi.pokemonwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

@Service
public class WebBc {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PokemonCharacterRepository pokemonRepository;

    public void signUpUser(String username, String password) {
        if (userRepository.findUserByUsername(username) != null) {
            throw new UsernameAlreadyExistsException("O nome de usuário já existe.");
        } else {
            saveNewUser(username, password);
        }
    }

    private void saveNewUser(String username, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);
    }

    public void validateLogin(String username, String password) throws LoginException {
        User user = userRepository.findUserByUsername(username);

        if (user != null) {
            if (!isLoginValid(user, password)) {
                throw new LoginException("Invalid username or password");
            }
        } else {
            throw new LoginException("User does not exist");
        }
    }

    private boolean isLoginValid(User user, String password) {
        return password.equals(user.getPassword());
    }

    public void editPokemonCharacter(PokemonCharacter editedPokemon) {
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
            throw new PokemonNotFoundException("Pokemon with ID " + editedPokemon.getPokemonId() + " not found");
        }
    }


    public void deletePokemonCharacter(Integer pokemonId) {
        pokemonRepository.deleteById(pokemonId);
    }

    public PokemonCharacter getPokemonById(Integer id) {
        Optional<PokemonCharacter> optionalPokemon = pokemonRepository.findById(id);
        return optionalPokemon.orElse(null);
    }

    public List<PokemonCharacter> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    public void addPokemon(String pokemonName, Integer pokemonTotal, Integer pokemonHp,
                           Integer pokemonAttack, Integer pokemonDefense, Integer pokemonSp_atk,
                           Integer pokemonSp_def, Integer pokemonSpeed, Integer pokemonGeneration,
                           String pokemonLegendary) {
        PokemonCharacter newPokemon = new PokemonCharacter();
        newPokemon.setPokemonName(pokemonName);
        newPokemon.setPokemonTotal(pokemonTotal);
        newPokemon.setPokemonHp(pokemonHp);
        newPokemon.setPokemonAttack(pokemonAttack);
        newPokemon.setPokemonDefense(pokemonDefense);
        newPokemon.setPokemonSp_atk(pokemonSp_atk);
        newPokemon.setPokemonSp_def(pokemonSp_def);
        newPokemon.setPokemonSpeed(pokemonSpeed);
        newPokemon.setPokemonGeneration(pokemonGeneration);
        newPokemon.setPokemonLegendary(pokemonLegendary);


        pokemonRepository.save(newPokemon);
    }

}
