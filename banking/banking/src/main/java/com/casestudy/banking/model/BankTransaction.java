package com.casestudy.banking.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "bank_transaction")
@NamedQuery(name = "BankTransaction.findAll", query = "SELECT b FROM BankTransaction b")
public class BankTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    @Column(name = "amount_credit")
    private Long amountCredit;

    @Column(name = "amount_debit")
    private Long amountDebit;

    @Column(name = "previous_balance")
    private Long bankBalance;

    @Column(name = "current_balance")
    private Long currentBankBalance;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getAmountCredit() {
        return amountCredit;
    }

    public void setAmountCredit(Long amountCredit) {
        this.amountCredit = amountCredit;
    }

    public Long getAmountDebit() {
        return amountDebit;
    }

    public void setAmountDebit(Long amountDebit) {
        this.amountDebit = amountDebit;
    }

    public Long getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(Long bankBalance) {
        this.bankBalance = bankBalance;
    }

    public Long getCurrentBankBalance() {
        return currentBankBalance;
    }

    public void setCurrentBankBalance(Long currentBankBalance) {
        this.currentBankBalance = currentBankBalance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
        result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
        result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
        result = prime * result + ((amountCredit == null) ? 0 : amountCredit.hashCode());
        result = prime * result + ((amountDebit == null) ? 0 : amountDebit.hashCode());
        result = prime * result + ((bankBalance == null) ? 0 : bankBalance.hashCode());
        result = prime * result + ((currentBankBalance == null) ? 0 : currentBankBalance.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BankTransaction other = (BankTransaction) obj;
        if (transactionId == null) {
            if (other.transactionId != null)
                return false;
        } else if (!transactionId.equals(other.transactionId))
            return false;
        if (accountNumber == null) {
            if (other.accountNumber != null)
                return false;
        } else if (!accountNumber.equals(other.accountNumber))
            return false;
        if (transactionDate == null) {
            if (other.transactionDate != null)
                return false;
        } else if (!transactionDate.equals(other.transactionDate))
            return false;
        if (amountCredit == null) {
            if (other.amountCredit != null)
                return false;
        } else if (!amountCredit.equals(other.amountCredit))
            return false;
        if (amountDebit == null) {
            if (other.amountDebit != null)
                return false;
        } else if (!amountDebit.equals(other.amountDebit))
            return false;
        if (bankBalance == null) {
            if (other.bankBalance != null)
                return false;
        } else if (!bankBalance.equals(other.bankBalance))
            return false;
        if (currentBankBalance == null) {
            if (other.currentBankBalance != null)
                return false;
        } else if (!currentBankBalance.equals(other.currentBankBalance))
            return false;
        return true;
    }

}
