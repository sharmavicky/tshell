package com.cts.tshell.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tshell.bean.Topic;
import com.cts.tshell.repository.TopicRepository;

@Service
public class TopicService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);
	
	private TopicRepository topicRepository;
	
	
	@Autowired
	public void setTopicRepository(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}
	
	
	@Transactional
	public void saveTopic(Topic topic) {
		LOGGER.info("Starting setSkill() inside topicService");
		LOGGER.debug("recived topic from topicController"+topic);
		topicRepository.save(topic);	
	}
	
	@Transactional
	public void deleteTopic(Topic topic) {
		LOGGER.info("Starting setSkill() inside topicService");
		LOGGER.debug("recived topic from topicController");
		topicRepository.delete(topic);

	}

}
