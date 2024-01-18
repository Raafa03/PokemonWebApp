package com.ips.tpsi.pokemonwebapp.repository;


import com.ips.tpsi.pokemonwebapp.entity.PokemonCharacter;

import com.ips.tpsi.pokemonwebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonCharacterRepository extends JpaRepository<PokemonCharacter, Integer> {

    @Query("SELECT p, ptl1, pt1, ptl2, pt2 " +
            "FROM PokemonCharacter p " +
            "LEFT JOIN PokemonTypeLevel ptl1 ON p.pokemonId = ptl1.pokemonCharacterFK.pokemonId AND ptl1.typeLevel = 1 " +
            "LEFT JOIN PokemonType pt1 ON ptl1.pokemonTypeFK.idType = pt1.idType " +
            "LEFT JOIN PokemonTypeLevel ptl2 ON p.pokemonId = ptl2.pokemonCharacterFK.pokemonId AND ptl2.typeLevel = 2 " +
            "LEFT JOIN PokemonType pt2 ON ptl2.pokemonTypeFK.idType = pt2.idType " +
            "WHERE p.pokemonId = :id")
    Optional<PokemonCharacter> findByIdWithTypes(@Param("id") Integer id);


    List<PokemonCharacter> findByPokemonNameContainingIgnoreCase(String pokemonName);
}

