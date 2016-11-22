package com.shan.mypubliclibrary.bean;

/**
 * Created by 陈俊山 on 2016/8/28.
 */

public class BaseBean {
    private int showapi_res_code;
    private String showapi_res_error;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                '}';
    }
}
