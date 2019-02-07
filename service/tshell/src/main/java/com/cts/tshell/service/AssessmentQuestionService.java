package com.cts.tshell.service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tshell.bean.Option;
import com.cts.tshell.bean.Question;
import com.cts.tshell.bean.Topic;
import com.cts.tshell.repository.QuestionRepository;
import com.cts.tshell.repository.SkillRepository;
import com.cts.tshell.repository.TopicRepository;

@Service
public class AssessmentQuestionService {

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private QuestionRepository questionRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentQuestionService.class);

	private static final float MAX_QUESTIONS = 40;

	public List<Question> getQuestionById(int questionId) {
		LOGGER.info("START :Getting all Question By Id from getQuestionById()  of QuestionService");
		LOGGER.debug("Question Id :  {}", questionId);
		List<Question> questionList = questionRepository.fetchQuestionById(questionId);

		for (Question question : questionList) {
			for (Option option : question.getOptionList()) {
				option.setAnswer(false);
			}
		}
		LOGGER.info("Returning Question from Service call");
		return questionList;
	}

	public Set<Integer> fetchQuestionsID(int skillId) {
		LOGGER.info("START :Getting all Question Ids from fetchQuestionsID()  of QuestionService");
		LOGGER.debug("SkillId :  {}", skillId);
		Set<Integer> questionIds = new HashSet<Integer>();

		List<Topic> topics = topicRepository.getTopicIdAndWeightage(skillId);
		for (Topic topic : topics) {
			int topicId = topic.getId();
			LOGGER.debug("TopicId :  {}", topicId);
			int percentage = topic.getPercentage();
			int numbersNeeded = (int) Math.round(percentage * MAX_QUESTIONS / 100);
			LOGGER.debug("Questions Needed :  {}", numbersNeeded);
			long[] mapping = questionRepository.getQuestionId(topicId, skillId);
			int max = mapping.length;
			LOGGER.debug("Questions Available :  {}", max);
			if (max < numbersNeeded) {
				throw new IllegalArgumentException("The number of questions available in "
						+ "the Database is less than the weightage required for the topic : " + topic.getName());
			}
			Random rng = new Random();
			// Note: use LinkedHashSet to maintain insertion order
			Set<Integer> generated = new HashSet<Integer>();

			while (generated.size() < numbersNeeded) {
				int next = rng.nextInt(max);
				// As we're adding to a set, this will automatically do a
				// containment check
				int questionId = (int) mapping[next];
				generated.add(questionId);
			}
			questionIds.addAll(generated);
			LOGGER.debug("Needed Question Ids Randomised and Added for Topic {}", topicId);
		}
		LOGGER.info("Returning the randoming generated Question Ids");
		if (questionIds.size() == MAX_QUESTIONS) {
			return questionIds;
		} else {
			throw new IllegalArgumentException(
					"The number of questions Ids generated  by randomisation = "+questionIds.size()+"  is not equal to required Ids =  "
							+ MAX_QUESTIONS);
		}
	}

	public List<Question> getAllQuetions() {
		return questionRepository.findAll();
	}

}
