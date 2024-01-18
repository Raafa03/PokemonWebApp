package com.ips.tpsi.pokemonwebapp.repository;

import com.ips.tpsi.pokemonwebapp.entity.PokemonTypeLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonTypeRepository extends JpaRepository<PokemonTypeLevel, Integer> {
}
