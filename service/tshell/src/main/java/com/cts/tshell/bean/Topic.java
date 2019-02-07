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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "topic")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@NamedQueries({
		@NamedQuery(name = "Topic.fetchTopics", query = "select t.name from Topic t "
				+ "join t.skill s where s.id=:skillId"),
		@NamedQuery(name = "Topic.findTopicByName", query = "select distinct t from Topic t "
				+ "left join fetch t.skill " + "left join fetch t.questions where t.name=:name"),

		@NamedQuery(name = "Topic.findTopics", query = "select t.id,t.name from Topic t join t.skill s where s.id=:skillId"),

		@NamedQuery(name = "Topic.fetchTopicsofSkill", query = "select distinct t from Topic t "

				+ "left join fetch t.skill s where s.id=:skillId") })
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tp_id")
	private int id;

	@Column(name = "tp_name")
	private String name;

	@Column(name = "tp_percentage")
	private int percentage;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "tp_sk_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Skill skill;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "topic_question", joinColumns = { @JoinColumn(name = "tq_tp_id") }, inverseJoinColumns = {
			@JoinColumn(name = "tq_qu_id") })
	@JsonView(Views.Internal.class)
	private List<Question> questions;

	@Transient
	private float topicScore;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Topic(String name) {
		this.name = name;
	}

	public Topic() {
		super();
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public float getTopicScore() {
		return topicScore;
	}

	public void setTopicScore(float topicScore) {
		this.topicScore = topicScore;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", name=" + name + "]";
	}
}
