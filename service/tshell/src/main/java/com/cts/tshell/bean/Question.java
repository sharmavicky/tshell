package com.cts.tshell.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "question")
@NamedQueries({ @NamedQuery(name = "Question.fetchLatestQuestion", query = "select q from Question q where"
		+ " q.id=(select Max(q1.id) from Question q1)"),

		@NamedQuery(name = "Question.fetchAllQuestionDetails", query = "select distinct q from Question q "
				+ "left join fetch q.questionDifficultyLevel " + "left join fetch q.questionAnswerType "
				+ " left join fetch q.createdUser " + "where q.id=:questionId"),

		@NamedQuery(name = "Question.fetchQuestionDetails", query = "select distinct q from Question q "
				+ "left join fetch q.questionDifficultyLevel " + "left join fetch q.questionAnswerType "
				+ " left join fetch q.createdUser " + "left join fetch q.optionList " + "where q.id=:questionId"),

		@NamedQuery(name = "Question.findQuestionWithOptions", query = "select q from Question q join q.optionList o where q.id=:questionId"),
		@NamedQuery(name = "Question.fetchQuestionById", 
				query = "select distinct q from Question q " +
						"left join fetch q.questionDifficultyLevel t " + 
						"left join fetch q.questionAnswerType t " + 
						"left join fetch q.optionList t " + 
						"left join fetch q.topicSet t " + 
						"where q.status ='Approved' " + 
						"and q.id = :questionId "+ 
						" ")

})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Question {

	public Question(int id, String question, String status, QuestionDifficultyLevel questionDifficultyLevel,
			User createdUser, List<Option> optionList) {
		super();
		this.id = id;
		this.question = question;
		this.status = status;
		this.questionDifficultyLevel = questionDifficultyLevel;
		this.createdUser = createdUser;
		this.optionList = optionList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qu_id")
	private int id;

	@Column(name = "qu_question")
	private String question;

	@Column(name = "qu_status")
	private String status;

	@Column(name = "qu_created_date")
	private Date createdDate;

	@Column(name = "qu_reviewed_date")
	private Date reviewedDate;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "qu_qd_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private QuestionDifficultyLevel questionDifficultyLevel;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "qu_qt_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private QuestionAnswerType questionAnswerType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qu_created_by_us_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User createdUser;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "qu_reviewed_by_us_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User reviewedUser;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "topic_question", joinColumns = { @JoinColumn(name = "tq_qu_id") }, inverseJoinColumns = {
			@JoinColumn(name = "tq_tp_id") })
	private Set<Topic> topicSet;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Option> optionList;

	@Transient
	private String topic;

	@Transient
	private boolean empty;

	@Transient
	private boolean lengthExceeded;

	@Transient
	private String error;

	@Transient
	private String Topic;

	@Transient
	private boolean validTopic;
	@JsonIgnore
	@Transient
	private int answerType;

	public String getTopic() {
		return Topic;
	}

	public void setTopic(String topic) {
		Topic = topic;
	}

	public boolean isValidTopic() {
		return validTopic;
	}

	public void setValidTopic(boolean validTopic) {
		this.validTopic = validTopic;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public boolean isLengthExceeded() {
		return lengthExceeded;
	}

	public void setLengthExceeded(boolean lengthExceeded) {
		this.lengthExceeded = lengthExceeded;
	}

	public Question() {
		super();
	}


	public Question(String[] csvContent) {
		Topic topic = new Topic(csvContent[0]);
		List<Option> options = new ArrayList<Option>();

		this.question = csvContent[1].trim();
		if(csvContent[1].isEmpty()){
			setError("Question Description is missing");
		}
		if (!csvContent[2].equals("") || !csvContent[3].equals("")) {
			options.add(new Option(csvContent[2].trim(), csvContent[3].trim()));
		}
		if (!csvContent[4].equals("") || !csvContent[5].equals("")) {
			options.add(new Option(csvContent[4].trim(), csvContent[5].trim()));
		}
		if (!csvContent[6].equals("") || !csvContent[7].equals("")) {

			options.add(new Option(csvContent[6].trim(), csvContent[7].trim()));
		}
		if (!csvContent[8].equals("") || !csvContent[9].equals("")) {
			options.add(new Option(csvContent[8].trim(), csvContent[9].trim()));
		}
		if (!csvContent[10].equals("") || !csvContent[11].equals("")) {
			options.add(new Option(csvContent[10].trim(), csvContent[11].trim()));
		}

		this.optionList = options;
		int correctAnswerCount = 0;
		int invalidinput = 0;
		int count = 0;
		int invalidAnswerCount = 0;

		Set<String> optionsSet = new HashSet<String>();
		for (Option option : optionList) {
			optionsSet.add(option.getDescription().toLowerCase());
			if (option.isAnswer()) {
				correctAnswerCount += 1;
			}
			if ((option.getDescription().equals("") && !option.isInvalidAnswerFormat())) {
				invalidinput += 1;
			}
			if (!option.getDescription().equals("") && ((option.getDescription().toLowerCase().equals("true")
					|| option.getDescription().toLowerCase().equals("false"))
					|| (option.getDescription().toLowerCase().equals("yes")
							|| option.getDescription().toLowerCase().equals("no"))
					|| (option.getDescription().toLowerCase().equals("active")
							|| option.getDescription().toLowerCase().equals("inactive")))) {
				if (option.isAnswer()) {
					count += 1;
				}
			}
			if (!option.getDescription().equals("") && option.isInvalidAnswerFormat()) {
				invalidAnswerCount += 1;
			}
			if (option.isLengthExceeded()) {
				setError("Option length exceeded");
			}

		}

		if (correctAnswerCount < 1) {
			setError("Atleast one option should be selected as Answer");
		}

		else if (count == 2) {
			setError("All option can not be Answer");
		} else if (invalidinput > 0) {
			setError("Option is missing");
		} else if (optionsSet.size() != getOptionList().size()) {
			setError("Options cannot have same values");
		} else if (invalidAnswerCount > 0) {
			setError("Answer is missing or incorrect format");
		}

		if (csvContent[2].isEmpty()) {
			empty = true;
		} else {
			empty = false;
		}

		if (getQuestion().length() > 500) {
			lengthExceeded = true;
			setError("Question length exceeded");
		} else {
			lengthExceeded = false;
		}
		if (correctAnswerCount > 1) {
			setAnswerType(2);

		} else {
			setAnswerType(1);
		}
	}

	public Set<Topic> getTopicSet() {
		return topicSet;
	}

	public void setTopicSet(Set<Topic> topicSet) {
		this.topicSet = topicSet;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public QuestionDifficultyLevel getQuestionDifficultyLevel() {
		return questionDifficultyLevel;
	}

	public void setQuestionDifficultyLevel(QuestionDifficultyLevel questionDifficultyLevel) {
		this.questionDifficultyLevel = questionDifficultyLevel;
	}

	public User getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(User createdUser) {
		this.createdUser = createdUser;
	}

	public QuestionAnswerType getQuestionAnswerType() {
		return questionAnswerType;
	}

	public void setQuestionAnswerType(QuestionAnswerType questionAnswerType) {
		this.questionAnswerType = questionAnswerType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getReviewedDate() {
		return reviewedDate;
	}

	public void setReviewedDate(Date reviewedDate) {
		this.reviewedDate = reviewedDate;
	}

	public User getReviewedUser() {
		return reviewedUser;
	}

	public void setReviewedUser(User reviewedUser) {
		this.reviewedUser = reviewedUser;
	}

	public List<Option> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}

	public Set<Topic> getTopicList() {
		return topicSet;
	}

	public void setTopicList(Set<Topic> topicSet) {
		this.topicSet = topicSet;
	}

	public int getAnswerType() {
		return answerType;
	}

	public void setAnswerType(int answerType) {
		this.answerType = answerType;
	}



}
