package com.lixinyu.cooperativecoding.model;

public class Message {

    private int id;
    private String content;
    private String from;
    private String extra;

    public Message() {
    }

    public Message(Message message){
        this.id = message.getId();
        this.content = message.getContent();
        this.from = message.getFrom();
        this.extra = message.getExtra();
    }

    public Message(int id, String content, String from) {
        this.id = id;
        this.content = content;
        this.from = from;
        this.extra = null;
    }

    public Message(int id, String content, String from, String extra) {
        this.id = id;
        this.content = content;
        this.from = from;
        this.extra = extra;
    }

    //Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}