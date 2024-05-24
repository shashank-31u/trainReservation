package com.casestudy.banking.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bank_account")
@NamedQuery(name = "BankAccount.findAll", query = "SELECT b FROM BankAccount b")
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    private Long userId;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Column(name = "expiry_date")
    private Timestamp expiryDate;

    @Column(name = "balance")
    private Long bankBalance;

    @Column(name = "cvv_start_date")
    private Timestamp cvvStartDate;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "cvv")
    private String cvv;
//with transient we will fetch the user record simul.
    @Transient
    private User user;

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Long getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(Long bankBalance) {
        this.bankBalance = bankBalance;
    }

    public Timestamp getCvvStartDate() {
        return cvvStartDate;
    }

    public void setCvvStartDate(Timestamp cvvStartDate) {
        this.cvvStartDate = cvvStartDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((bankAccountNumber == null) ? 0 : bankAccountNumber.hashCode());
        result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
        result = prime * result + ((creditCardNumber == null) ? 0 : creditCardNumber.hashCode());
        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        result = prime * result + ((bankBalance == null) ? 0 : bankBalance.hashCode());
        result = prime * result + ((cvvStartDate == null) ? 0 : cvvStartDate.hashCode());
        result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BankAccount other = (BankAccount) obj;
        if (bankAccountNumber == null) {
            if (other.bankAccountNumber != null)
                return false;
        } else if (!bankAccountNumber.equals(other.bankAccountNumber))
            return false;
        if (bankName == null) {
            if (other.bankName != null)
                return false;
        } else if (!bankName.equals(other.bankName))
            return false;
        if (creditCardNumber == null) {
            if (other.creditCardNumber != null)
                return false;
        } else if (!creditCardNumber.equals(other.creditCardNumber))
            return false;
        if (expiryDate == null) {
            if (other.expiryDate != null)
                return false;
        } else if (!expiryDate.equals(other.expiryDate))
            return false;
        if (bankBalance == null) {
            if (other.bankBalance != null)
                return false;
        } else if (!bankBalance.equals(other.bankBalance))
            return false;
        if (cvvStartDate == null) {
            if (other.cvvStartDate != null)
                return false;
        } else if (!cvvStartDate.equals(other.cvvStartDate))
            return false;
        if (accountType == null) {
            if (other.accountType != null)
                return false;
        } else if (!accountType.equals(other.accountType))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BankAccount [bankAccountNumber=" + bankAccountNumber + ", userId=" + userId + ", bankName=" + bankName
                + ", creditCardNumber=" + creditCardNumber + ", expiryDate=" + expiryDate + ", bankBalance="
                + bankBalance + ", cvvStartDate=" + cvvStartDate + ", accountType=" + accountType + ", cvv=" + cvv
                + "]";
    }

}
