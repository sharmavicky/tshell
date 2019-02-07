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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "assessment_question_option")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class AssessmentQuestionOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ao_id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ao_aq_id")
	@JsonView(Views.Internal.class)
	private AssessmentQuestion assessmentQuestion;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ao_op_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Option assessmentOption;

	@Column(name = "ao_is_selected")
	private boolean selected;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AssessmentQuestion getAssessmentQuestion() {
		return assessmentQuestion;
	}

	public void setAssessmentQuestion(AssessmentQuestion assessmentQuestion) {
		this.assessmentQuestion = assessmentQuestion;
	}

	public Option getAssessmentOption() {
		return assessmentOption;
	}

	public void setAssessmentOption(Option assessmentOption) {
		this.assessmentOption = assessmentOption;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	/*
	 * @Override public String toString() { return
	 * "AssessmentQuestionOption [id=" + id + ", assessmentQuestion=" +
	 * assessmentQuestion + ", assessmentOption=" + assessmentOption +
	 * ", selected=" + selected + "]"; }
	 */
}
