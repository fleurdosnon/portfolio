package com.fdosnon.online_banking.repositories;

import com.fdosnon.online_banking.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "SELECT * FROM account WHERE client_id = :clientId AND subscription_end_date IS NULL ORDER BY subscription_date DESC", nativeQuery = true)
    Iterable<Account> getAllActiveAccountsByClientId(Integer clientId);
}
