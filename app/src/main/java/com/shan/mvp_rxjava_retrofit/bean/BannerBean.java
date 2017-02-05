package com.shan.mvp_rxjava_retrofit.bean;

/**
 * Created by roock on 03/09.
 */
public class BannerBean {
    private String title;
    private String image;

    public BannerBean() {

    }

    public BannerBean(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
