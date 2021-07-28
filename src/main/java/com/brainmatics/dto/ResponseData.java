package com.brainmatics.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData<T> {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private T playload; //generic di Java
    
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public List<String> getMessages() {
        return messages;
    }
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    public T getPlayload() {
        return playload;
    }
    public void setPlayload(T playload) {
        this.playload = playload;
    }

    
}
