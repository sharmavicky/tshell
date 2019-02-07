package com.cts.tshell.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cts.tshell.bean.Skill;
import com.cts.tshell.service.CountPendingQuestionsService;

@RestController
@RequestMapping("/questionHome")
public class CountPendingQuestionsController {
	private CountPendingQuestionsService countPendingQuestionsService;
	private static final Logger LOGGER = LoggerFactory.getLogger(CountPendingQuestionsController.class);

	@Autowired
	public void setCountPendingQuestionsService(CountPendingQuestionsService countPendingQuestionsService) {
		this.countPendingQuestionsService = countPendingQuestionsService;
	}

	@GetMapping("/getcountReviewQ")
	public List<Skill> getCount() {
		LOGGER.info("------Start in count controller for getting pending questions count----");
		List<Skill> skill = countPendingQuestionsService.getPendingCount();
		LOGGER.debug("Count: {}", skill);
		return skill;
	}

	@PostMapping("/getSkillsOnSearch")
	public List<Skill> skillName(@RequestBody String searchSkillName) {
		LOGGER.debug("searchName: {}", searchSkillName);
		LOGGER.info("------Start in count controller for getting skill for dropdown----");
		List<Skill> skill = countPendingQuestionsService.getSkillName(searchSkillName);
		LOGGER.debug("skill: {}", skill);
		return skill;
	}

	/*@PostMapping("/getskillId")
	public int skillId(@RequestBody String SkillName) {
		LOGGER.debug("searchName: {}", SkillName);
		LOGGER.info("------Start in count controller for getting skillid---");
		int skill = countPendingQuestionsService.getSkillId(SkillName);
		LOGGER.debug("id: {}", skill);
		return skill;
	}*/
}
