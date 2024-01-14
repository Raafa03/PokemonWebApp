package com.ips.tpsi.pokemonwebapp.repository;


import com.ips.tpsi.pokemonwebapp.entity.PokemonCharacter;

import com.ips.tpsi.pokemonwebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCharacterRepository extends JpaRepository<PokemonCharacter, Integer> {


}

