
package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value =  "select id.c,currency.c as cuentas,first_name.u,second_name.u,email.u as usuario from account as a, users as u where c.user_id=u.id",nativeQuery = true)
    Collection<ConteoPersonaNac> getUsersByAccount();
}