package com.ips.tpsi.pokemonwebapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemon_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Integer idType;

    @Column(name = "type_desc")
    private String typeDesc;

}
