package com.cts.tshell.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tshell.bean.Assessment;
import com.cts.tshell.bean.AssessmentQuestion;
import com.cts.tshell.bean.TopicWiseScore;
import com.cts.tshell.bean.Views;
import com.cts.tshell.service.AssessmentService;
import com.cts.tshell.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {


private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentController.class);
	
	@Autowired
	private AssessmentService assessmentService;

	@Autowired
	public UserService userService;


//	@RequestMapping(value = "/top5list/{skillId}", method = RequestMethod.GET)
//	public List<Assessment> findTop5Performers(@PathVariable("skillId") int skillId){
//		LOGGER.info("AssessmentController->findTop5PerformersOfSkill");
//		return assessmentService.findTop5AssessmentBasedSkill(skillId);
//	}
//	
	@GetMapping("/top5list/{skillId}")
	public String findTop5Performers(@PathVariable("skillId") int skillId) throws JsonProcessingException{
		LOGGER.info("Start");
		LOGGER.debug("Skill Id:"+skillId);
		List<Assessment> assessments=assessmentService.findTop5AssessmentBasedSkill(skillId);	
		LOGGER.debug("Assessments : {}",assessments);
		ObjectMapper mapper = new ObjectMapper();
		String result=mapper.writerWithView(Views.Public.class).writeValueAsString(assessments);
		LOGGER.info("End");
		return result;
	}
	
	@GetMapping("/assessmentcount")
	public long getTotalNumberofAssessments() {
		LOGGER.info("start");
		long assessmentCount = assessmentService.getAssessmentCount();
		LOGGER.debug("totalAssessments -> {}", assessmentCount);
		return assessmentCount;
	}
	
	@GetMapping("/getAssessment/{userId}")
	public String getAssessmentsOfUserById(@PathVariable("userId") String userId) throws JsonProcessingException{
		LOGGER.info("start");
		LOGGER.debug("userId: {} " , userId);
		LOGGER.info("end");
		List<Assessment> assessments=assessmentService.getAssessmentsOfUserById(userId);
		ObjectMapper mapper = new ObjectMapper();
		String result=mapper.writerWithView(Views.Public.class).writeValueAsString(assessments);
		return result;
	}



	@PostMapping("/start")
	public String startAssesment(@RequestBody Assessment assessment) {

		LOGGER.info("START :Creating Entry for assessment from startAssesment() of AssessmentController");
		LOGGER.debug("Assesment Object : {}", assessment);
		return assessmentService.startAssessment(assessment);

	}

	@PostMapping("/saveresponse")
	public void saveAssesmentResponse(@RequestBody AssessmentQuestion assessmentQuestion) {

		LOGGER.info(
				"START : Saving Response for Assessment Question from saveAssesmentResponse() of AssessmentController");
		LOGGER.debug("AssessmentQuestionOption Object : {}", assessmentQuestion);
		assessmentService.saveAssessmentResponse(assessmentQuestion);
	}

	@PostMapping("/submit")
	public void submitAssesment(@RequestBody Assessment assessment) {

		LOGGER.info("START : Submitting the assessment submitAssesment() of AssessmentController");
		LOGGER.debug("Assessment Object : {}", assessment);
		assessmentService.submitAssesment(assessment);
		LOGGER.info("Assessment with End Time And Responses Saved Successfully.......");
		LOGGER.info("End : Saved the assessment submitAssesment() of AssessmentController");
	}

	@GetMapping("/topicwisescore/{assessmentId}")
	public List<TopicWiseScore> getTopicwiseScore(@PathVariable int assessmentId) {
		LOGGER.info("Start : getTopicWiseScore() of AssessmentController");
		LOGGER.debug("Assessment Id : {}", assessmentId);
		LOGGER.info("Start Evaluating Score .......");
		assessmentService.evaluateScore(assessmentId);
		LOGGER.info("Evaluating Score Ended...........");
		LOGGER.info("-----------------------------------");
		LOGGER.info("Getting Topic Wise Score.........");
		List<TopicWiseScore> topicWiseScore = assessmentService.getTopicWiseScore(assessmentId);
		LOGGER.info("End : getTopicWiseScore() of AssessmentController");
		return topicWiseScore;
	}

	// This Method is just to view the data for get mapping in Browser no call
	// is ever made to this method from angular.
	@GetMapping("/score/{assessmentId}")
	public String evaluateAssesment(@PathVariable("assessmentId") int assessmentId) throws JsonProcessingException {

		LOGGER.info("START : Evaluating Score from evaluateAssesment() of AssessmentController");
		LOGGER.debug("Assessment Id : ", assessmentId);
		Assessment assessment = assessmentService.evaluateScore(assessmentId);
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writerWithView(Views.Public.class).writeValueAsString(assessment);
		return result;

	}


}
