package com.itmo.blps.delegators;

import com.itmo.blps.dao.TopicRepository;
import com.itmo.blps.dao.UserRepository;
import com.itmo.blps.model.topic.Topic;
import com.itmo.blps.model.topic.TopicCategory;
import com.itmo.blps.model.user.User;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import spinjar.com.sun.xml.bind.v2.TODO;

import javax.inject.Named;
import java.util.Date;

@Named("addTopic")
public class AddTopic implements JavaDelegate {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String title = (String) execution.getVariable("title");
        String description = (String) execution.getVariable("description");
        String content = (String) execution.getVariable("content");
        String category = (String) execution.getVariable("category");

        // TODO: 16.06.2023 получить пользователя
        String email = (String) execution.getVariable("email");

        User user = userRepository.findByEmail(email);

        Date currantDate = new Date();
        TopicCategory topicCategory = TopicCategory.valueOf(category);

        Topic topic = new Topic(
                title,
                description,
                content,
                topicCategory,
                currantDate,
                currantDate,
                0,
                user
        );

        topicRepository.save(topic);

        execution.setVariable("topicTitle", topic.getTitle());
        execution.setVariable("topicDescription", topic.getDescription());
        execution.setVariable("topicContent", topic.getContent());
        execution.setVariable("topicCategory", topic.getCategory().label);

    }
}
