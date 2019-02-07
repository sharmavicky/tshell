package com.cts.tshell.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.tshell.bean.Assessment;
import com.cts.tshell.bean.TopicWiseScore;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Integer>{
	
	Page<Assessment> findTop5BySkill(@Param("skillId") int skillId, Pageable pageable);
	List<Assessment> findUserHistory(@Param("id")String assessmentId);
	Assessment findById(int assessmentId);
	@Query("select count(a.id) from Assessment a ")
	long totalAssessmentsCount();

	Assessment findAssessmentById(int id);
	
	Assessment fetchAssesmentDetailById(@Param("assessmentId")int assessmentId);
	
	List<TopicWiseScore> getTopicWiseQuestionCount(@Param("assessmentId")int assessmentId);
}
