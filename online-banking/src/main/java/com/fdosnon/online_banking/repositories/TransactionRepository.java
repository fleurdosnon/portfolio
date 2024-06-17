package com.fdosnon.online_banking.repositories;

import com.fdosnon.online_banking.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT * FROM transaction WHERE account_id = :accountId AND cancellation_date IS NULL ORDER BY transaction_date DESC", nativeQuery = true)
    Iterable<Transaction> findAllActiveTransactionsByAccountId(Integer accountId);
}
