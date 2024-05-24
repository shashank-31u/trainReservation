package com.casestudy.banking.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.casestudy.banking.dto.ResponseDTO;
import com.casestudy.banking.dto.UserDTO;
import com.casestudy.banking.interfaces.UserService;
import com.casestudy.banking.model.BankAccount;
import com.casestudy.banking.model.User;
import com.casestudy.banking.repository.BankAccountRepository;
import com.casestudy.banking.repository.UserRepository;
import com.casestudy.banking.utils.StringUtils;

@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountServiceImpl bankAccountServiceImpl;

    @Override
    public ResponseDTO saveUserAccountDetails(UserDTO userDTO) {

        List<User> usersListsByEmail = userRepository.findByEmail(userDTO.getEmail());
        List<User> usersListsByMobile = userRepository.findByMobile(userDTO.getMobile());
        if ((usersListsByEmail != null && usersListsByEmail.size() > 0)
                || (usersListsByMobile != null && usersListsByMobile.size() > 0)) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Duplication Record Entry!");
            responseDTO.setSuccess(false);
            responseDTO.setResponseTime(LocalDateTime.now());
            List<String> errorMessage = new ArrayList<>();
            addErrorMessageIfDuplicate(errorMessage, usersListsByEmail, "email id", userDTO.getEmail());
            addErrorMessageIfDuplicate(errorMessage, usersListsByMobile, "mobile number", userDTO.getMobile());
            responseDTO.setErrorMessage(errorMessage);
            return responseDTO;
        }

        ResponseDTO responseDTO = bankAccountServiceImpl.isBankDetailsVerified(userDTO);
        if (!responseDTO.getSuccess())
            return responseDTO;

        try {
            User user = new User();
            user.setFullName(userDTO.getFullName());
            user.setAge(userDTO.getAge());
            user.setMobile(userDTO.getMobile());
            user.setEmail(userDTO.getEmail());
            user.setDateOfBirth(userDTO.getDateOfBirth());
            

            user = userRepository.save(user);

            responseDTO = bankAccountServiceImpl.saveBankDetails(userDTO, user.getUserId().toString());

            if (responseDTO.getSuccess()) {
                StringBuilder comment = new StringBuilder(
                        "User Bank Account Successfully Created! Bank Account Number Is: ");
                comment.append(responseDTO.getSuccessMessage());
                responseDTO.setSuccessMessage(comment.toString());
            }

            return responseDTO;

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

    private void addErrorMessageIfDuplicate(List<String> errorMessage, List<?> userList, String field, String value) {
        if (userList != null && userList.size() > 0) {
            errorMessage.add("User account exists with " + field + ": " + value);
        }
    }

    public List<User> validateUserAndBankDetails(UserDTO userDTO, String email, String mobile) {
        Boolean flag = false;

        List<User> usersLists = !StringUtils.checkNullString(email).isEmpty()
                ? userRepository.findByEmail(email)
                : !StringUtils.checkNullString(mobile).isEmpty()
                        ? userRepository.findByMobile(mobile)
                        : new ArrayList<>();

        if (usersLists != null && usersLists.size() > 0) {
            BankAccount bankAccount = bankAccountRepository.findByUserId(usersLists.get(0).getUserId());
            if (bankAccount != null) {
                flag = true;
            }
        }
        return flag == true ? usersLists : new ArrayList<>();
    }

    @Override
    public ResponseDTO deleteUserAccountDetails(UserDTO userDTO) {

        List<User> usersList = validateUserAndBankDetails(userDTO, userDTO.getEmail(), userDTO.getMobile());
        if (usersList == null || usersList.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("FAILED!");
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(Arrays.asList("User doesn't exists"));
            responseDTO.setSuccess(false);
            return responseDTO;
        }

        try {
            bankAccountRepository.deleteByUserId(usersList.get(0).getUserId());
            userRepository.deleteById(usersList.get(0).getUserId());
        } catch (Exception ex) {
            ex.printStackTrace();
            ResponseDTO response = new ResponseDTO();
            response.setMessage("FAILED!");
            response.setResponseTime(LocalDateTime.now());
            response.setSuccess(false);
            response.setErrorMessage(Arrays.asList("Something went wrong, while deleting the record"));
            return response;
        }

        return new ResponseDTO(true);
    }

    @Override
    public ResponseDTO updateUserAccountDetails(UserDTO userDTO, String email, String mobile) {

        List<User> usersList = validateUserAndBankDetails(userDTO, email, mobile);
        if (usersList == null || usersList.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("FAILED!");
            responseDTO.setResponseTime(LocalDateTime.now());
            responseDTO.setErrorMessage(Arrays.asList("User doesn't exists"));
            responseDTO.setSuccess(false);
            return responseDTO;
        }

        try {

            User user = usersList.get(0);
            user.setFullName(userDTO.getFullName());
            user.setAge(userDTO.getAge());
            user.setMobile(userDTO.getMobile());
            user.setEmail(userDTO.getEmail());
            user.setDateOfBirth(userDTO.getDateOfBirth());
           
            userDTO.setUserId(user.getUserId());
            userRepository.save(user);

            ResponseDTO responseDTO = bankAccountServiceImpl.updateBankDetails(userDTO);
            if (!responseDTO.getSuccess()) {
                return responseDTO;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            ResponseDTO response = new ResponseDTO();
            response.setMessage("FAILED!");
            response.setResponseTime(LocalDateTime.now());
            response.setSuccess(false);
            response.setErrorMessage(Arrays.asList("Something went wrong, while updating the record"));
            return response;
        }

        return new ResponseDTO(true);
    }

}
