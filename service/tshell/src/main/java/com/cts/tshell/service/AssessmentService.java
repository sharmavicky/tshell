package com.cts.tshell.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.tshell.bean.Assessment;
import com.cts.tshell.bean.AssessmentQuestion;
import com.cts.tshell.bean.AssessmentQuestionOption;
import com.cts.tshell.bean.Option;
import com.cts.tshell.bean.Skill;
import com.cts.tshell.bean.TopicWiseScore;
import com.cts.tshell.bean.User;
import com.cts.tshell.repository.AssessmentQuestionOptionRepository;
import com.cts.tshell.repository.AssessmentQuestionRepository;
import com.cts.tshell.repository.AssessmentRepository;
import com.cts.tshell.repository.QuestionRepository;
import com.cts.tshell.repository.SkillRepository;
import com.cts.tshell.repository.TopicRepository;
import com.cts.tshell.repository.UserRepository;

@Service
public class AssessmentService {


	private final static Logger LOGGER = LoggerFactory.getLogger(AssessmentService.class);
	@Autowired
	private AssessmentRepository assessmentRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private UserRepository userRepository;



	@Autowired
	private AssessmentQuestionRepository assessmentQuestionRepository;

	@Autowired
	private AssessmentQuestionOptionRepository assessmentQuestionOptionRepository;
	
	@Transactional
	public long getAssessmentCount(){
		LOGGER.info("start");
		long assessmentCount = assessmentRepository.totalAssessmentsCount();
		LOGGER.debug("AssessmentCount -> " + assessmentCount);
		
		return assessmentCount;
	}

	
	@Transactional
	public List<Assessment> findTop5AssessmentBasedSkill(int skillId) {
		LOGGER.info("Start");
		LOGGER.info("Skill Id : " + skillId);
		Page<Assessment> assessmentsPage = assessmentRepository.findTop5BySkill(skillId, PageRequest.of(0, 8));
		List<Assessment> assessments = assessmentsPage.getContent();
		LOGGER.debug("Assessments : {}", assessments);
		LOGGER.info("End");
		return assessments;
	}

	@Transactional
	public List<Assessment> getAssessmentsOfUserById(String userId){
		List<Assessment> assesments = assessmentRepository.findUserHistory(userId);

		return assesments;
	}
	// public List<Assessment> findTop5AssessmentBasedSkill(int skillId){
	// LOGGER.debug("SkillId : "+skillId);
	// return assessmentRepository.findTop5BySkill(skillId, new PageRequest(0,
	// 5));
	// }


	public String startAssessment(Assessment assessment) {
		LOGGER.info("START : Saving Start of Assessment in  startAssesment() Service  of AssessmentService");
		LOGGER.debug("Assesment Object : {}", assessment);
		LOGGER.debug("Assesment taken in Skill Id  : {}", assessment.getSkill().getId());
		Skill skill = skillRepository.findSkillById(assessment.getSkill().getId());
		LOGGER.debug("Employee Id : {}", assessment.getUser().getEmployeeId());
		User user = userRepository.findUserByEmployeeId(assessment.getUser().getEmployeeId());
		LOGGER.debug("User : {}", user);
		assessment.setSkill(skill);
		assessment.setUser(user);
		assessmentRepository.save(assessment);
		LOGGER.debug(" Latest Inserted Id: {}" + assessment.getId());
		LOGGER.info("End : START : startAssesment() of AssessmentController");
		LOGGER.info("End : startAssesment() Service  of AssessmentService");
		return "{\"id\":" + assessment.getId() + ",\"skillName\":\"" + assessment.getSkill().getName() + "\"" + "}";
	}

