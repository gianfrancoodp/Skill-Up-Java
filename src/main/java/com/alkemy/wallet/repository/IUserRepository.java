package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {


}
