package com.shan.mvp_rxjava_retrofit.bean;

import com.shan.mypubliclibrary.bean.BaseBean;

import java.util.List;

/**
 * Created by 陈俊山 on 2016/8/30.
 */

public class MovieBean extends BaseBean {

    /**
     * ret_code : 0
     * datalist : [{}]
     * remark : 成功
     */

    private ShowapiResBodyBean showapi_res_body;

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        private String ret_code;
        private String remark;
        /**
         * AvgPeople : 87
         * price : 43
         * RowNum : 1
         * TodayShowCount : 105
         * Attendance : 39.62%
         * MovieName : Jackie Chan北京耀莱
         * TodayBox : 397861.00
         */

        private List<DatalistBean> datalist;

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<DatalistBean> getDatalist() {
            return datalist;
        }

        public void setDatalist(List<DatalistBean> datalist) {
            this.datalist = datalist;
        }

        public static class DatalistBean {
            private String AvgPeople;
            private String price;
            private String RowNum;
            private String TodayShowCount;
            private String Attendance;
            private String MovieName;
            private String TodayBox;

            public DatalistBean(String movieName) {
                MovieName = movieName;
            }

            public String getAvgPeople() {
                return AvgPeople;
            }

            public void setAvgPeople(String AvgPeople) {
                this.AvgPeople = AvgPeople;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getRowNum() {
                return RowNum;
            }

            public void setRowNum(String RowNum) {
                this.RowNum = RowNum;
            }

            public String getTodayShowCount() {
                return TodayShowCount;
            }

            public void setTodayShowCount(String TodayShowCount) {
                this.TodayShowCount = TodayShowCount;
            }

            public String getAttendance() {
                return Attendance;
            }

            public void setAttendance(String Attendance) {
                this.Attendance = Attendance;
            }

            public String getMovieName() {
                return MovieName;
            }

            public void setMovieName(String MovieName) {
                this.MovieName = MovieName;
            }

            public String getTodayBox() {
                return TodayBox;
            }

            public void setTodayBox(String TodayBox) {
                this.TodayBox = TodayBox;
            }

            @Override
            public String toString() {
                return "DatalistBean{" +
                        "AvgPeople='" + AvgPeople + '\'' +
                        ", price='" + price + '\'' +
                        ", RowNum='" + RowNum + '\'' +
                        ", TodayShowCount='" + TodayShowCount + '\'' +
                        ", Attendance='" + Attendance + '\'' +
                        ", MovieName='" + MovieName + '\'' +
                        ", TodayBox='" + TodayBox + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ShowapiResBodyBean{" +
                    "ret_code='" + ret_code + '\'' +
                    ", remark='" + remark + '\'' +
                    ", datalist=" + datalist +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MovieBean{" +
                "showapi_res_body=" + showapi_res_body +
                '}';
    }
}
