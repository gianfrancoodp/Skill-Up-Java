package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository <Role, Long> {
    Role findByName(String name);
}
