package com.itmo.blps.delegators;

import com.itmo.blps.service.RateService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named("rateTopic")
public class RateTopic implements JavaDelegate {
    private final RateService rateService;
    private final IdentityService identityService;
    @Autowired
    public RateTopic(RateService rateService, IdentityService identityService) {
        this.rateService = rateService;
        this.identityService = identityService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long topicId = Long.parseLong( (String) execution.getVariable("topicId"));
        String userId = identityService.getCurrentAuthentication().getUserId();
        rateService.rateTopic(userId, topicId);
    }
}
