package com.ips.tpsi.pokemonwebapp.repository;


import com.ips.tpsi.pokemonwebapp.entity.PokemonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonCharacterRepository extends JpaRepository<PokemonCharacter, Integer> {
    List<PokemonCharacter> findByPokemonNameContainingIgnoreCase(String pokemonName);
}

