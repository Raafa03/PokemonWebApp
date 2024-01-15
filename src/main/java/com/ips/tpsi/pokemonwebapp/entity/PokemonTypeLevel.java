package com.ips.tpsi.pokemonwebapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemon_type_level")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonTypeLevel {


    @Id
    @Column(name = "id_pokemon_type_level")
    private Integer IdpokemonTypeLevel;


    @Column(name = "type_level")
    private Integer typeLevel;


    @ManyToOne
    @JoinColumn(name = "pokemon_character_id", referencedColumnName = "id_pokemon_character", foreignKey = @ForeignKey(name = "FK_pokemon_character"))
    private PokemonCharacter pokemonCharacterFK;


    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id_type", foreignKey = @ForeignKey(name = "FK_pokemon_type"))
    private PokemonType pokemonTypeFK;



}
