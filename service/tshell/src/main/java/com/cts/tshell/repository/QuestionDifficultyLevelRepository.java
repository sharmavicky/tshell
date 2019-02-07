package com.cts.tshell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.tshell.bean.QuestionDifficultyLevel;

public interface QuestionDifficultyLevelRepository extends JpaRepository<QuestionDifficultyLevel, Integer> {
	public QuestionDifficultyLevel findById(int id);

}
