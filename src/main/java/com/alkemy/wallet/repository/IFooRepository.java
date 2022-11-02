package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFooRepository extends JpaRepository<Foo, Long> {
}
