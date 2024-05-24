package com.casestudy.banking.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.casestudy.banking.config.CustomValidationException;
import com.casestudy.banking.dto.ResponseDTO;
import com.casestudy.banking.dto.UserDTO;
import com.casestudy.banking.model.BankAccount;
import com.casestudy.banking.model.BankTransaction;
import com.casestudy.banking.services.BankAccountServiceImpl;
import com.casestudy.banking.services.UserServiceImpl;
import com.casestudy.banking.utils.StringUtils;

@CrossOrigin
@Controller
@RequestMapping(value = "/api/bank")
public class AccountController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private BankAccountServiceImpl bankAccountServiceImpl;

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody UserDTO userDTO) {

        try {
            if (null != userDTO) {
                Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
                if (!violations.isEmpty()) {
                    throw new CustomValidationException(violations, userDTO);
                }

                ResponseDTO response = userServiceImpl.saveUserAccountDetails(userDTO);
                if (!response.getSuccess()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }

                return ResponseEntity.ok().body(response);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(FAILED, Arrays.asList("Request not sent properly!"), LocalDateTime.now(),
                            false, null));

        } catch(CustomValidationException ex){
        	throw ex;
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getBalance(@RequestParam("bankAccountNumber") String bankAccountNumber) {

        try {
            if (!StringUtils.checkNullString(bankAccountNumber).isEmpty()) {
                Long getBalance = bankAccountServiceImpl.getBalance(bankAccountNumber);
                if (getBalance != null) {
                    ResponseDTO responseDTO = new ResponseDTO();
                    responseDTO.setMessage(SUCCESS);
                    responseDTO.setSuccess(true);
                    responseDTO.setResponseTime(LocalDateTime.now());
                    responseDTO.setSuccessMessage("Current Balance For Account Number: [" + bankAccountNumber
                            + "] is : " + getBalance.toString());
                    return ResponseEntity.ok().body(responseDTO);
                }
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(SUCCESS,
                            null,
                            LocalDateTime.now(), true,
                            "Bank Account Number : [" + bankAccountNumber + "] doesn't exists"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(value = "/amountDebit", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> debitBalace(@RequestParam("bankAccountNumber") String bankAccountNumber,
            @RequestParam("debitBalance") Long debitBalance, @RequestParam("creditCardNumber") String creditCardNumber,
            @RequestParam("cvv") String cvv) {

        try {
            if (!StringUtils.checkNullString(bankAccountNumber).isEmpty()
                    && !StringUtils.checkNullString(debitBalance.toString()).isEmpty()) {
                ResponseDTO responseDTO = bankAccountServiceImpl.balanceDebited(bankAccountNumber, debitBalance,
                        creditCardNumber, cvv);
                if (responseDTO.getSuccess()) {
                    responseDTO.setMessage(SUCCESS);
                    return ResponseEntity.ok().body(responseDTO);
                }
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(SUCCESS,
                            null,
                            LocalDateTime.now(), true,
                            "Bank Account Number : [" + bankAccountNumber + "] doesn't exists"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> creditBalance(@RequestParam("bankAccountNumber") String bankAccountNumber,
            @RequestParam("creditBalance") Long creditBalance) {

        try {
            if (!StringUtils.checkNullString(bankAccountNumber).isEmpty()
                    && !StringUtils.checkNullString(creditBalance.toString()).isEmpty()) {
                ResponseDTO responseDTO = bankAccountServiceImpl.balanceCredited(bankAccountNumber, creditBalance);
                if (responseDTO.getSuccess()) {
                    responseDTO.setMessage(SUCCESS);
                    return ResponseEntity.ok().body(responseDTO);
                }
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(SUCCESS,
                            null,
                            LocalDateTime.now(), true,
                            "Bank Account Number : [" + bankAccountNumber + "] doesn't exists"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(value = "/deleteUserAndBankAccount", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteUserAndBankAccount(@RequestBody UserDTO userDTO) {

        try {
            ResponseDTO responseDTO = userServiceImpl.deleteUserAccountDetails(userDTO);
            if (!responseDTO.getSuccess()) {
                return ResponseEntity.badRequest().body(responseDTO);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(SUCCESS,
                            null,
                            LocalDateTime.now(), true,
                            "User and Bank Account Deleted Successfully!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(value = "/updateUserAndBankAccount", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDTO> updateUserAndBankAccount(@RequestBody UserDTO userDTO,
            @RequestParam("email") String email, @RequestParam("mobile") String mobile) {

        try {
            ResponseDTO responseDTO = userServiceImpl.updateUserAccountDetails(userDTO, email, mobile);
            if (!responseDTO.getSuccess()) {
                return ResponseEntity.badRequest().body(responseDTO);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(SUCCESS,
                            null,
                            LocalDateTime.now(), true,
                            "User and Bank Account Updated Successfully!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(value = "/getUserByBankAccountNumber", method = RequestMethod.GET)
    public ResponseEntity<?> getUserDetailsByBankAccountNumber(
            @RequestParam("bankAccountNumber") String bankAccountNumber) {

        try {
            BankAccount bankAccount = bankAccountServiceImpl.getBankAccountByAccountNumber(bankAccountNumber);
            if (bankAccount == null || bankAccount.getUserId() == null) {
                return ResponseEntity.badRequest().body(new ResponseDTO(SUCCESS,
                        null,
                        LocalDateTime.now(), true,
                        "User with that Bank Account Number Doesn't Exists!"));
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(bankAccount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(value = "/getTransactionHistoryByBankAccountNumber", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactionHistoryByBankAccountNumber(
            @RequestParam("bankAccountNumber") String bankAccountNumber) {

        try {
            List<BankTransaction> bankTransactionList = bankAccountServiceImpl
                    .getTransactionHistoryByAccountNumber(bankAccountNumber);
            if (bankTransactionList == null || bankTransactionList.isEmpty()) {
                return ResponseEntity.badRequest().body(new ResponseDTO(SUCCESS,
                        null,
                        LocalDateTime.now(), true,
                        "No Transaction History With That Account Number!"));
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(bankTransactionList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
