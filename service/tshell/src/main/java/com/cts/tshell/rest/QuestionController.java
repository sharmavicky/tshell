package com.cts.tshell.rest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.cts.tshell.Search;
import com.cts.tshell.bean.Option;
import com.cts.tshell.bean.Question;
import com.cts.tshell.bean.Topic;
import com.cts.tshell.bean.User;
import com.cts.tshell.bean.Views;
import com.cts.tshell.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

@ControllerAdvice
@RestController
@RequestMapping("/question")
@SessionAttributes("questionsList")
public class QuestionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);
	private QuestionService questionService;

	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@GetMapping("/getTopics/{skillId}")
	public List<Topic> getTopics(@PathVariable int skillId) {
		LOGGER.info("Start Question Controller");
		List<Topic> topics = questionService.getAllTopics(skillId);
		LOGGER.debug("Topics :{}" + topics);
		return topics;
	}

	@PostMapping("/addQuestion")
	public void insertQuestion(@RequestBody Question question) {
		LOGGER.info("starting insertQuestion method");
		questionService.saveQuestion(question);
		LOGGER.info("end");
	}

	@GetMapping("/getUser/{userId}")
	public List<User> getUser(@PathVariable String userId) {
		LOGGER.info("starting getUser method");
		List<User> user = questionService.getUser(userId);
		LOGGER.debug("User details are:{}" + user);
		LOGGER.info("end");
		return user;

	}

	@GetMapping("/contributed/{employeeId}")
	public List<Question> totalQuestionContributed(@PathVariable("employeeId") String employeeId) {
		LOGGER.info(" START");
		List<Question> totalQuestion = questionService.findTotalQuestionContributed(employeeId);
		LOGGER.debug("total no of question contributed for each subject : {} ", totalQuestion);
		LOGGER.info("END");
		return totalQuestion;

	}

	@GetMapping("/questioncount")
	public long getQuestionCount() {
		LOGGER.info("start");
		long questionCount = questionService.getQuestionCount();
		LOGGER.debug("QuestionCount  -> {}", questionCount);
		return questionCount;
	}

	@PostMapping("/option/add")
	public String addOption(@RequestBody Option option) throws JsonProcessingException {
		LOGGER.info("start");
		LOGGER.debug("Option: {}", option);
		Question question = questionService.saveOption(option);
		LOGGER.info("Getting New Question");
		LOGGER.debug("Question: {}", question);
		ObjectMapper mapper = new ObjectMapper();
		String questionString = mapper.writerWithView(Views.Public.class).writeValueAsString(question);
		LOGGER.debug("Question String : {}", questionString);
		LOGGER.info("end");
		return questionString;
	}

	@GetMapping("/review/{skillId}")
	public String getSingleReviewQuestion(@PathVariable int skillId) throws JsonProcessingException {
		LOGGER.info("START");
		LOGGER.debug("SkillId {} ", skillId);
		List<Question> questionList = questionService.getSingleReviewQuestion(skillId);
		LOGGER.debug("Question List : {}", questionList);
		ObjectMapper mapper = new ObjectMapper();
		String questions = mapper.writerWithView(Views.Public.class).writeValueAsString(questionList);
		LOGGER.debug("Questions as String : {}", questions);
		LOGGER.info("END");
		return questions;
	}

	@GetMapping("/option/delete/{id}")
	public boolean deleteOptionById(@PathVariable int id) {
		LOGGER.info("START");
		boolean optionDeleteStatus = false;
		LOGGER.debug("Option Delete Status(initial): {}", optionDeleteStatus);
		optionDeleteStatus = questionService.deleteOption(id);
		LOGGER.debug("Option Delete Status(final): {}", optionDeleteStatus);
		LOGGER.info("END");
		return optionDeleteStatus;
	}

	@GetMapping("/updatestatus/{questionId}/{status}/{skillId}")
	public String updateStatus(@PathVariable int questionId, @PathVariable String status, @PathVariable int skillId)
			throws JsonProcessingException {
		LOGGER.info("START");
		LOGGER.debug("Question Id {} ", questionId);
		LOGGER.debug("Question Status {} ", status);
		LOGGER.debug("Skill Id {} ", skillId);
		questionService.updateStatus(questionId, status);
		List<Question> questionList = questionService.getSingleReviewQuestion(skillId);
		LOGGER.debug("Question List : {}", questionList);
		ObjectMapper mapper = new ObjectMapper();
		String questions = mapper.writerWithView(Views.Public.class).writeValueAsString(questionList);
		LOGGER.debug("Questions as String : {}", questionList);
		LOGGER.info("END");
		return questions;
	}

	@PostMapping("/searchedquestionslist")
	public String getAllQuestions(@RequestBody Search search) throws JsonProcessingException {
		LOGGER.info("Start Fetching Questions Based On Keyword .");
		String searchedQuestion = search.getKeyword();
		List<Question> allQuestions = questionService.fetchQuestionBasedOnKeyword(searchedQuestion);
		ObjectMapper mapper = new ObjectMapper();
		String questions = mapper.writerWithView(Views.Public.class).writeValueAsString(allQuestions);
		LOGGER.info("End Fetching Questions Based On Keyword .");
		return questions;

	}

	@PostMapping("/save")
	public boolean saveOptionDescription(@RequestBody Option option) {
		LOGGER.info("Starting Controller");
		LOGGER.info("Ending Controller");
		boolean isOptionEdited = questionService.saveOptionDescription(option);
		LOGGER.debug("isOptionEdited: {}", isOptionEdited);
		LOGGER.info("END");
		return isOptionEdited;
	}

	@PostMapping("/update")
	public String editQuestion(@RequestBody Question question) throws JsonProcessingException {
		LOGGER.info("START");
		LOGGER.debug("Question {}", question);
		Question updatedQuestion = questionService.updateQuestion(question);
		LOGGER.debug("Updated Question {}", updatedQuestion);
		ObjectMapper mapper = new ObjectMapper();
		String uQuestion = mapper.writerWithView(Views.Public.class).writeValueAsString(updatedQuestion);
		LOGGER.debug("Updated Question as String {}", uQuestion);
		LOGGER.info("END");
		return uQuestion;
	}

	@GetMapping("/option/updatestatus/{optionId}")
	public boolean updateOptionStatus(@PathVariable int optionId) {
		LOGGER.info("START");
		LOGGER.debug("Option Id {}", optionId);
		boolean modified = questionService.modifyOptionStatus(optionId);
		LOGGER.debug("Is Option status modified {}", modified);
		return modified;
	}

	// Creating template wrt to skillId
	@GetMapping("/template/{skillId}")
	public void createTemplate(@PathVariable int skillId, HttpServletResponse response) throws IOException {
		LOGGER.info("createTemplate() is called");
		List<Topic> topics = questionService.getNamesOfTopics(skillId);
		String[] array = topics.toArray(new String[0]);
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; fileName=Question_Bank_Template.csv");
		Writer writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
		@SuppressWarnings("resource")
		CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		String[] heading = { "Topic", "Question", "Option 1", "Option 1 is answer?", "Option 2", "Option 2 is answer?",
				"Option 3", "Option 3 is answer?", "Option 4", "Option 4 is answer?", "Option 5",
				"Option 5 is answer?", };
		for (int i = 0; i < 11; i++) {
			if (i == 0 || i == 10) {
				csvWriter.writeNext(new String[] { "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#" });
			} else if (i == 3) {
				csvWriter.writeNext(new String[] { "#", "", "Use the below values for Topics", "", "", "", "", "", "",
						"", "", "#" });
				Arrays.toString(array);
				csvWriter.writeNext(new String[] { "#", "" + String.join("              ", array) + "", "", "", "", "",
						"", "", "", "", "", "#" });

			} else {
				csvWriter.writeNext(new String[] { "#", "", "", "", "", "", "", "", "", "", "", "#" });
			}
		}

		csvWriter.writeNext(heading);
		writer.flush();
		csvWriter.close();
		writer.close();
		LOGGER.info("createTemplate() execution is completed!");
	}

	// Uploading csv file
	@PostMapping("/upload")
	public List<Question> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("skillId") String selectedSkillId, ModelMap model, HttpServletRequest request) {
		LOGGER.info("uploadFile() is called");
		int skillId = Integer.parseInt(selectedSkillId);
		LOGGER.debug("Skill ID is {}", skillId);
		LOGGER.debug("Content type is {}", file.getContentType());
		request.getSession(true);
		List<Question> questionsList = new ArrayList<Question>();
		questionsList = questionService.readFile(file, skillId);
		LOGGER.info("uploadFile() execution is completed!");
		model.addAttribute("questionsList", questionsList);
		return questionsList;
	}

	// Submitting Questions for Reviewing
	@GetMapping("/submitforreview/{employeeId}")
	public void submitForReview(ModelMap model, @PathVariable String employeeId) {
		LOGGER.info("submitForReview() is called");
		LOGGER.debug("Employee id is {}", employeeId);
		List<Question> questions = (List<Question>) model.get("questionsList");
		questionService.saveQuestionsForReview(questions, employeeId);
		LOGGER.info("submitForReview() execution is completed!");
	}

	// Submitting Questions as Approved
	@GetMapping("/approveandsubmit/{employeeId}")
	public void submitAsApproved(ModelMap model, @PathVariable String employeeId) {
		LOGGER.info("submitAsApproved() is called");
		LOGGER.debug("Employee id is {}", employeeId);
		List<Question> questions = (List<Question>) model.get("questionsList");
		questionService.saveQuestionsAsApproved(questions, employeeId);
		LOGGER.info("submitAsApproved() execution is completed!");
	}
}
