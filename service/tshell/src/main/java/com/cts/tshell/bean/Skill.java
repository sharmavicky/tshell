package com.cts.tshell.bean;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "skill")
@NamedQueries({
		@NamedQuery(name = "Skill.findPendingQuestionsCount", query = "select count(*), s.name, s.id from Skill s "
				+ "join s.topics t " + "join t.questions q " + "where q.status='Pending' group by s.name "),

		@NamedQuery(name = "Skill.findSkillNames", query = "select s.name,s.id from Skill s "
				+ "where s.name LIKE CONCAT('%',:searchSkillName,'%') "),

		@NamedQuery(name = "Skill.fetchTopSearchedSkills", query = "select s.name, s.searchCount from Skill s  where s.searchCount>0 order by searchCount desc") })

public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sk_id")
	private int id;

	@Column(name = "sk_name")
	private String name;

	@Column(name = "sk_search_count")
	private int searchCount;

	@Column(name = "sk_active")
	private String active;

	@Column(name = "sk_test_count")
	private int testCount;

	@Column(name = "sk_description")
	private String description;

	@Column(name = "sk_image")
	private byte[] image;

	@Column(name = "sk_creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "skill")
	@JsonView(Views.Internal.class)
	private List<Topic> topics;

	public Skill() {
		super();
	}

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

	public int getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public int getTestCount() {
		return testCount;
	}

	public void setTestCount(int testCount) {
		this.testCount = testCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + "]";
	}

}
