package com.ips.tpsi.pokemonwebapp.Exceptions;


public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
