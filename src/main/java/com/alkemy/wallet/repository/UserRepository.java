package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();





}
