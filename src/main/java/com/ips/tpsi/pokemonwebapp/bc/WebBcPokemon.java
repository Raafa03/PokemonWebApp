package com.ips.tpsi.pokemonwebapp.bc;
/*
import com.ips.tpsi.pokemonwebapp.entity.PokemonCharacter;
import com.ips.tpsi.pokemonwebapp.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// a anotação Service serve para definir serviços, neste caso o nosso BC que é onde está a componente de lógica de negócio

@Service
public class WebBcPokemon {

    @Autowired
    PokemonRepository repository;



    public void getRepositoryPokemonInfoEdit(Integer pokemonId, String pokemonName, Integer pokemonTotal, Integer pokemonHp, Integer pokemonAttack, Integer pokemonDefense, Integer pokemonSp_atk, Integer pokemonSp_def, Integer pokemonSpeed, Integer pokemonGeneration, String pokemonLegendary) {
        editPokemon(pokemonId, pokemonName, pokemonTotal, pokemonHp, pokemonAttack, pokemonDefense, pokemonSp_atk, pokemonSp_def, pokemonSpeed, pokemonGeneration, pokemonLegendary);
    }





    public void editPokemon(Integer pokemonId, String pokemonName, Integer pokemonTotal, Integer pokemonHp, Integer pokemonAttack, Integer pokemonDefense, Integer pokemonSp_atk, Integer pokemonSp_def, Integer pokemonSpeed, Integer pokemonGeneration, String pokemonLegendary) {

        if (repository.findPokemonById(pokemonId) != null) {
            PokemonCharacter updatedPokemon = new PokemonCharacter();
            updatedPokemon.setPokemonName(pokemonName);
            updatedPokemon.setPokemonTotal(pokemonTotal);
            updatedPokemon.setPokemonHp(pokemonHp);
            updatedPokemon.setPokemonAttack(pokemonAttack);
            updatedPokemon.setPokemonDefense(pokemonDefense);
            updatedPokemon.setPokemonSp_atk(pokemonSp_atk);
            updatedPokemon.setPokemonSp_def(pokemonSp_def);
            updatedPokemon.setPokemonSpeed(pokemonSpeed);
            updatedPokemon.setPokemonGeneration(pokemonGeneration);
            updatedPokemon.setPokemonLegendary(pokemonLegendary);

            repository.save(updatedPokemon);
        }
    }

    public PokemonCharacter getRepositoryPokemonInfoById(Integer pokemonId) {
        return repository.findPokemonById(pokemonId);
    }

}

*/

