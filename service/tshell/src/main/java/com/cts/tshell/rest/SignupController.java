package com.cts.tshell.rest;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tshell.bean.SignupStatus;
import com.cts.tshell.bean.User;
import com.cts.tshell.service.SignupService;

@RestController
public class SignupController {

	
	private SignupService signupService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);

	@Autowired
	public void setSignupService(SignupService signupService) {
		this.signupService = signupService;
	}

	@PostMapping("/signup")
	public SignupStatus signupUser(@RequestBody User user) throws NoSuchAlgorithmException {
		LOGGER.info("Checking user exists");
		LOGGER.debug("User :{} ", user);
		LOGGER.debug("User Role:{} ", user.getRole());
		String email = user.getEmail();
		User actualUserEmail = signupService.findUserByEmail(email);
		LOGGER.debug("ActualUserByEmail :{} ", actualUserEmail);

		String userId = user.getEmployeeId();
		
		User actualUserbyId = signupService.findUserById(userId);
		LOGGER.debug("ActualUserById :{} ", actualUserbyId);

		SignupStatus status = new SignupStatus();
		status.setEmailExist(false);
		status.setUserIdExist(false);
		status.setSignupStatus(false);
		status.setOtpVerifyStatus(false);
		if (actualUserbyId != null) {
			status.setUserIdExist(true);
		}
		if (actualUserEmail != null) {
			status.setEmailExist(true);
		}

		if (!status.isEmailExist() && !status.isUserIdExist()) {
			LOGGER.info("User existence checked");
			LOGGER.info("Signup User");
			signupService.signup(user);
			status.setSignupStatus(true);
			status.setOtpVerifyStatus(false);
		} else if (status.isEmailExist() && status.isUserIdExist() && actualUserbyId != null) {
			if (!actualUserbyId.getSignupOtpVerifyStatus().equals("false")) {
				status.setSignupStatus(true);
				status.setOtpVerifyStatus(true);
			}
		}

		LOGGER.info("User signed up successfully!!!");
		LOGGER.info("end");
		return status;

	}

	@PostMapping("/requestSignupOtp")
	public boolean requestSignupOtp(@RequestBody String employeeId) throws NoSuchAlgorithmException {
		LOGGER.info("Start");
		boolean otp = false;
		LOGGER.debug("employee id :{} ", employeeId);
		signupService.generateSignupOtp(employeeId);
		otp = true;
		LOGGER.info("end");
		return otp;
	}

	@PostMapping("/verifySignupOtp")
	public boolean verifySignupOtp(@RequestBody User user) throws NoSuchAlgorithmException {
		LOGGER.info("Start");
		boolean status;
		status = false;
		status = signupService.matchSignupOtp(user);
		LOGGER.debug("OTP Verify Status:{}", status);
		LOGGER.info("end");
		return status;

	}

	@PostMapping("/validateOtpTime")
	public boolean validateOtpTime(@RequestBody User user) throws NoSuchAlgorithmException {
		LOGGER.info("Start validateOtpTime");
		boolean validateTime = false;
		validateTime = signupService.validateOtpTime(user);
		LOGGER.debug("Validated Time:{}", validateTime);
		LOGGER.info("end");
		return validateTime;

	}

	@PostMapping("/regenerateOtp")
	public boolean regenerateOtp(@RequestBody User user) throws NoSuchAlgorithmException {
		LOGGER.info("Start");
		signupService.generateSignupOtp(user.getEmployeeId());
		LOGGER.info("end");
		return true;
	}

	@GetMapping("/mail")
	public String resetEmail(@PathVariable("email") String email) {
		LOGGER.info("Start");
		signupService.send(email);
		LOGGER.info("end");
		return "check email:";
	}
}
