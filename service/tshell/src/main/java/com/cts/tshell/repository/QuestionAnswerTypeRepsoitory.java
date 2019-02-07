package com.cts.tshell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.tshell.bean.QuestionAnswerType;

public interface QuestionAnswerTypeRepsoitory extends JpaRepository<QuestionAnswerType, Integer> {
	public QuestionAnswerType findById(int id);
}
