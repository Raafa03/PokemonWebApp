package com.ips.tpsi.pokemonwebapp.repository;



import com.ips.tpsi.pokemonwebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);


}
