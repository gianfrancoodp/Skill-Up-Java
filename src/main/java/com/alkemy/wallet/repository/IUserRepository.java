package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.User;
import com.alkemy.wallet.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUsername(String username);
}
