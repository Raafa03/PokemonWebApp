package com.ips.tpsi.pokemonwebapp.repository;


import com.ips.tpsi.pokemonwebapp.entity.PokemonTypeLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PokemonTypeLevelRepository extends JpaRepository<PokemonTypeLevel, Integer> {


    /*
    List<PokemonTypeLevel> findByPokemonCharacterFKId(Integer id);
    */

}



