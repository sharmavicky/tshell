package com.cts.tshell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.tshell.bean.AssessmentQuestion;

public interface AssessmentQuestionRepository extends JpaRepository<AssessmentQuestion, Integer> {

	@Query(value = "select aq_id from assessment_question  "+
			  "where aq_as_id =:assessmentId and aq_qu_id=:questionId  "+
			  " ", nativeQuery = true)
	int[] fetchAssesmentQuestionId(@Param("assessmentId")int assessmentId, @Param("questionId")int questionId );
	
	AssessmentQuestion findAssessmentQuestionById(int id);
	
}
