package com.itmo.blps.service;

import com.itmo.blps.dao.TopicRepository;
import com.itmo.blps.dao.UserRateRepository;
import com.itmo.blps.exceptions.NoAvailableGradesException;
import com.itmo.blps.model.Topic;
import com.itmo.blps.model.UserRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

@Service
public class RateService {
    private final UserRateRepository userRateRepository;
    private final TopicRepository topicRepository;
    private final TransactionTemplate transactionTemplate;

    @Autowired
    public RateService(UserRateRepository userRateRepository, TopicRepository topicRepository, TransactionTemplate transactionTemplate) {
        this.userRateRepository = userRateRepository;
        this.topicRepository = topicRepository;
        this.transactionTemplate = transactionTemplate;
    }

    public Topic rateTopic(String userId, long topicId) {
        return transactionTemplate.execute(status -> {
            Optional<Topic> topicOptional = topicRepository.findById(topicId);
            if (topicOptional.isEmpty()) return null;

            Optional<UserRate> userOptional = userRateRepository.findById(userId);
            UserRate userRate = userOptional.orElseGet(() -> new UserRate(userId, 100L));

            Topic topic = topicOptional.get();

            if (userRate.getRemainingGrades() > 0) {
                userRate.setRemainingGrades(userRate.getRemainingGrades() - 1);
                userRateRepository.save(userRate);
                topic.setRate(topic.getRate() + 1);
                return topicRepository.save(topic);
            } else throw new NoAvailableGradesException("Grades ran out");
        });
    }

    public void updateLikes() {
        userRateRepository.updateRemainingGradesBy100();
    }
}
