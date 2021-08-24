package com.practice.r2dbc.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {

    private final long id;

    private final String content;

    @JsonCreator
    public Greeting(@JsonProperty("content") String content, @JsonProperty("id") long id) {
        this.content = content;
        this.id = id;
    }

    public Greeting() {
        this.id = -1;
        this.content = "";
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
