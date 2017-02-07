package com.shan.sharelibrary;

/**
 * Created by chenjunshan on 17-2-7.
 */

public class ShareBean {
    private String text;
    private String title;

    public ShareBean(String text, String title) {
        this.text = text;
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
