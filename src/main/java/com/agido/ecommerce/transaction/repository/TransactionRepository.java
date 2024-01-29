package com.agido.ecommerce.transaction.repository;

import com.agido.ecommerce.account.model.Account;
import com.agido.ecommerce.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionByAccount(Account account);

}
