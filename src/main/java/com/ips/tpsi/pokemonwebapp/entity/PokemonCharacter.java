package com.ips.tpsi.pokemonwebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Entity
@Table(name = "pokemon_character")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pokemon_character")
    private Integer pokemonId;

    @Column(name = "pokemon_character_name")
    private String pokemonName;

    @Column(name = "total")
    private Integer pokemonTotal;

    @Column(name = "hp")
    private Integer pokemonHp;

    @Column(name = "attack")
    private Integer pokemonAttack;

    @Column(name = "defense")
    private Integer pokemonDefense;

    @Column(name = "sp_atk")
    private Integer pokemonSp_atk;

    @Column(name = "sp_def")
    private Integer pokemonSp_def;

    @Column(name = "speed")
    private Integer pokemonSpeed;

    @Column(name = "generation")
    private Integer pokemonGeneration;

    @Column(name = "legendary")
    private String pokemonLegendary;

    @OneToMany(mappedBy = "pokemonCharacterFK", fetch = FetchType.LAZY)
    private List<PokemonTypeLevel> types;


}
