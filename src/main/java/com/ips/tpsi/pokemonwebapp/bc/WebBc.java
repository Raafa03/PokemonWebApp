package com.ips.tpsi.pokemonwebapp.bc;

import com.ips.tpsi.pokemonwebapp.entity.User;

import com.ips.tpsi.pokemonwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

// a anotação Service serve para definir serviços, neste caso o nosso BC que é onde está a componente de lógica de negócio

@Service
public class WebBc {

    @Autowired
    UserRepository repository;

    public boolean isLoginValid(String name, String password) {
        if (name != null && password != null) {
            User user = repository.findUserByUsername(name);
            if (password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public void getRepositoryUserInfo(String username, String password) {
        if (repository.findUserByUsername(username) != null) {
            throw new UsernameAlreadyExistsException("O nome de usuário já existe.");
        } else {
            saveNewUser(username, password);
        }
    }


    



    public User getRepositoryUserInfoByUsername(String username) {
        return repository.findUserByUsername(username);
    }



}

