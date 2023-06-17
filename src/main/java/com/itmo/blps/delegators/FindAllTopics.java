package com.itmo.blps.delegators;

import com.itmo.blps.dao.TopicRepository;
import com.itmo.blps.model.Topic;
import com.itmo.blps.service.TopicService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

@Named("findAllTopics")
public class FindAllTopics implements JavaDelegate {
    @Autowired
    TopicRepository topicRepository;
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Topic> topics = topicRepository.findAll();

        String allTopics = "";
        for (Topic topic: topics){
            allTopics += topic.getTitle() + "\n";
            allTopics += topic.getDescription() + "\n";
            allTopics += topic.getContent() + "\n";
            allTopics += topic.getCategory().label + "\n\n";
        }

        execution.setVariable("topics", allTopics);
    }
}
