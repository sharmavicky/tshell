package com.cts.tshell.service;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tshell.bean.ChangePasswordStatus;
import com.cts.tshell.bean.Role;
import com.cts.tshell.bean.Skill;
import com.cts.tshell.bean.User;
import com.cts.tshell.bean.Util;
import com.cts.tshell.repository.RoleRepository;
import com.cts.tshell.repository.SkillRepository;
import com.cts.tshell.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public RoleRepository roleRepository;
	
	@Autowired
    public SkillRepository skillRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional
	public User findByEmployeeId(String employeeId) {
		LOGGER.info("start");
		LOGGER.debug("employeeId" + employeeId);
		User user = userRepository.findByEmployeeId(employeeId);
		LOGGER.debug("Username: {}", user.getName());
		LOGGER.debug("Role: {}", user.getRole());
		LOGGER.info("end");
		return user;
	}

	@Transactional
	public ChangePasswordStatus changePassword(User user, String currentPassword, String newPassword) throws NoSuchAlgorithmException {
		LOGGER.info("start");
		LOGGER.debug("User " + user);
		LOGGER.debug("User Password : {} ", user.getPassword());
		ChangePasswordStatus status = new ChangePasswordStatus();
		
		String encryptedCurrentPassword = user.getPassword();
		String encryptedFormPassword = Util.encryptToMD5(currentPassword);
		String encryptedNewPassword = Util.encryptToMD5(newPassword);
		
		LOGGER.debug("encryptedFormPassword : {} ", encryptedFormPassword);
		LOGGER.debug("encryptedNewPassword : {} ", encryptedNewPassword);
		
		if (encryptedFormPassword.equals(encryptedNewPassword)) {
			status.setNewAndOldPasswordSame(true);
			status.setMessage("New Password and Current Password not same!");
			
		}
		
		if (!encryptedFormPassword.equals(encryptedCurrentPassword)) {
			LOGGER.info("inside the 2 checked");
			status.setCurrentPasswordIncorrect(true);
			status.setMessage("Current Password Invalid!");
			
		}
		
		if (encryptedFormPassword.equals(encryptedCurrentPassword) && !encryptedFormPassword.equals(encryptedNewPassword)) {
			LOGGER.info("inside the third checked");
			user.setPassword(encryptedNewPassword);
			userRepository.save(user);
			status.setPasswordChanged(true);
			status.setMessage("Password changed successfully!");
		}
		LOGGER.info("end");
		return status;
	}

	
	
	@Transactional
	public User getUser(String employeeId) {
		LOGGER.info("Start");
		LOGGER.debug("EmployeeId: {}", employeeId);
		LOGGER.info("End");
		return userRepository.findByEmpId(employeeId);
	}
	
	
	@Transactional
	public long getUserCount(){
		LOGGER.info("start");
		long userCount = userRepository.totalUserCount();
		LOGGER.debug("totalUserCount -> ",  userCount );
		
		return userCount; 
	}

	@Transactional
	public Boolean requestPasswordReset(String employeeId) throws NoSuchAlgorithmException {
		LOGGER.info("Start");
		User user = userRepository.findByEmployeeId(employeeId);
		LOGGER.debug(" user-> {}", user);
		if (user == null) {
			return false;
		}
		user.setOtp(Util.encryptToMD5(String.valueOf(Util.generateOTP())));
		user.setOtpGeneratedTime(Calendar.getInstance());
		userRepository.save(user);
		LOGGER.info("end");
		return true;
	}

	public boolean verifyOTP(String employeeId, String encryptedOTP) {
		LOGGER.info("Start");
		User user = userRepository.findByEmployeeId(employeeId);
		LOGGER.debug(" user-> {}", user);
		if (validateTime(user.getOtpGeneratedTime()) && user.getOtp().equals(encryptedOTP)) {
			LOGGER.info("end");
			return true;
			}
		return false;
	}

	boolean validateTime(Calendar dateStart) {
		LOGGER.info("Start");
		Calendar dateStop = Calendar.getInstance();
		LOGGER.debug(" start time -> {}", dateStart.getTimeInMillis());
		LOGGER.debug(" end time -> {}", dateStop.getTimeInMillis());
		long diff = (dateStop.getTimeInMillis() / 1000) - (dateStart.getTimeInMillis() / 1000);
		LOGGER.debug("difference of time --> {}", diff);
		if (diff > 120) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean restPassword(String employeeId, String encryptedPassword) {
		LOGGER.info("Start");
		User user = userRepository.findByEmployeeId(employeeId);
		LOGGER.debug(" user-> {}", user);
		if (user != null) {
			user.setPassword(encryptedPassword);
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public void save(User user){
		LOGGER.info("start");
		User currentUser = userRepository.findByEmployeeId(user.getEmployeeId());
		LOGGER.debug("currentUser: {} " , currentUser);
		String editUsername = user.getName();
		LOGGER.debug("editUsername: {} " , editUsername);
		currentUser.setName(editUsername);
		LOGGER.debug("currentUser: {} " , currentUser);
		String editEmail = user.getEmail();
		LOGGER.debug("editEmail: {} " , editEmail);
		currentUser.setEmail(editEmail);
		LOGGER.info("end");
		userRepository.save(currentUser);
	}
	
	@Transactional
	public void update(User user){
		LOGGER.info("start");
		User currentUser = userRepository.findByEmployeeId(user.getEmployeeId());
		LOGGER.debug("currentUser: {} " , currentUser);
		Role role = user.getRole();
		LOGGER.debug("role: {} " , role);
		role=roleRepository.findById(role.getId());
		LOGGER.debug("editRole: {} " , role.getId());
		currentUser.setRole(role);
		LOGGER.info("end");
		userRepository.save(currentUser);
	}
	
	@Transactional
	public User getUserId(String employeeId){
		return userRepository.fetchByEmployeeId(employeeId);
	}
	
	@Transactional
	public List<Role> getRoles(){
		return roleRepository.findAll();
	}
	
	@Transactional
	public List<Skill> getSkillName(String searchSkillName) {
		LOGGER.info("----------Start in get count service for dropdown skill name--------");
		return skillRepository.findSkillNames(searchSkillName);
	}
	
	@Transactional
	public void saveSkills(User user){
		LOGGER.info("start");
		List<Skill> skillList = new ArrayList<Skill>();
		int skillid;
		for(Skill skill : user.getSkills()){
			LOGGER.debug("incoming skill details " , skill);
			skillid=skill.getId();
			skill=skillRepository.findById(skillid);
			LOGGER.debug("fetching all skill details " , skill);
			skillList.add(skill);
		}
		user.setSkills(skillList);
		LOGGER.debug("user details with skill list " , user);
		LOGGER.info("end");
		userRepository.save(user);
	}
}
