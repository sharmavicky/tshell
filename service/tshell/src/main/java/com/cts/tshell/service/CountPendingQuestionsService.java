package com.cts.tshell.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cts.tshell.bean.Skill;
import com.cts.tshell.repository.SkillRepository;

@Service
public class CountPendingQuestionsService {

	private SkillRepository skillRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(CountPendingQuestionsService.class);

	@Autowired
	public void setSkillRepository(SkillRepository skillRepository) {
		this.skillRepository = skillRepository;
	}

	@Transactional
	public List<Skill> getPendingCount() {
		LOGGER.info("----------Start in get count service for pending count--------");
		return skillRepository.findPendingQuestionsCount();
	}

	@Transactional
	public List<Skill> getSkillName(String searchSkillName) {
		LOGGER.info("----------Start in get count service for dropdown skill name--------");
		return skillRepository.findSkillNames(searchSkillName);
	}

	/*@Transactional
	public int getSkillId(String SkillName) {
		LOGGER.info("----------Start in get count service for getting skill id--------");
		return skillRepository.findSkillId(SkillName);
	}*/
}
