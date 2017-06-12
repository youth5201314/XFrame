package com.youth.xf.data;

import java.util.List;

/**
 * 实体类和json对应
 */
public class Weather {

    /**
     * data : {"yesterday":{"date":"11日星期日","high":"高温 31℃","fx":"南风","low":"低温 18℃","fl":"微风","type":"阴"},"city":"北京","aqi":"71","forecast":[{"date":"12日星期一","high":"高温 27℃","fengli":"微风级","low":"低温 18℃","fengxiang":"南风","type":"阵雨"},{"date":"13日星期二","high":"高温 29℃","fengli":"微风级","low":"低温 17℃","fengxiang":"南风","type":"多云"},{"date":"14日星期三","high":"高温 34℃","fengli":"微风级","low":"低温 23℃","fengxiang":"南风","type":"晴"},{"date":"15日星期四","high":"高温 36℃","fengli":"微风级","low":"低温 24℃","fengxiang":"南风","type":"晴"},{"date":"16日星期五","high":"高温 34℃","fengli":"3-4级","low":"低温 23℃","fengxiang":"南风","type":"阴"}],"ganmao":"相对今天出现了较大幅度降温，较易发生感冒，体质较弱的朋友请注意适当防护。","wendu":"26"}
     * status : 1000
     * desc : OK
     */

    private DataBean data;
    private int status;
    private String desc;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class DataBean {
        /**
         * yesterday : {"date":"11日星期日","high":"高温 31℃","fx":"南风","low":"低温 18℃","fl":"微风","type":"阴"}
         * city : 北京
         * aqi : 71
         * forecast : [{"date":"12日星期一","high":"高温 27℃","fengli":"微风级","low":"低温 18℃","fengxiang":"南风","type":"阵雨"},{"date":"13日星期二","high":"高温 29℃","fengli":"微风级","low":"低温 17℃","fengxiang":"南风","type":"多云"},{"date":"14日星期三","high":"高温 34℃","fengli":"微风级","low":"低温 23℃","fengxiang":"南风","type":"晴"},{"date":"15日星期四","high":"高温 36℃","fengli":"微风级","low":"低温 24℃","fengxiang":"南风","type":"晴"},{"date":"16日星期五","high":"高温 34℃","fengli":"3-4级","low":"低温 23℃","fengxiang":"南风","type":"阴"}]
         * ganmao : 相对今天出现了较大幅度降温，较易发生感冒，体质较弱的朋友请注意适当防护。
         * wendu : 26
         */

        private YesterdayBean yesterday;
        private String city;
        private String aqi;
        private String ganmao;
        private String wendu;
        private List<ForecastBean> forecast;

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 11日星期日
             * high : 高温 31℃
             * fx : 南风
             * low : 低温 18℃
             * fl : 微风
             * type : 阴
             */

            private String date;
            private String high;
            private String fx;
            private String low;
            private String fl;
            private String type;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class ForecastBean {
            /**
             * date : 12日星期一
             * high : 高温 27℃
             * fengli : 微风级
             * low : 低温 18℃
             * fengxiang : 南风
             * type : 阵雨
             */

            private String date;
            private String high;
            private String fengli;
            private String low;
            private String fengxiang;
            private String type;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
