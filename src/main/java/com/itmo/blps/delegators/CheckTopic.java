package com.itmo.blps.delegators;

import com.itmo.blps.dao.TopicRepository;
import com.itmo.blps.model.Topic;
import com.itmo.blps.service.TopicService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.feel.syntaxtree.In;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Optional;

@Named("checkTopic")
public class CheckTopic implements JavaDelegate {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    TopicService topicService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Integer topicIdTmp = (Integer) execution.getVariable("topicId");
        Long topicId = Long.valueOf(topicIdTmp);

        if (!topicRepository.existsById(topicId)) {
            throw new BpmnError(
                    "topic_not_found",
                    "no topic found with this id"
            );
        }

        Optional<Topic> topic = topicService.findById(topicId);
        execution.setVariable("title", topic.get().getTitle());
        execution.setVariable("description", topic.get().getDescription());
        execution.setVariable("content", topic.get().getContent());
        execution.setVariable("category", topic.get().getCategory().label.toUpperCase());
    }
}
