package com.itmo.blps.delegators;

import com.itmo.blps.dao.TopicRepository;
import com.itmo.blps.model.Topic;
import com.itmo.blps.model.TopicCategory;
import com.itmo.blps.service.TopicService;
import net.bytebuddy.implementation.bytecode.ShiftRight;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;


import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named("addTopic")
public class AddTopic implements JavaDelegate {
    @Autowired
    private TopicService topicService;
    @Autowired
    private IdentityService identityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String title = (String) execution.getVariable("title");
        String description = (String) execution.getVariable("description");
        String content = (String) execution.getVariable("content");
        String category = (String) execution.getVariable("category");

        String userId = identityService.getCurrentAuthentication().getUserId();
        List<String> roles = identityService.getCurrentAuthentication().getGroupIds();
        if (!roles.contains("camunda-admin")) {
            throw new BpmnError("authority_error", "Not enough authority");
        }

        Date currentDate = new Date();
        TopicCategory topicCategory = TopicCategory.valueOf(category);

        Topic topic = topicService.save(new Topic(
                title,
                description,
                content,
                topicCategory,
                currentDate,
                currentDate,
                0,
                userId
        ));
    }
}
