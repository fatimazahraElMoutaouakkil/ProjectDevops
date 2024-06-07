package com.ensab.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ensab.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    
}