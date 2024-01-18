package com.ips.tpsi.pokemonwebapp.repository;


import com.ips.tpsi.pokemonwebapp.entity.PokemonCharacter;
import com.ips.tpsi.pokemonwebapp.entity.PokemonTypeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PokemonTypeLevelRepository extends JpaRepository<PokemonTypeLevel, Integer> {

    List<PokemonTypeLevel> findAllPokemonTypeLevelByPokemonCharacterFK(PokemonCharacter pokemonCharacterFK);


}



