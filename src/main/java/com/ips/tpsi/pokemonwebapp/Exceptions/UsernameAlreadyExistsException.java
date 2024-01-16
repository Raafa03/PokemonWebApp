    package com.ips.tpsi.pokemonwebapp.Exceptions;

    public class UsernameAlreadyExistsException extends RuntimeException {
        public UsernameAlreadyExistsException(String message) {
            super(message);
        }
    }
