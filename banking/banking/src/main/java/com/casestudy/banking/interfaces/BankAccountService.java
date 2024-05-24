package com.casestudy.banking.interfaces;

import java.util.List;

import com.casestudy.banking.dto.ResponseDTO;
import com.casestudy.banking.dto.UserDTO;
import com.casestudy.banking.model.BankAccount;
import com.casestudy.banking.model.BankTransaction;

public interface BankAccountService {

    public ResponseDTO saveBankDetails(UserDTO userDTO, String accountNumber);

    public ResponseDTO updateBankDetails(UserDTO userDTO);

    public Long getBalance(String bankAccountNumber);

    public ResponseDTO balanceDebited(String bankAccountNumber, Long amountToBeDebited, String creditCardNumber,
            String cvv);

    public ResponseDTO balanceCredited(String bankAccountNumber, Long amountToBeCredited);

    public BankAccount getBankAccountByAccountNumber(String bankAccountNumber);

    public List<BankTransaction> getTransactionHistoryByAccountNumber(String bankAccountNumber);

}
