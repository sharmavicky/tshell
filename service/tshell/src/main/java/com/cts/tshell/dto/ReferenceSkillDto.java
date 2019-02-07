package com.cts.tshell.dto;

public class ReferenceSkillDto {
	private int id;
	private int skillId;
	private String classifier;
	private int referenceSkillId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getClassifier() {
		return classifier;
	}

	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}

	public int getReferenceSkillId() {
		return referenceSkillId;
	}

	public void setReferenceSkillId(int referenceSkillId) {
		this.referenceSkillId = referenceSkillId;
	}

}
