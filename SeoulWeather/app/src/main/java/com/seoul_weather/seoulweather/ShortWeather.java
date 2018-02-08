package com.seoul_weather.seoulweather;

/**
 * Created by Jeong Minji on 2018-01-11.
 */

public class ShortWeather {
    private String category;    // 위치
    private String hour;  // 시간
    private String day;    // 날짜
    private String temp;  // 온도
    private String wfKor; // 상태
    private String pop; // 강수확률
    private String reh; // 습도
    private String tmx; // 최고기온
    private String tmn; // 최저기온
    private String ws;  // 풍속
    private String pty;    // 강수코드

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPty() {
        return pty;
    }

    public void setPty(String pty) {
        this.pty = pty;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getTmx() {
        return tmx;
    }

    public void setTmx(String tmx) {
        this.tmx = tmx;
    }

    public String getTmn() {
        return tmn;
    }

    public void setTmn(String tmn) {
        this.tmn = tmn;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getReh() {
        return reh;
    }

    public void setReh(String reh) {
        this.reh = reh;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setWfKor(String wfKor) {
        this.wfKor = wfKor;
    }

    public String getHour() {
        return hour;
    }

    public String getTemp() {
        return temp;
    }

    public String getWfKor() {
        return wfKor;
    }
}
