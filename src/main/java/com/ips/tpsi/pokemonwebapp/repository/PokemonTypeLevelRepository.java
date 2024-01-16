package com.ips.tpsi.pokemonwebapp.repository;


import com.ips.tpsi.pokemonwebapp.entity.PokemonCharacter;
import com.ips.tpsi.pokemonwebapp.entity.PokemonTypeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PokemonTypeLevelRepository extends JpaRepository<PokemonTypeLevel, Integer> {
}



