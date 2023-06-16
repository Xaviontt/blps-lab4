package com.itmo.blps.delegators;

import com.itmo.blps.service.RateService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named("updateLikes")
public class UpdateLikes implements JavaDelegate {
    private final RateService rateService;
    @Autowired
    public UpdateLikes(RateService rateService) {
        this.rateService = rateService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        rateService.updateLikes();
    }
}
