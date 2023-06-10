package com.itmo.blps.model;

public enum TopicCategory {
    NEWS("News"),
    ARTICLES("Article"),
    TEST_DRIVES("Test-Drives");

    public final String label;
    TopicCategory(String label) {
        this.label = label;
    }
}
