package com.ips.tpsi.pokemonwebapp.repository;

import com.ips.tpsi.pokemonwebapp.entity.PokemonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonCharacterRepository extends JpaRepository<PokemonCharacter, Integer> {

    List<PokemonCharacter> findByPokemonNameContainingIgnoreCase(String pokemonName);

    @Query("SELECT p FROM PokemonCharacter p WHERE p.isDisabled = false")
    List<PokemonCharacter> findActivePokemon();

    @Query("SELECT p FROM PokemonCharacter p WHERE p.isDisabled = true")
    List<PokemonCharacter> findInactivePokemon();
}
