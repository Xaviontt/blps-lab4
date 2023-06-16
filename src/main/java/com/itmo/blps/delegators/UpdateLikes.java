package com.itmo.blps.delegators;

import com.itmo.blps.dao.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named("updateLikes")
public class UpdateLikes implements JavaDelegate {
    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        userRepository.updateRemainingGradesBy100();
        userRepository.saveAll(userRepository.findAll());
    }
}
