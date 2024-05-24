package com.casestudy.banking.services;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.banking.dto.ResponseDTO;
import com.casestudy.banking.dto.UserDTO;
import com.casestudy.banking.interfaces.BankAccountService;
import com.casestudy.banking.model.BankAccount;
import com.casestudy.banking.model.BankTransaction;
import com.casestudy.banking.model.User;
import com.casestudy.banking.repository.BankAccountRepository;
import com.casestudy.banking.repository.BankTransactionRepository;
import com.casestudy.banking.repository.UserRepository;
import com.casestudy.banking.utils.StringUtils;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseDTO saveBankDetails(UserDTO userDTO, String accountNumber) {

        Date today = new Date();

        BankAccount bankAccountDetails = bankAccountRepository.findByUserId(userDTO.getUserId());
        if (bankAccountDetails != null) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("FAILED!");
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(Arrays.asList("Bank account already there with that user id!"));
            responseDTO.setSuccess(false);
        }

        try {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setBankBalance(userDTO.getDeposit());
            bankAccount.setBankName(userDTO.getBankName());
            bankAccount.setCreditCardNumber(userDTO.getCreditCardNumber());
            bankAccount.setCvvStartDate(new Timestamp(today.getTime()));
            bankAccount.setExpiryDate(userDTO.getExpiryDate());
            bankAccount.setAccountType(userDTO.getAccountType());
            bankAccount.setUserId(Long.parseLong(accountNumber));
            bankAccount.setCvv(userDTO.getCvv());
            bankAccount.setBankAccountNumber(StringUtils.generateBankAccountNumber(accountNumber, 12));

            bankAccount = bankAccountRepository.save(bankAccount);
            return new ResponseDTO("SUCCESS", null, LocalDateTime.now(), true, bankAccount.getBankAccountNumber());
        } catch (Exception ex) {
            ex.printStackTrace();
            ResponseDTO response = new ResponseDTO();
            response.setMessage("FAILED!");
            response.setResponseTime(LocalDateTime.now());
            response.setSuccess(false);
            response.setErrorMessage(Arrays.asList("Something went wrong, while saving the record"));
            return response;
        }

    }

    public ResponseDTO isBankDetailsVerified(UserDTO userDTO) {

        List<BankAccount> bankAccountList = bankAccountRepository.findByAccountNumber(userDTO.getBankName(),
                userDTO.getCreditCardNumber());

        if (bankAccountList != null && bankAccountList.size() > 0) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Duplication Record Entry!");
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(Arrays.asList(
                    "Credit card number: " + userDTO.getCreditCardNumber()
                            + " is already registered for this bank, Bank Name: " + userDTO.getBankName()));
            return responseDTO;
        }

        return new ResponseDTO(true);
    }

    @Override
    public Long getBalance(String bankAccountNumber) {
        
            Long bankBalance = bankAccountRepository.findBalanceByBankAccountNumber(bankAccountNumber);
            return bankBalance;
        
    }

    @Override
    public ResponseDTO balanceDebited(String bankAccountNumber, Long amountToBeDebited, String creditCardNumber,
            String cvv) {
        Date today = new Date();
        try {
            List<BankAccount> bankAccountDetails = bankAccountRepository.findByBankAccountNumber(bankAccountNumber);
            ResponseDTO responseDTO = new ResponseDTO();
            if (bankAccountDetails != null && bankAccountDetails.size() > 0) {
                BankAccount bankAccount = bankAccountDetails.get(0);
                if (bankAccount.getCreditCardNumber().equalsIgnoreCase(StringUtils.checkNullString(creditCardNumber))
                        && bankAccount.getCvv().equalsIgnoreCase(StringUtils.checkNullString(cvv))) {
                    if (bankAccount.getBankBalance() < amountToBeDebited) {
                        responseDTO.setSuccessMessage("Insufficient Balance!");
                    } else {
                        BankTransaction bankTransaction = new BankTransaction();
                        bankTransaction.setAccountNumber(bankAccountNumber);
                        bankTransaction.setAmountDebit(amountToBeDebited);
                        bankTransaction.setTransactionDate(new Timestamp(today.getTime()));
                        bankTransaction.setAmountCredit(0L);
                        bankTransaction.setBankBalance(bankAccount.getBankBalance());
                        bankTransaction.setCurrentBankBalance(bankAccount.getBankBalance() - amountToBeDebited);
                        bankTransactionRepository.save(bankTransaction);

                        bankAccountRepository.updateBalanceByAccountNumber(
                                bankAccount.getBankBalance() - amountToBeDebited, cvv, creditCardNumber,
                                bankAccountNumber);
                        responseDTO.setSuccessMessage("Amount Debited Successfully!");
                    }
                } else {
                    responseDTO.setSuccessMessage("Credit card number and cvv is not matching with the bank details");
                }

            } else {
                responseDTO.setSuccessMessage("Bank Account Doesn't exists!");
            }
            responseDTO.setSuccess(true);
            responseDTO.setResponseTime(LocalDateTime.now());
            return responseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(false);
        }

    }

    @Override
    public ResponseDTO balanceCredited(String bankAccountNumber, Long amountToBeCredited) {
        Date today = new Date();
        try {
            List<BankAccount> bankAccountDetails = bankAccountRepository.findByBankAccountNumber(bankAccountNumber);
            ResponseDTO responseDTO = new ResponseDTO();
            if (bankAccountDetails != null && bankAccountDetails.size() > 0) {
                BankAccount bankAccount = bankAccountDetails.get(0);
                BankTransaction bankTransaction = new BankTransaction();
                bankTransaction.setAccountNumber(bankAccountNumber);
                bankTransaction.setAmountDebit(0L);
                bankTransaction.setTransactionDate(new Timestamp(today.getTime()));
                bankTransaction.setAmountCredit(amountToBeCredited);
                bankTransaction.setBankBalance(bankAccount.getBankBalance());
                bankTransaction.setCurrentBankBalance(bankAccount.getBankBalance() + amountToBeCredited);
                bankTransactionRepository.save(bankTransaction);

                bankAccountRepository.updateBalanceByAccountNumber(
                        bankAccount.getBankBalance() + amountToBeCredited, bankAccount.getCvv(),
                        bankAccount.getCreditCardNumber(), bankAccountNumber);
                responseDTO.setSuccessMessage("Amount Credited Successfully!");
            } else {
                responseDTO.setSuccessMessage("Bank Account Doesn't exists!");
            }
            responseDTO.setSuccess(true);
            responseDTO.setResponseTime(LocalDateTime.now());
            return responseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(false);
        }
    }

    @Override
    public ResponseDTO updateBankDetails(UserDTO userDTO) {

        try {
            BankAccount bankAccount = bankAccountRepository.findByUserId(userDTO.getUserId());

            bankAccount.setBankBalance(userDTO.getDeposit());
            bankAccount.setBankName(userDTO.getBankName());
            bankAccount.setCreditCardNumber(userDTO.getCreditCardNumber());
            bankAccount.setExpiryDate(userDTO.getExpiryDate());
            bankAccount.setAccountType(userDTO.getAccountType());
            bankAccount.setCvv(userDTO.getCvv());

            bankAccountRepository.save(bankAccount);
            return new ResponseDTO(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            ResponseDTO response = new ResponseDTO();
            response.setMessage("FAILED!");
            response.setResponseTime(LocalDateTime.now());
            response.setSuccess(false);
            response.setErrorMessage(Arrays.asList("Something went wrong, while updating the record"));
            return response;
        }

    }

    @Override
    public BankAccount getBankAccountByAccountNumber(String bankAccountNumber) {

        if (!StringUtils.checkNullString(bankAccountNumber).isEmpty()) {
            List<BankAccount> bankAccountList = bankAccountRepository.findByBankAccountNumber(bankAccountNumber);

            if (bankAccountList != null && bankAccountList.size() > 0) {
                BankAccount bankAccount = bankAccountList.get(0);
                Optional<User> userList = userRepository.findById(bankAccount.getUserId());
                if (userList != null && userList.isPresent()) {
                    bankAccount.setUser(userList.get());
                }
                return bankAccount;
            }
        }
        return new BankAccount();
    }

    @Override
    public List<BankTransaction> getTransactionHistoryByAccountNumber(String bankAccountNumber) {

        if (!StringUtils.checkNullString(bankAccountNumber).isEmpty()) {
            List<BankTransaction> BankTransactionList = bankTransactionRepository
                    .findTransactionHistoryByBankAccountNumber(bankAccountNumber);
            if (BankTransactionList != null && BankTransactionList.size() > 0) {
                return BankTransactionList;
            }
        }
        return null;
    }

}
