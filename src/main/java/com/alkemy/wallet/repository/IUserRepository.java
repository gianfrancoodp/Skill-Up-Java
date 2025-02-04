package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Long> {


    UserEntity findByEmail(String email);

    Page<UserEntity> findAll(Pageable pageable);

}