package com.shan.mvp_rxjava_retrofit.bean;

/**
 * Created by root on 17-2-5.
 */

public class MainTypeBean {
    private String image;
    private String name;

    public MainTypeBean(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
