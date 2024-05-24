package com.casestudy.banking.interfaces;

import com.casestudy.banking.dto.ResponseDTO;
import com.casestudy.banking.dto.UserDTO;

public interface UserService {

    public ResponseDTO saveUserAccountDetails(UserDTO userDTO);

    public ResponseDTO deleteUserAccountDetails(UserDTO userDTO);

    public ResponseDTO updateUserAccountDetails(UserDTO userDTO, String email, String mobile);

}
