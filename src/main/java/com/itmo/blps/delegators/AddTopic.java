package com.itmo.blps.delegators;

import com.itmo.blps.dao.TopicRepository;
import com.itmo.blps.model.Topic;
import com.itmo.blps.model.TopicCategory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Date;

@Named("addTopic")
public class AddTopic implements JavaDelegate {
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String title = (String) execution.getVariable("title");
        String description = (String) execution.getVariable("description");
        String content = (String) execution.getVariable("content");
        String category = (String) execution.getVariable("category");

        Date currantDate = new Date();
        TopicCategory topicCategory = TopicCategory.valueOf(category);

        Topic topic = new Topic(
                title,
                description,
                content,
                topicCategory,
                currantDate,
                currantDate
        );

        topicRepository.save(topic);

        execution.setVariable(
                "result",
                "Статья %s успешно создана!".formatted(title)
        );

    }
}
