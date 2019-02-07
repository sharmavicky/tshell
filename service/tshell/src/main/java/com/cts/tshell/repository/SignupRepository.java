package com.cts.tshell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.tshell.bean.User;

public interface SignupRepository extends JpaRepository<User, Integer> {

	User findByEmployeeId(String employeeId);
}
