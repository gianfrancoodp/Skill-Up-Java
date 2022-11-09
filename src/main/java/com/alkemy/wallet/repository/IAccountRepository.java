package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.util.CurrencyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account , Long> {

    @Query("SELECT a FROM Account a WHERE a.userId.id = :idUser AND a.currency = :USD")
    public Optional<Account> queryAccountCurrencyUSD(@Param("idUser") long idUser , @Param("USD") CurrencyEnum usd);

    @Query("SELECT a FROM Account a WHERE a.userId.id = :idUser AND a.currency = :ARS")
    public Optional<Account> queryAccountCurrencyARS(@Param("idUser") long idUser , @Param("ARS")CurrencyEnum ars);


    @Query("SELECT a FROM Account a WHERE a.userId.id = :idUser")
    public List<Account> accountList(@Param("idUser") long idUser);
}
