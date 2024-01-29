package com.agido.ecommerce.user.repository;

import com.agido.ecommerce.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailAndPassword(String email,String password);



}
