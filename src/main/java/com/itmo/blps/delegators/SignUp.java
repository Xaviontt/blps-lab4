package com.itmo.blps.delegators;

import com.itmo.blps.dao.UserRepository;
import com.itmo.blps.model.user.User;
import com.itmo.blps.model.user.UserRole;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Date;

@Named("signUp")
public class SignUp implements JavaDelegate {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = (String) execution.getVariable("email");
        String password = (String) execution.getVariable("password");

        User user = new User(
                email,
                password,
                UserRole.USER,
                100,
                new Date()
        );

        userRepository.save(user);

        execution.setVariable(
                "result",
                "Пользователь " + user.getEmail() + " успешно добавлен"
        );
    }
}
