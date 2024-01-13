package com.ips.tpsi.pokemonwebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemon_character")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class pokemon_character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pokemon_character")
    private Integer id_pokemon_character;

    @Column(name = "pokemon_character_name")
    private String pokemon_character_name;

    @Column(name = "total")
    private Integer total;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "attack")
    private Integer attack;

    @Column(name = "defense")
    private Integer defense;

    @Column(name = "sp_atk")
    private Integer sp_atk;

    @Column(name = "sp_def")
    private Integer sp_def;

    @Column(name = "generation")
    private Integer generation;

    @Column(name = "legendary")
    private String legendary;





}
