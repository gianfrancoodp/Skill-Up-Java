package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account , Long> {

    @Query("SELECT a FROM Account a WHERE a.userId = :idUser AND a.currency = :USD")
    public Account queryAccountCurrencyUSD(@Param("idUser") Long idUser , @Param("USD")String usd);

    @Query("SELECT a FROM Account a WHERE a.userId = :idUser AND a.currency = :ARS")
    public Account queryAccountCurrencyARS(@Param("idUser") Long idUser , @Param("ARS")String ars);
    @Query("SELECT a FROM Account a WHERE a.userId = :idUser")
    public List<Account> accountList(@Param("idUser") Long idUser);
    public List<Account> findByUserId(Long id);
}
