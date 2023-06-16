package com.itmo.blps.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userId;
    private String title;
    private String description;
    private String content;
    private TopicCategory category;
    private Date createdAt;
    private Date updatedAt;
    private Integer rate;

    public Topic(String title, String description, String content, TopicCategory category,
                 Date createdAt, Date updatedAt, Integer rate, String userId) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.rate = rate;
        this.userId = userId;
    }
}
