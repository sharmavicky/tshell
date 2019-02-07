package com.cts.tshell.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.tshell.bean.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	@Query(" select count(q.id),s.name from Question q " + " join q.createdUser u " + " join u.skills s "
			+ " join s.topics t join t.questions " + " where u.employeeId = :employeeId and t.id=q.id  group by s.name")
	List<Question> findTotalQuestionContributedById(@Param("employeeId") String employeeId);

	@Query("select count(q.id) from Question q")
	long totalQuestionsCount();

	Question fetchLatestQuestion();

	Question fetchAllQuestionDetails(@Param("questionId") int questionId);

	Question fetchQuestionDetails(@Param("questionId") int questionId);

	/*
	 * Mentioning the query using @Query annotation and passing the Pageable
	 * object to get the limited row from database for the below method
	 * findReviewQuestion()
	 */
	@Query("select q from Question q " + "left join q.questionDifficultyLevel " + "left join q.questionAnswerType "
			+ "left join q.optionList " + "left join q.topicSet t " + "where q.status ='Pending' "
			+ "and t.skill.id = :skillId " + "order by q.createdDate asc ")
	public Page<Question> findReviewQuestion(@Param("skillId") int skillId, Pageable pageable);

	public Question findById(int questionId);

	@Query(value = "SELECT * FROM question  WHERE MATCH (qu_question) AGAINST (? IN NATURAL LANGUAGE MODE) ", nativeQuery = true)
	List<Question> fetchQuestionBasedOnKeyword(String searchedQuestion);

	public Question findQuestionWithOptions(@Param("questionId") int questionId);

	List<Question> fetchQuestionById(@Param("questionId") int questionId);

	Question findQuestionById(int id);

	@Query(value = "select qu_id from question  " + "left join topic_question on tq_qu_id = qu_id  "
			+ "left join topic on tp_id = tq_tp_id " + " " + "where qu_status = 'Approved' "
			+ "and tq_tp_id = :topicId and tp_sk_id = :skillId " + " ", nativeQuery = true)
	long[] getQuestionId(@Param("topicId") int topicId, @Param("skillId") int skillId);
}
