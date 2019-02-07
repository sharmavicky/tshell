package com.cts.tshell.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cts.tshell.bean.Role;
import com.cts.tshell.repository.RoleRepository;

@Service
public class RoleService {
	private RoleRepository roleRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository){
		this.roleRepository=roleRepository;
	}
	
	
	@Transactional
	public Role getRoleByName(String role){
		LOGGER.info("Role service starts");
		return roleRepository.findRoleByName(role);
		
	}
	
}
