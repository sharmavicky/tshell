package com.cts.tshell.service;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cts.tshell.bean.Role;
import com.cts.tshell.bean.User;
import com.cts.tshell.bean.Util;
import com.cts.tshell.repository.SignupRepository;
import com.cts.tshell.repository.UserRepository;
import com.cts.tshell.rest.SignupController;

@Service
public class SignupService {
	private SignupRepository signupRepository;
	private JavaMailSender javaMailSender;

	@Autowired
	private UserRepository userRepository;
	private RoleService roleService;

	@Autowired
	public SignupService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);

	@Autowired
	public void setSignupRepository(SignupRepository signupRepository) {

		this.signupRepository = signupRepository;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Transactional
	public void generateSignupOtp(String employeeId) throws NoSuchAlgorithmException {
		LOGGER.info("Start ");
		String generatedOtp = Util.encryptToMD5(String.valueOf(Util.generateSignupOTP()));
		User user = userRepository.getUserByEmployeeId(employeeId);
		user.setSignupOtp(generatedOtp);
		user.setSignupOtpTime(new Date());
		userRepository.save(user);
		LOGGER.debug("Generated signup otp: {}", generatedOtp);
		LOGGER.info("end");

	}

	@Transactional
	public boolean matchSignupOtp(User user) throws NoSuchAlgorithmException {
		LOGGER.info("Start generateSignupOtp");
		boolean status = false;
		boolean timeStatus = false;
		LOGGER.debug("Sent User object: {}", user);
		LOGGER.debug("Otp time:{}", user.getSignupOtpTime());
		String employeeId = user.getEmployeeId();
		String userOtp = user.getSignupOtp();
		String encryptedUserOtp = Util.encryptToMD5(userOtp);
		LOGGER.debug("Sent otp: {}", encryptedUserOtp);
		User actualUser = userRepository.getUserByEmployeeId(employeeId);
		LOGGER.debug("Actual user otp time:{}", actualUser.getSignupOtpTime());
		String actualOtp = actualUser.getSignupOtp();
		LOGGER.debug("Actual Otp:{} ", actualOtp);

		if (actualOtp.equals(encryptedUserOtp)) {
			status = true;

			actualUser.setSignupOtpVerifyStatus("true");
			userRepository.save(actualUser);
			LOGGER.info("OTP verification successful");
		}
		LOGGER.info("end");
		return status;
	}

	@Transactional
	public void signup(User user) throws NoSuchAlgorithmException {
		LOGGER.info("Starts");
		LOGGER.info("Set role As Learner");
		LOGGER.debug("User: {}", user);
		Date date =new Date(); 
		user.setSignupDate(date);

		String userRole = user.getRole().getName();

		Role role = roleService.getRoleByName(userRole);
		user.setRole(role);
		LOGGER.info("Role set");
		user.setSignupOtpVerifyStatus("false");
		String password = user.getPassword();

		String encryptedPassword = Util.encryptToMD5(password);
		user.setPassword(encryptedPassword);
		signupRepository.save(user);

		LOGGER.info("Signup done");
		LOGGER.info("end");

	}

	boolean validateTime(Date otpDate) {
		LOGGER.info("Start");
		LOGGER.debug("OTP DATE:{}", otpDate);
		String currentDate = getCurrentDateTime();
		LOGGER.debug(" current date-> {}", currentDate);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = otpDate;
			d2 = format.parse(currentDate);
			LOGGER.debug(" Formatted current date-> {}", d2);
			long diff = d2.getTime() - d1.getTime();

			if (diff / (24 * 60 * 60 * 1000) >= 2) {
				return false;
			} else {
				if (diff / (60 * 60 * 1000) % 24 >= 2) {
					return false;
				} else {
					if (diff / (60 * 1000) % 60 >= 2) {
						return false;
					} else {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean validateOtpTime(User user) throws NoSuchAlgorithmException {
		LOGGER.info("Start generateSignupOtp");
		boolean timeStatus = false;
		User actualUser = userRepository.getUserByEmployeeId(user.getEmployeeId());
		LOGGER.debug("Actual user signup Otp time:{}", actualUser.getSignupOtpTime());
		if (validateTime(actualUser.getSignupOtpTime())) {
			timeStatus = true;
		}

		LOGGER.debug("otp on time:{}", timeStatus);
		LOGGER.info("end");
		return timeStatus;

	}

	public String getCurrentDateTime() {
		LOGGER.info("Start");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		LOGGER.info("end");
		return formatter.format(date);
	}

	@Transactional
	public User findUserByEmail(String email) {
		LOGGER.info("Finding user by email");
		return userRepository.getUserByEmail(email);

	}

	@Transactional
	public User findUserById(String userId){
		LOGGER.info("Finding user by employeeId");
		return userRepository.getUserByEmployeeId(userId);
	}

	@Transactional
	public void send(String email) {
		LOGGER.info("Start");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("Aastha.Teji@cognizant.com");
		mail.setFrom("Aastha.Teji@cognizant.com");
		mail.setSubject("Try");
		mail.setText("hi hello! plaease go ahead");
		javaMailSender.send(mail);
		LOGGER.info("end");
	}

}
