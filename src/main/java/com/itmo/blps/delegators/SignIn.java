package com.itmo.blps.delegators;

import com.itmo.blps.dao.UserRepository;
import com.itmo.blps.model.user.User;
import com.itmo.blps.model.user.UserRole;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named("signIn")
public class SignIn implements JavaDelegate {
    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = (String) execution.getVariable("email");
        String password = (String) execution.getVariable("password");

        if (!userRepository.existsUserByEmail(email)) {
            throw new BpmnError(
                    "user_doesnt_exist",
                    "Пользователь с такой почтой не найден"
            );
        }

        User user = userRepository.findByEmail(email);
        if (!user.getPassword().equals(password)) {
            throw new BpmnError(
                    "wrong_password",
                    "Введён неверный пароль"
            );
        }

        if (user.getRole().equals(UserRole.USER)) {
            throw new BpmnError(
                    "no_access",
                    "У вас нет доступа к данному функционалу"
            );
        }

    }
}
