package com.cts.tshell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.tshell.bean.AssessmentQuestionOption;

public interface AssessmentQuestionOptionRepository extends JpaRepository<AssessmentQuestionOption, Integer> {

	@Query(value = "select ao_id from assessment_question_option   "
			+ "where ao_aq_id =:assessmentQuestionId and ao_op_id=:optionId  " + " ", nativeQuery = true)
	int fetchAssesmentQuestionOptionId(@Param("assessmentQuestionId") int assessmentQuestionId,
			@Param("optionId") int optionId);

	@Modifying
	@Query(value = "update assessment_question_option "
			+ "set ao_aq_id =:assessmentQuestionId, ao_op_id=:optionId, ao_is_selected=:selected where ao_id=:assessmentQuestionOptionId "
			+ " ", nativeQuery = true)
	void saveAssesmentQuestionOption(@Param("assessmentQuestionId") int assessmentQuestionId,
			@Param("optionId") int optionId, @Param("selected") boolean selected,
			@Param("assessmentQuestionOptionId") int assessmentQuestionOptionId);
}
