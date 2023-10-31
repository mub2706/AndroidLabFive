package com.cst3104.androidlab5;

public class Message {
    // Private instance variables to store message text and the sent status.
    private String text;      // The text content of the message.
    private boolean isSent;   // A flag to indicate whether the message has been sent.

    public Message(String text, boolean isSent) {
        this.text = text;
        this.isSent = isSent;
    }

    public String getText() {
        return text;
    }

    public boolean isSent() {
        return isSent;
    }
}
