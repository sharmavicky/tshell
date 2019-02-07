package com.cts.tshell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.tshell.bean.User;

@Repository

public interface UserRepository extends JpaRepository<User, String> {

	public User findByEmpId(@Param("employeeId") String employeeId);

	public List<User> findAllById(@Param("id") String userId);

	public User getUserByEmail(String email);

	public User getUserByEmployeeId(String userId);

	@Query("select count(u.id) from User u ")
	public long totalUserCount();

	public User fetchByEmployeeId(@Param("employeeId") String employeeId);

	public User findByEmployeeId(String employeeId);

	User findUserByEmployeeId(String employeeId);
}
