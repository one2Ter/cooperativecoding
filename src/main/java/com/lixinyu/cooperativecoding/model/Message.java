package com.lixinyu.cooperativecoding.model;

public class Message {

    private int channel;
    private String content;
    private String from;
    private String extra;

    public Message() {
    }

    public Message(Message message){
        this.channel = message.getChannel();
        this.content = message.getContent();
        this.from = message.getFrom();
        this.extra = message.getExtra();
    }

    public Message(int channel, String content, String from) {
        this.channel = channel;
        this.content = content;
        this.from = from;
        this.extra = null;
    }

    public Message(int channel, String content, String from, String extra) {
        this.channel = channel;
        this.content = content;
        this.from = from;
        this.extra = extra;
    }

    //Getters and Setters
    public int getChannel() { return channel; }

    public void setChannel(int channel) { this.channel = channel; }

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