package com.cts.tshell.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="assessment_question")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class AssessmentQuestion {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="aq_id")
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="aq_as_id")
	@JsonView(Views.Internal.class)
	private Assessment assessment;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.MERGE)
	@JoinColumn(name="aq_qu_id")
	@JsonView(Views.Internal.class)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Question question;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="assessmentQuestion",cascade=CascadeType.MERGE)
	private List<AssessmentQuestionOption> assessmentQuestionOption;
	
	@Column(name="aq_is_correct")
	private boolean correct;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<AssessmentQuestionOption> getAssessmentQuestionOption() {
		return assessmentQuestionOption;
	}

	public void setAssessmentQuestionOption(List<AssessmentQuestionOption> assessmentQuestionOption) {
		this.assessmentQuestionOption = assessmentQuestionOption;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
}

