package com.agido.ecommerce.account.repository;

import com.agido.ecommerce.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.transactions")
    List<Account> findAllWithTransactions();
    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.transactions WHERE a.account_id = ?1")
    Account findAccountWithTransactions(Long id);

}
