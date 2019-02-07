package com.cts.tshell.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "`option`")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@NamedQueries({
		@NamedQuery(name = "Option.fetchOptionDetailsById", query = "select o from Option o join o.question where o.id=:optionId")

})
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "op_id")
	private int id;

	@Column(name = "op_description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "op_qu_id")
	@JsonView(Views.Internal.class)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Question question;

	@Column(name = "op_is_correct")
	private boolean answer;

	@Transient
	private boolean response;

	@Transient
	private int counter = 0;

	@Transient
	private boolean invalidAnswerFormat;

	@Transient
	private boolean lengthExceeded;

	public boolean isInvalidAnswerFormat() {
		return invalidAnswerFormat;
	}

	public void setInvalidAnswerFormat(boolean invalidAnswerFormat) {
		this.invalidAnswerFormat = invalidAnswerFormat;
	}

	public boolean isLengthExceeded() {
		return lengthExceeded;
	}

	public void setLengthExceeded(boolean lengthExceeded) {
		this.lengthExceeded = lengthExceeded;
	}

	public Option(String description, String answer) {
		super();
		this.description = description;
		if (getDescription().length() > 200) {
			lengthExceeded = true;
		} else {
			lengthExceeded = false;
		}
		if (answer.equalsIgnoreCase("y")) {
			this.answer = true;
			invalidAnswerFormat = false;
		} else if (answer.equalsIgnoreCase("n")) {
			this.answer = false;
			invalidAnswerFormat = false;
		} else {
			invalidAnswerFormat = true;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public Option() {
		super();
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}
