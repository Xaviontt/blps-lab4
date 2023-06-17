package com.itmo.blps.delegators;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class DeleteTopic implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        long topicId = (long) execution.getVariable("topicId");

        if (topicId)

    }
}
