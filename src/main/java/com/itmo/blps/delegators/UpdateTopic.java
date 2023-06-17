package com.itmo.blps.delegators;

import com.itmo.blps.model.TopicCategory;
import com.itmo.blps.service.TopicService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named("updateTopic")
public class UpdateTopic implements JavaDelegate {
    @Autowired
    TopicService topicService;
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Integer topicIdTmp = (Integer) execution.getVariable("topicId");
        Long topicId = Long.valueOf(topicIdTmp);

        String title = (String) execution.getVariable("title");
        String description = (String) execution.getVariable("description");
        String content = (String) execution.getVariable("content");
        String category = (String) execution.getVariable("category");

        topicService.update(
            topicId,
            title,
            description,
            content,
            TopicCategory.valueOf(category)
        );
    }
}
