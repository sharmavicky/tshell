package com.cts.tshell.rest;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tshell.bean.AuthenticationStatus;
import com.cts.tshell.bean.User;
import com.cts.tshell.bean.Views;
import com.cts.tshell.service.UserService;
import com.cts.tshell.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
@RestController
public class LoginController extends TshellController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userservice;

	@GetMapping("/rest/{employeeId}")
	public String getUser(@PathVariable("employeeId") String employeeId) throws JsonProcessingException {
		LOGGER.info("Start");
		User user = userservice.getUser(employeeId);
		LOGGER.debug("EmployeeId : {}", employeeId);
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writerWithView(Views.Public.class).writeValueAsString(user);
		LOGGER.info("End");
		return result;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationStatus> authenticate(@RequestBody User user) throws NoSuchAlgorithmException {
		LOGGER.info("Start");

		LOGGER.debug("From request (user) : {}", user);
		LOGGER.debug("From USerName (user) : {}", user.getEmployeeId());
		String employeeId = user.getEmployeeId();
		LOGGER.debug("Value of employeeId: {} ", employeeId);
		String password = user.getPassword();
		LOGGER.debug("Value of password: {} ", password);

		String encryptedPassword = Util.encryptToMD5(password);
		LOGGER.debug("User entered encrypted password: {} ", encryptedPassword);

		String actualPassword = "";
		String actualEmployeeId = "";
		AuthenticationStatus status = new AuthenticationStatus();
		status.setAuthenticated(false);
		status.setAdmin(false);
		User actualUser = userservice.getUser(employeeId);

		LOGGER.debug("From request (actualUser) : {}", actualUser);
		if (actualUser != null) {
			actualPassword = actualUser.getPassword();
			if (employeeId.equals("admin") && encryptedPassword.equals(actualPassword)) {
				status.setAdmin(true);
			} else {
				
				actualEmployeeId = actualUser.getEmployeeId();
				status.setUser(actualUser);
				status.setAuthenticated(employeeId.equals(actualEmployeeId));
				status.setAuthenticated(encryptedPassword.equals(actualPassword));
			}
		}
		LOGGER.debug("Value of actualPassword: {} ", actualPassword);
		LOGGER.debug("Value of status: {} ", status);
		LOGGER.info("End");
		return new ResponseEntity<AuthenticationStatus>(status, HttpStatus.OK);
	}

}
