package com.example.android.navigationdrawerexample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by waihong on 15/02/2015.
 */
public class userSetting implements Serializable {


    public boolean shackInvoke;
    public int levelOfVibration;
    public ArrayList<Date> autoInvokeTimes;
    public boolean autoInvoke;
    public int password;
    public String policeNumber;
    public ArrayList<String> SMSList;
    public ArrayList<String> emailList;

    public userSetting(){
        shackInvoke = true;
        levelOfVibration = 50;
        autoInvokeTimes = new ArrayList<Date>();
        autoInvoke =false;
        password = 1234;
        policeNumber = "0429388400";
        SMSList = new ArrayList<String>();
        emailList = new ArrayList<String>();
    }

    public userSetting(boolean shackInvoke, int levelOfVibration, ArrayList<Date> autoInvokeTimes, boolean autoInvoke, int password, String policeNumber, ArrayList<String> SMSList, ArrayList<String> emailList) {
        this.shackInvoke = shackInvoke;
        this.levelOfVibration = levelOfVibration;
        this.autoInvokeTimes = autoInvokeTimes;
        this.autoInvoke = autoInvoke;
        this.password = password;
        this.policeNumber = policeNumber;
        this.SMSList = SMSList;
        this.emailList = emailList;
    }

    public boolean isShackInvoke() {
        return shackInvoke;
    }

    public int getLevelOfVibration() {
        return levelOfVibration;
    }

    public ArrayList<Date> getAutoInvokeTimes() {
        return autoInvokeTimes;
    }

    public boolean isAutoInvoke() {
        return autoInvoke;
    }

    public int getPassword() {
        return password;
    }

    public String getPoliceNumber() {
        return policeNumber;
    }

    public ArrayList<String> getSMSList() {
        return SMSList;
    }

    public ArrayList<String> getEmailList() {
        return emailList;
    }

    public void setShackInvoke(boolean shackInvoke) {
        this.shackInvoke = shackInvoke;
    }

    public void setLevelOfVibration(int levelOfVibration) {
        this.levelOfVibration = levelOfVibration;
    }

    public void setAutoInvokeTimes(ArrayList<Date> autoInvokeTimes) {
        this.autoInvokeTimes = autoInvokeTimes;
    }

    public void setAutoInvoke(boolean autoInvoke) {
        this.autoInvoke = autoInvoke;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    public void setSMSList(ArrayList<String> SMSList) {
        this.SMSList = SMSList;
    }

    public void setEmailList(ArrayList<String> emailList) {
        this.emailList = emailList;
    }

    @Override
    public String toString() {
        return "userSetting{" +
                "shackInvoke=" + shackInvoke +
                ", levelOfVibration=" + levelOfVibration +
                ", autoInvokeTimes=" + autoInvokeTimes +
                ", autoInvoke=" + autoInvoke +
                ", password=" + password +
                ", policeNumber='" + policeNumber + '\'' +
                ", SMSList=" + SMSList +
                ", emailList=" + emailList +
                '}';
    }
}
