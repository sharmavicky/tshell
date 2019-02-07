package com.cts.tshell.rest;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tshell.bean.ReferenceSkill;
import com.cts.tshell.bean.Skill;
import com.cts.tshell.bean.Topic;
import com.cts.tshell.service.SkillService;
import com.cts.tshell.service.TopicService;

@RestController
@RequestMapping("/skill")
public class SkillController extends TshellController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SkillController.class);
	private SkillService skillService;
	private TopicService topicService;

	@Autowired
	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	@RequestMapping(value = "/skills", method = RequestMethod.GET)
	public List<Skill> getAllSkills() {
		LOGGER.debug("Fetching All Skills from database");
		LOGGER.info("All Skills Available" + skillService.getSkills());
		LOGGER.info("Returning with Skills");
		return skillService.getSkills();
	}

	@RequestMapping(value = "/getSkillsOnSearch", method = RequestMethod.GET)
	public List<Skill> skillName(@RequestBody String pressedKeys) {
		LOGGER.debug("searchName: {}", pressedKeys);
		LOGGER.info("------Start in count controller for getting skill for dropdown----");
		List<Skill> skill = skillService.getSkillByKeys(pressedKeys);
		LOGGER.debug("skill: {}", skill);
		return skill;
	}

	@RequestMapping(value = "/updateSkillSearch", method = RequestMethod.POST)
	public void updateSkillSearch(@RequestBody int skillId) {
		LOGGER.debug("Accessing Database to update search count of {} with {}");
		LOGGER.info("Updating skill searchCount from {} to {} ");
		Skill skill = skillService.getSkillById(skillId);
		skillService.updateSkillSearch(skill);
		LOGGER.debug("Existing from updateSearch Skill with skill \n{}", skill);
	}

	@RequestMapping(value = "/deleteTopic", method = RequestMethod.POST)
	public void deleteSkillTopic(@RequestBody String string) {
		LOGGER.debug("Initialising Topic deletion Process");
		String[] splited = string.split("\\s+");
		LOGGER.debug("Fetching Object of Skill Object of {}", splited[0]);
		Skill skill = new Skill();
		skill = skillService.getSkillById(Integer.parseInt(splited[1]));
		LOGGER.debug("Skill Object Fetched");
		LOGGER.debug("Initialising Topics of skill");

		for (Topic topics : skill.getTopics()) {
			if (topics.getName().equals(splited[0])) {
				LOGGER.debug("Deleting {}", topics);
				topicService.deleteTopic(topics);
				LOGGER.debug("{} Deleted", topics);
			}
		}
		LOGGER.debug("Exiting Topic deletion Process");
	}

	@RequestMapping(value = "/updateSkill", method = RequestMethod.POST)
	public int UpdateSkill(@RequestBody Skill skill) {
		LOGGER.info("starting insertneSkills");
		LOGGER.debug("Recived skill from Browser: " + skill);
		int addStatus = 0;
		Skill checkSkill = skillService.getSkillByName(skill.getName());

		List<Topic> topics = skill.getTopics();
		LOGGER.debug("Recived topics from Browser: " + topics);
		Skill skill2 = skillService.getSkillByName(skill.getName());
		LOGGER.debug("Recived skill from sevice: " + skill2);
		for (Topic topic : topics) {
			topic.setSkill(skill2);
			topicService.saveTopic(topic);
		}

		if (checkSkill == null) {
			skillService.addOrUpdateSkill(skill);
			skill.setTopics(topics);
			LOGGER.info("ending Update Skill");
			addStatus = 2;
		} else {
			addStatus = 1;
		}
		return addStatus;
	}

	@RequestMapping(value = "/addskill", method = RequestMethod.POST)
	public int addOrUpdateSkill(@RequestBody Skill skill) {
		LOGGER.info("starting insertnewSkills");
		String skillName = skill.getName();
		Skill skill1 = skillService.getSkillByName(skillName);
		int addStatus;
		if (skill1 != null) {
			addStatus = 1;
		} else {
			List<Topic> topics = skill.getTopics();
			LOGGER.debug("Recived skill from Browser: " + skill);
			LOGGER.debug("Recived topics from Browser: " + topics);
			skillService.addOrUpdateSkill(skill);
			Skill skill2 = skillService.getSkillByName(skill.getName());
			LOGGER.debug("Recived skill from sevice: " + skill2);
			for (Topic topic : topics) {
				topic.setSkill(skill2);
				topicService.saveTopic(topic);
			}
			skill.setTopics(topics);
			addStatus = 2;
			LOGGER.info("ending inserting Skill");
		}
		return addStatus;
	}

	@RequestMapping(value = "/top4searchedskills", method = RequestMethod.GET)
	public List<Skill> getTop4SearchedSkills() {
		LOGGER.info("start ");
		List<Skill> top4SearchedSkills = skillService.getTop4Skills();
		LOGGER.debug("Top 4 Searched Skills ->", top4SearchedSkills);
		return top4SearchedSkills;
	}

	@RequestMapping(value = "/recentskilllist", method = RequestMethod.GET)
	public List<Skill> getRecentSkills() {
		LOGGER.info("start");
		Date date = skillService.getCurrentTimeUsingCalendar();
		List<Skill> skills = skillService.getRecent5Skills(date);
		LOGGER.debug("SkillController -> {}", skills);
		return skills;
	}

	@GetMapping("/gettop5tests")

	// @RequestMapping(value = "/gettop5tests", method = RequestMethod.GET)

	public List<Skill> getTopAccessedtests() {
		LOGGER.info("start");
		List<Skill> topAccessedtests = skillService.getTopAccessedtests();
		LOGGER.debug("top5 accessed tests ->", topAccessedtests);
		return topAccessedtests;

	}

	@RequestMapping(value = "/skillcount", method = RequestMethod.GET)
	public long getSkillCount() {
		LOGGER.info("start");
		long skillCount = skillService.getSkillCount();
		LOGGER.debug("Total Skill Count -> {}", skillCount);
		return skillCount;
	}

	@RequestMapping(value = "/referenceskill/{skillId}", method = RequestMethod.GET)
	public List<ReferenceSkill> getReferenceSkill(@PathVariable("skillId") int skillId) {
		LOGGER.info("Fetching Reference Skill Through getReferenceSkill()");
		LOGGER.debug("Fetching Result for fetching skill for skill ID : {}", skillId);
		List<ReferenceSkill> result = skillService.getRefernceSkill(skillId);
		return result;
	}

	@RequestMapping(value = "/addreferenceskill", method = RequestMethod.POST)
	public void addReferenceSkill(@RequestBody ReferenceSkill referenceSkill) {
		LOGGER.info("Adding ReferenceSkils");
		LOGGER.debug("Fetching Result for fetching skill for skill ID : {}");
		LOGGER.info("Adding ReferenceSkils itration {}", referenceSkill.getId());
		skillService.addReferenceSkill(referenceSkill);

	}

	@RequestMapping(value = "/deleteReferenceskill/{refskillId}", method = RequestMethod.GET)
	public void deleteReferenceSkill(@PathVariable("refskillId") int refskillId) {
		LOGGER.info("Fetching Reference Skill Through getReferenceSkill()");
		LOGGER.debug("Fetching Result for fetching skill for skill ID : {}", refskillId);
		skillService.deleteReferenceSkill(refskillId);
	}

	@RequestMapping(value = "/skillbyname/{name}", method = RequestMethod.GET)
	public Skill getSkillsByName(@PathVariable String name) {
		LOGGER.info("start get SkillsbyName");
		Skill skill = skillService.getSkillByName(name);
		LOGGER.debug("SkillController -> {}", skill);
		return skill;
	}
}