	@Transactional
	public void saveAssessmentResponse(AssessmentQuestion assessmentQuestion) {

		LOGGER.info("START : saveAssessmentResponse() Service  of AssessmentService");
		int assessmentId = assessmentQuestion.getAssessment().getId();
		int questionId = assessmentQuestion.getQuestion().getId();
		LOGGER.debug("Assessment ID {}", assessmentId + " Question Id : {}", questionId);
		int[] assmentQuestionId = assessmentQuestionRepository.fetchAssesmentQuestionId(assessmentId, questionId);

		if (assmentQuestionId.length == 0) {
			AssessmentQuestion question = new AssessmentQuestion();
			question.setQuestion(assessmentQuestion.getQuestion());
			question.setAssessmentQuestionOption(assessmentQuestion.getAssessmentQuestionOption());
			Assessment as = assessmentRepository.findAssessmentById(assessmentQuestion.getAssessment().getId());
			question.setAssessment(as);
			question.setCorrect(assessmentQuestion.isCorrect());
			assessmentQuestionRepository.save(question);

			LOGGER.info("Starting AssessmentQuestionOption ");

			for (Option option : question.getQuestion().getOptionList()) {
				AssessmentQuestionOption opt = new AssessmentQuestionOption();
				opt.setAssessmentQuestion(question);
				opt.setAssessmentOption(option);
				opt.setSelected(option.isResponse());
				LOGGER.info("Starting saving of Assessment Question's Option ");
				assessmentQuestionOptionRepository.save(opt);
			}

		} else {
			int assessmentQuestionId = assmentQuestionId[0];
			AssessmentQuestion question = assessmentQuestionRepository.findAssessmentQuestionById(assessmentQuestionId);
			question.setQuestion(assessmentQuestion.getQuestion());

			for (Option option : question.getQuestion().getOptionList()) {
				int assessmentQuestionOptionId = assessmentQuestionOptionRepository
						.fetchAssesmentQuestionOptionId(question.getId(), option.getId());
				System.out.println("AssessmentQOption Id : " + assessmentQuestionOptionId);
				int optionId = option.getId();
				boolean selected = option.isResponse();
				System.out.println("Starting save ");
				assessmentQuestionOptionRepository.saveAssesmentQuestionOption(assessmentQuestionId, optionId, selected,
						assessmentQuestionOptionId);
			}
		}
	}

	public void submitAssesment(Assessment assessment) {
		LOGGER.info("START : Saving Assessment Submission submitAssesment() Service  of AssessmentService");
		Assessment as = assessmentRepository.findAssessmentById(assessment.getId());
		as.setEndTime(assessment.getEndTime());
		assessmentRepository.save(as);
	}

	
	public Assessment evaluateScore(int assessmentId) {
		LOGGER.info("START : evaluateScore() Service  of AssessmentService");
		Assessment assessment = assessmentRepository.fetchAssesmentDetailById(assessmentId);
		int score = 0;
		Set<AssessmentQuestion> assessmentQuestions = new LinkedHashSet<>(assessment.getAssessmentQuestions());
		for (AssessmentQuestion assessmentQuestion : assessmentQuestions) {
			LOGGER.info("Taking Assessment Question");
			int counter = 0;
			boolean answerStatus = false;
			int optionsSize = assessmentQuestion.getAssessmentQuestionOption().size();
			for (AssessmentQuestionOption assessmentQuestionOption : assessmentQuestion.getAssessmentQuestionOption()) {
				LOGGER.info("Taking AssessmentQuestionOption");
				if (assessmentQuestionOption.getAssessmentOption().isAnswer() == assessmentQuestionOption
						.isSelected()) {
					counter++;
				}
				if (counter == optionsSize) {
					score++;
					answerStatus = true;
				}
			}
			assessmentQuestion.setCorrect(answerStatus);
			LOGGER.info("Saving Answer Status for the Question");
			assessmentQuestionRepository.save(assessmentQuestion);
		}

		assessment.setScore(score);
		LOGGER.info("Saving Assessment Score");
		assessmentRepository.save(assessment);
		LOGGER.info("Score saved Successfully");
		LOGGER.info("End : evaluateScore() of AssessmentService");
		 return assessment;
	}

	public List<TopicWiseScore> getTopicWiseScore(int assessmentId) {
		LOGGER.info("Start : getTopicWiseScore() of AssessmentService");
		LOGGER.debug("Assessment ID : {}", assessmentId);
		LOGGER.info("End : getTopicWiseScore() of AssessmentService");
		return assessmentRepository.getTopicWiseQuestionCount(assessmentId);
	}
}
