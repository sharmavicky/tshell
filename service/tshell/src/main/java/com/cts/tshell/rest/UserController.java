package com.cts.tshell.rest;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tshell.bean.ChangePasswordStatus;
import com.cts.tshell.bean.Role;
import com.cts.tshell.bean.Skill;
import com.cts.tshell.bean.User;
import com.cts.tshell.bean.Views;
import com.cts.tshell.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
@RestController
@RequestMapping("/user")
public class UserController extends TshellController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	private void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/usercount")
	public long getTotalNumberofUsers() {
		LOGGER.info("start");
		long userCount = userService.getUserCount();
		LOGGER.debug("totaluserscount -> {}", userCount);
		return userCount;
	}
	/*
	 * @GetMapping("/getpassword/{employeeId}") public User
	 * getPassword(@PathVariable("employeeId") int employeeId) {
	 * LOGGER.info("start"); LOGGER.debug("employeeId" + employeeId); User user
	 * = userService.findByEmployeeId(employeeId); LOGGER.debug("Username: {}",
	 * user.getName()); LOGGER.debug("Role: {}", user.getRole());
	 * LOGGER.info("end"); return user; }
	 */

	@GetMapping("/changepassword/{employeeId}/{currentPassword}/{newPassword}")
	public ResponseEntity<ChangePasswordStatus> changePassword(@PathVariable String employeeId,
			@PathVariable String currentPassword, @PathVariable String newPassword) throws NoSuchAlgorithmException {
		LOGGER.info("start");
		LOGGER.debug("employeeId:{}; currentPassword:{}; newPassword:{}", employeeId, currentPassword, newPassword);
		User user = userService.findByEmployeeId(employeeId);
		LOGGER.debug("User:{} ", user);
		// userService.changePassword(user, currentPassword, newPassword);
		LOGGER.debug("User password {} ", user.getPassword());
		ChangePasswordStatus status = userService.changePassword(user, currentPassword, newPassword);

		LOGGER.info("end");
		return new ResponseEntity<ChangePasswordStatus>(status, HttpStatus.OK);

	}
	

	@GetMapping("/requestpasswordreset/{employeeId}")
	public boolean requestPasswordReset(@PathVariable String employeeId) throws NoSuchAlgorithmException {
		LOGGER.info("Start");
		return userService.requestPasswordReset(employeeId);
	}

	@GetMapping("/verifyotp/{employeeId}/{encryptedOTP}")
	public boolean verifyOTP(@PathVariable String employeeId, @PathVariable String encryptedOTP) {
		LOGGER.info("Start");
		return userService.verifyOTP(employeeId, encryptedOTP);
	}

	@GetMapping("/resetPassword/{employeeId}/{encryptedPassword}")
	public boolean resetPassword(@PathVariable String employeeId, @PathVariable String encryptedPassword) {
		LOGGER.info("Start");
		return userService.restPassword(employeeId, encryptedPassword);
	}
	@GetMapping("/getUser/{employeeId}")
	public String getUserId(@PathVariable("employeeId") String employeeId) throws JsonProcessingException {
		LOGGER.info("start");
		LOGGER.debug("employeeId: {} " , employeeId);
		LOGGER.info("end");
		User user=userService.getUserId(employeeId);
		ObjectMapper mapper = new ObjectMapper();
		String result=mapper.writerWithView(Views.Internal.class).writeValueAsString(user);
		return result;
	}
	
	@PostMapping("/save")
		public void saveUser(@RequestBody User user){
		LOGGER.info("start");
		LOGGER.debug("User: {} " , user.getName());
		LOGGER.debug("User: {} " , user.getEmail());
		LOGGER.info("end");
		userService.save(user);
	}
	
	@GetMapping("/getRoles")
		public List<Role> getRoles(){
		LOGGER.info("start");
		LOGGER.debug("roleId: {} ");
		LOGGER.info("end");
		return userService.getRoles();
		}
	
	
	@PostMapping("/update")
	public void save(@RequestBody User user){
		LOGGER.info("start");
		LOGGER.debug("User: {} " , user.getRole());
		LOGGER.info("end");
		userService.update(user);
	}
	
	@PostMapping("/getSkillsOnSearch")
	public List<Skill> skillName(@RequestBody String searchSkillName) {
		LOGGER.debug("searchName: {}", searchSkillName);
		LOGGER.info("------For getting skill ----");
		List<Skill> skill = userService.getSkillName(searchSkillName);
		LOGGER.debug("skill: {}", skill);
		return skill;
	}

	@PostMapping("/saveskill")
	public void skillNames(@RequestBody User user ){
		userService.saveSkills(user);
	}
}
