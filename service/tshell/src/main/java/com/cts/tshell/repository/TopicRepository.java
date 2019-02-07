package com.cts.tshell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.tshell.bean.Skill;
import com.cts.tshell.bean.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

	public Topic findTopicByName(@Param("name") String name);

	public List<Topic> findTopics(@Param("skillId") int skillId);

	public Topic getTopicByName(String TopicName);

	public List<Topic> findBySkill(Skill skill);

	public Topic findByName(String topicName);

	public List<Topic> fetchTopics(@Param("skillId") int skillId);

	@Query(value = "select * from topic  " + "where tp_sk_id = :skillId " + " ", nativeQuery = true)
	List<Topic> getTopicIdAndWeightage(@Param("skillId") int skillId);

}
