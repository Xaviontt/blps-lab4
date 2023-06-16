package com.itmo.blps.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itmo.blps.model.Topic;
import com.itmo.blps.dao.TopicRepository;
import com.itmo.blps.model.TopicCategory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public Optional<Long> delete(long id) {
        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
            return Optional.of(id);
        }
        return Optional.empty();
    }

    public Topic update(long id, String title, String description, String content, TopicCategory topicCategory) {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if (topicOptional.isEmpty()) return null;

        Topic topic = topicOptional.get();
        topic.setTitle(title);
        topic.setDescription(description);
        topic.setContent(content);
        topic.setCategory(topicCategory);
        topic.setUpdatedAt(new Date());
        return topicRepository.save(topic);
    }

    public Optional<Topic> findById(long id) {
        return topicRepository.findById(id);
    }


//    public void generateSummary(Long dateInEpochMillis) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            List<Topic> topicList = topicRepository.findAllSince(new Date(dateInEpochMillis));
//            String filename = String.format("summary-%d.json", (new Date()).getTime());
//
//            mapper.writeValue(new File("summaries/" + filename), topicList);
//        } catch (IOException e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
}
