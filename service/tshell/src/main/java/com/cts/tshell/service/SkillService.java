package com.cts.tshell.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cts.tshell.bean.ReferenceSkill;
import com.cts.tshell.bean.Skill;
import com.cts.tshell.repository.ReferenceSkillRepository;
import com.cts.tshell.repository.SkillRepository;

@Service
public class SkillService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);

	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private ReferenceSkillRepository refernceSkillRepository;


/*
	public List<Skill> getTop4Skills() {
		LOGGER.info("start ");
		List<Skill> topSearchedSkills = skillRepository.fetchTopSearchedSkills();
		
		 * if(topSearchedSkills.size()>4){
		 * LOGGER.debug("top Searched Skills ->",topSearchedSkills); return
		 * topSearchedSkills.subList(0, 4); } else{ return topSearchedSkills; }
		 
		return topSearchedSkills;*/


	@Transactional
	public List<Skill> getTop4Skills() {
		LOGGER.info("start ");
		Page<Skill> topSearchedSkills = skillRepository.findBySkillTop4( PageRequest.of(0, 4));
		return topSearchedSkills.getContent();
	}		

	@Transactional
	public List<Skill> getSkills() {
		LOGGER.info("Starting getSkill() inside SkillRepository");
		return skillRepository.findAll();
	}


	@Transactional
	public Skill getSkillById(int skillId) {
		LOGGER.info("Starting getSkillbyId() inside SkillService");
		return skillRepository.findById(skillId);
	}
	
	@Transactional
	public Skill getSkillByName(String skillname) {
		LOGGER.info("Starting getSkillbyName() inside SkillService");
		LOGGER.debug("recived skillname from controller: " + skillname);
		return skillRepository.findByName(skillname);
	}



	@Autowired
	public void setSkillRepository(SkillRepository skillRepository) {
		this.skillRepository = skillRepository;
	}

/*	@Transactional
	public List<Skill> getTop4Skills() {
		LOGGER.info("start ");
		Page<Skill> topSearchedSkills = skillRepository.findBySkillTop4( PageRequest.of(0, 4));
		return topSearchedSkills.getContent();
	}
*/

	@Transactional
	public void updateSkillSearch(Skill skill) {
		LOGGER.debug("Updating {}'s SearchCount from {} to {}", skill.getName(), skill.getSearchCount(),
				skill.getSearchCount() + 1);
		skill.setSearchCount(skill.getSearchCount() + 1);
		LOGGER.info("Skill Updated");
		LOGGER.debug("Updated Skill", skill);
		skillRepository.save(skill);
	}

	@Transactional
	public void addOrUpdateSkill(Skill skill) {
		LOGGER.debug("Updating {}'s SearchCount from {} to {}", skill.getName());
		LOGGER.info("Skill Updated");
		LOGGER.debug("Updated Skill", skill);
		skillRepository.save(skill);
	}
	
	@Transactional
	public List<Skill> getRecent5Skills(Date date) {
		LOGGER.info("start");
		List<Skill> recent5Skills = skillRepository.fetchRecentSkills(date);
		LOGGER.debug("recentSkills -> " + recent5Skills);
		if (recent5Skills.size() >= 5) {
			LOGGER.debug("size of json data ->" + recent5Skills);
			return recent5Skills.subList(0, 5);
		}
		return recent5Skills;
	}

	public Date getCurrentTimeUsingCalendar() {
	    Calendar cal = Calendar.getInstance();
	    LOGGER.debug("CAL",cal);
	    cal.add(Calendar.DAY_OF_YEAR,-30);
	    Date date=cal.getTime();
		return date;
	}
	
	@Transactional
	public List<Skill> getTopAccessedtests() {
		LOGGER.info("start ");
		Page<Skill> topAccessedtests = skillRepository.findBySkillTop5( PageRequest.of(0, 5));
		return topAccessedtests.getContent();
	}
	
	@Transactional
	public long getSkillCount(){
		LOGGER.info("start");
		long skillCount = skillRepository.totalSkillCount();
		LOGGER.debug("Skill Count -> " , skillCount);
		
		return skillCount;
	}

	public List<ReferenceSkill> getRefernceSkill(int skillId) {
		LOGGER.info("Fetching Reference Skill Through skillService()");
		List<ReferenceSkill> result = refernceSkillRepository.findBySkillId(skillId);
		
//		List<Skill> referenceSkills = new ArrayList<Skill>();
//		for (ReferenceSkill referenceSkill : result) {
//			referenceSkills.add(referenceSkill.getReferenceSkill());
//			referenceSkills.add(referenceSkill.getClassifier());
//			LOGGER.debug("Skill Added {}", referenceSkill.getReferenceSkill());
//			
//		}
		return result;
	}
	
	@Transactional
	public void addReferenceSkill(ReferenceSkill referenceSkill) {		
//		Skill skill = 
		LOGGER.info("saving {}", referenceSkill);
		refernceSkillRepository.save(referenceSkill);
		
		LOGGER.info("ReferenceSkill Updated");
	}
	
	public void deleteReferenceSkill(int refskillId) {
		ReferenceSkill referenceSkill=new ReferenceSkill();
		referenceSkill.setId(refskillId);		
		refernceSkillRepository.delete(referenceSkill);		
	}

	public List<Skill> getSkillByKeys(String pressedKeys) {
		LOGGER.info("----------Start in get count service for dropdown skill name--------");
		return skillRepository.findSkillNames(pressedKeys);
	}
}


