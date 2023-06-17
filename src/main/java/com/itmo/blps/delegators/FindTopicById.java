package com.itmo.blps.delegators;

import com.itmo.blps.dao.TopicRepository;
import com.itmo.blps.model.Topic;
import com.itmo.blps.service.TopicService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Optional;

@Named("findTopicById")
public class FindTopicById implements JavaDelegate {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    TopicService topicService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Integer topicIdTmp = (Integer) execution.getVariable("topicId");
        Long topicId = Long.valueOf(topicIdTmp);

        if (!topicRepository.existsById(topicId)){
            execution.setVariable("topicTitle", "Статьи с таким Id не существует");
        } else {
            Optional<Topic> topic = topicService.findById(topicId);
            execution.setVariable("topicTitle", topic.get().getTitle());
            execution.setVariable("topicDescription", topic.get().getDescription());
            execution.setVariable("topicContent", topic.get().getContent());
            execution.setVariable("topicCategory", topic.get().getCategory().label.toUpperCase());
        }
    }
}
