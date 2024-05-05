package com.example.ms1user.dao.repository;

import com.example.ms1user.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findUserByEmail(String email);
    Optional<UserEntity> findUserByCustomerCode(String customerCode);

}