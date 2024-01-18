package com.ips.tpsi.pokemonwebapp.repository;

import com.ips.tpsi.pokemonwebapp.entity.PokemonTypeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonTypeRepository extends JpaRepository<PokemonTypeLevel, Integer> {
}
