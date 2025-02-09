package org.casbin.casibase.entity;

import java.util.List;

public class Task {

    public String activity;

    public String application;

    public String createdTime;

    public String displayName;

    public String example;

    public String grade;

    public List<String> labels;

    public String log;

    public String name;

    public String owner;

    public String path;

    public String provider;

    public String result;

    public String subject;

    public String text;

    public String topic;

    public String type;

    public Task() {
    }

    public Task(String activity, String application, String createdTime, String displayName, String example,
                String grade, List<String> labels, String log, String name, String owner, String path, String provider,
                String result, String subject, String text, String topic, String type) {
        this.activity = activity;
        this.application = application;
        this.createdTime = createdTime;
        this.displayName = displayName;
        this.example = example;
        this.grade = grade;
        this.labels = labels;
        this.log = log;
        this.name = name;
        this.owner = owner;
        this.path = path;
        this.provider = provider;
        this.result = result;
        this.subject = subject;
        this.text = text;
        this.topic = topic;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Task{" +
                "activity='" + activity + '\'' +
                ", application='" + application + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", displayName='" + displayName + '\'' +
                ", example='" + example + '\'' +
                ", grade='" + grade + '\'' +
                ", labels=" + labels +
                ", log='" + log + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", path='" + path + '\'' +
                ", provider='" + provider + '\'' +
                ", result='" + result + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", topic='" + topic + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
