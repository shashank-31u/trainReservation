package com.casestudy.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casestudy.banking.model.BankTransaction;
import com.casestudy.banking.utils.QueryConstants;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {

    @Query(value = QueryConstants.FETCH_BANK_TRANSACTION_HISTORY_BY_ACCOUNT_NUMBER, nativeQuery = true)
    public List<BankTransaction> findTransactionHistoryByBankAccountNumber(String bankAccountNumber);

}
