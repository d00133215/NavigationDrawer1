package com.example.android.navigationdrawerexample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by waihong on 15/02/2015.
 */
public class userSetting implements Serializable {

    public boolean callPolice ;
    public boolean SMSRemote;
    public String HelpMsgContent;
    public boolean help;

    public boolean shackInvoke;
    public int levelOfVibration;
    public ArrayList<Date> autoInvokeTimes;
    public boolean autoInvoke;
    public String password;
    public String policeNumber;
    public ArrayList<String> SMSList;
    public ArrayList<String> emailList;

    public userSetting(){
        help=false;
        shackInvoke = true;
        levelOfVibration = 50;
        autoInvokeTimes = new ArrayList<Date>();
        autoInvoke =false;
        password = "no password";
        policeNumber = "0429388400";
        SMSList = new ArrayList<String>();
        emailList = new ArrayList<String>();

        HelpMsgContent = "Help please!";
        callPolice = false;
        SMSRemote=false;
    }

    public userSetting(boolean help, boolean shackInvoke, int levelOfVibration, ArrayList<Date> autoInvokeTimes, boolean autoInvoke, String password, String policeNumber, ArrayList<String> SMSList, ArrayList<String> emailList, boolean policeOnOff, boolean SMSRemote, String helpMsg) {
        this.help = help;
        this.shackInvoke = shackInvoke;
        this.levelOfVibration = levelOfVibration;
        this.autoInvokeTimes = autoInvokeTimes;
        this.autoInvoke = autoInvoke;
        this.password = password;
        this.policeNumber = policeNumber;
        this.SMSList = SMSList;
        this.emailList = emailList;

        this.callPolice = policeOnOff;
        this.HelpMsgContent=helpMsg;
        this.SMSRemote = SMSRemote;
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    public String getHelpMsgContent() {
        return HelpMsgContent;
    }

    public void setHelpMsgContent(String helpMsgContent) {
        HelpMsgContent = helpMsgContent;
    }

    public boolean isCallPolice() {
        return callPolice;
    }

    public void setCallPolice(boolean callPolice) {
        this.callPolice = callPolice;
    }

    public boolean isSMSRemote() {
        return SMSRemote;
    }

    public void setSMSRemote(boolean SMSRemote) {
        this.SMSRemote = SMSRemote;
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

    public String getPassword() {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    public void setSMSList(ArrayList<String> SMSList) {
        this.SMSList = SMSList;
    }

    public void addSMSList(String number){
        SMSList.add(number);

    }

    public void setEmailList(ArrayList<String> emailList) {
        this.emailList = emailList;
    }

    @Override
    public String toString() {
        return "userSetting{" +
                "callPolice=" + callPolice +
                ", SMSRemote=" + SMSRemote +
                ", HelpMsgContent='" + HelpMsgContent + '\'' +
                ", help=" + help +
                ", shackInvoke=" + shackInvoke +
                ", levelOfVibration=" + levelOfVibration +
                ", autoInvokeTimes=" + autoInvokeTimes +
                ", autoInvoke=" + autoInvoke +
                ", password='" + password + '\'' +
                ", policeNumber='" + policeNumber + '\'' +
                ", SMSList=" + SMSList +
                ", emailList=" + emailList +
                '}';
    }
}
