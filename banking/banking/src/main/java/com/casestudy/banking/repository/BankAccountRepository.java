package com.casestudy.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.banking.model.BankAccount;
import com.casestudy.banking.utils.QueryConstants;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    @Query(value = QueryConstants.FETCH_BANK_ACCOUNT_BY_ACCNO_BANK_NAME_AND_CARDNO, nativeQuery = true)
    public List<BankAccount> findByAccountNumber(String bankName, String creditCardNumber);

    @Query(value = QueryConstants.FETCH_BANK_BALANCE_BY_ACCOUNT_NUMBER, nativeQuery = true)
    public Long findBalanceByBankAccountNumber(String bankAccountNumber);

    public List<BankAccount> findByBankAccountNumber(String bankAccountNumber);

    @Transactional
    @Modifying
    @Query(value = QueryConstants.UPDATE_BALACE_BANK_ACCOUNT_NUMBER, nativeQuery = true)
    public void updateBalanceByAccountNumber(Long bankBalance, String cvv, String creditCardNumber,
            String bankAccountNumber);

    public BankAccount findByUserId(Long userId);

    @Transactional
    @Modifying
    public void deleteByUserId(Long userId);

}