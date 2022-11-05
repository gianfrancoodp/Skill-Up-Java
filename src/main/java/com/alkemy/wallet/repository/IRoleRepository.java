package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository <Role, Long> {
}
