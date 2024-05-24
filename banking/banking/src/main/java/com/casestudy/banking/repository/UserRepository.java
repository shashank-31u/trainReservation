package com.casestudy.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casestudy.banking.model.User;
import com.casestudy.banking.utils.QueryConstants;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = QueryConstants.FETCH_USERS_BY_FULLNAME, nativeQuery = true)
    public List<User> findByFullName(String fullName);

    @Query(value = QueryConstants.FETCH_USERS_BY_EMAIL, nativeQuery = true)
    public List<User> findByEmail(String email);

    @Query(value = QueryConstants.FETCH_USERS_BY_MOBILE, nativeQuery = true)
    public List<User> findByMobile(String mobile);

}
