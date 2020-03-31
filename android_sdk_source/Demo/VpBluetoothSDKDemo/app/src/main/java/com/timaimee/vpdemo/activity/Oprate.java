package com.timaimee.vpdemo.activity;

/**
 * Created by Administrator on 2017/11/25.
 */

public interface Oprate {
    public final static String PWD_COMFIRM = "1, device password-authentication";
    public final static String PERSONINFO_SYNC = "2. Personal Information-Settings";
    public final static String SETTING_FIRST = "<-first operation 1, 2";
    public final static String PWD_MODIFY = "device password-modify";
    public final static String HEART_DETECT_START = "Measure heart rate-start";

    public final static String HEART_DETECT_STOP = "Measure heart rate-end";
    public final static String BP_DETECT_START = "Measure blood pressure-start";
public final static String BP_DETECT_STOP = "Measuring blood pressure-end";
public final static String BP_DETECTMODEL_SETTING = "Blood pressure mode-setting";
public final static String BP_DETECTMODEL_SETTING_ADJUSTE = "Blood pressure mode [Dynamic adjustment] -setting";
public final static String BP_DETECTMODEL_SETTING_ADJUSTE_CANCEL = "Blood pressure mode [dynamic adjustment] -cancel";
public final static String BP_DETECTMODEL_READ = "Blood pressure mode-read";
public final static String SPORT_CURRENT_READ = "Current step count-read";
public final static String CAMERA_START = "Photo Mode-Start";
public final static String CAMERA_STOP = "Photo mode-stop";
public final static String ALARM_SETTING = "Alarm-setting";
public final static String ALARM_READ = "Alarm-Read";
public final static String ALARM_NEW_READ = "New alarm-read";
public final static String ALARM_NEW_ADD = "New Alarm-Add";
public final static String ALARM_NEW_MODIFY = "New Alarm-Modified";
public final static String ALARM_NEW_DELETE = "New Alarm-Delete";
public final static String LONGSEAT_SETTING_OPEN = "Sit-Open";
public final static String LONGSEAT_SETTING_CLOSE = "Sit-Close";
public final static String LONGSEAT_READ = "Sit-Read";
public final static String LANGUAGE_CHINESE = "Language Settings-Chinese";
public final static String LANGUAGE_ENGLISH = "Language Settings-English";
public final static String BATTERY = "Battery Status-Read";
public final static String NIGHT_TURN_WRIST_OPEN = "Turn wrist at night-open";
public final static String NIGHT_TURN_WRIST_CLOSE = "Night turn wrist-closed";
public final static String NIGHT_TURN_WRIST_READ = "Wrist Turn at Night-Read";
public final static String NIGHT_TURN_WRIST_CUSTOM_TIME = "Turn wrist at night-custom time";
public final static String NIGHT_TURN_WRIST_CUSTOM_TIME_LEVEL = "Wrist Turn at Night-Custom Time and Level";
public final static String FINDPHONE = "Anti-lost phone";
public final static String CHECK_WEAR_SETING_OPEN = "wear detection-open";
public final static String CHECK_WEAR_SETING_CLOSE = "Wear Detection-Closed";
public final static String FINDDEVICE_SETTING_OPEN = "Anti-lost device-open";
public final static String FINDDEVICE_SETTING_CLOSE = "Anti-lost device-shutdown";
public final static String FINDDEVICE_READ = "device anti-lost-read";
public final static String DEVICE_COUSTOM_READ = "Personalization-Read";
public final static String DEVICE_COUSTOM_SETTING = "Personalization-Settings";
public final static String SOCIAL_MSG_SETTING = "Social Message Alert-Settings";
public final static String SOCIAL_MSG_READ = "Social message reminder-read setting";
public final static String SOCIAL_MSG_SEND = "Social Message Reminder-Send Content";
public final static String SOCIAL_PHONE_IDLE_OR_OFFHOOK = "Social Message Reminder-Answered call";
public final static String DEVICE_CONTROL_PHONE = "Monitor bracelet-hang up, mute";
public final static String HEARTWRING_READ = "Heart rate alarm-read";
public final static String HEARTWRING_OPEN = "Heart rate alarm-open";
public final static String HEARTWRING_CLOSE = "Heart rate alarm-closed";
public final static String SPO2H_OPEN = "SpO2-read";
public final static String SPO2H_CLOSE = "Blood Oxygen-End";
public final static String SPO2H_AUTO_DETECT_READ = "Automatic blood oxygen detection-read";
public final static String SPO2H_AUTO_DETECT_OPEN = "Automatic blood oxygen detection-open";
public final static String SPO2H_AUTO_DETECT_CLOSE = "Automatic blood oxygen detection-closed";
public final static String FATIGUE_OPEN = "Fatigue-Read";
public final static String FATIGUE_CLOSE = "Fatigue-End";
public final static String WOMEN_SETTING = "Female status-setting";
public final static String WOMEN_READ = "female status-read";
public final static String COUNT_DOWN_WATCH = "Countdown-watch alone";
public final static String COUNT_DOWN_APP = "Countdown-App use";
public final static String COUNT_DOWN_APP_READ = "Countdown-Read";
public final static String SCREEN_LIGHT_SETTING = "Screen adjustment-settings";
public final static String SCREEN_LIGHT_READ = "Screen adjustment-read";
public final static String SCREEN_STYLE_READ = "screen style-read";
public final static String SCREEN_STYLE_SETTING = "Screen Style-Settings";
public final static String AIM_SPROT_CALC = "Target steps-calculation";
public final static String INSTITUTION_TRANSLATION = "Metric and English conversion";
public final static String READ_HEALTH_DRINK = "Read Health Data-Drinking";
public final static String READ_HEALTH_SLEEP = "Read Health Data-Sleep";
public final static String READ_HEALTH_SLEEP_FROM = "Read health data-sleep-from which day";
public final static String READ_HEALTH_SLEEP_SINGLEDAY = "Read health data-sleep-read this day";
public final static String READ_HEALTH_ORIGINAL = "Read health data-5 minutes";
public final static String READ_HEALTH_ORIGINAL_FROM = "Read health data-from which day";
public final static String READ_HEALTH_ORIGINAL_SINGLEDAY = "Read health data-read this day";
public final static String READ_HEALTH = "Read health data-all";
public final static String OAD = "Firmware upgrade";
public final static String SHOW_SP = "Show sp";
    public final static String SPORT_MODE_ORIGIN_READ = "Read Data-Sports Mode";
public final static String SPORT_MODE_ORIGIN_READSTAUTS = "Read State-Sports Mode";
public final static String SPORT_MODE_ORIGIN_START = "Enable-Sport Mode";
public final static String SPORT_MODE_ORIGIN_END = "End-Sport Mode";
public final static String SPO2H_ORIGIN_READ = "Read data-blood oxygen data";
public final static String HRV_ORIGIN_READ = "Read data-HRV data";
public final static String CLEAR_DEVICE_DATA = "Clear data";
public final static String DISCONNECT = "Bluetooth connection-disconnect";
public final static String DETECT_PTT = "PTT";
public final static String DETECT_START_ECG = "Begin measuring ECG";
public final static String DETECT_STOP_ECG = "End measuring ECG";
public final static String LOW_POWER_READ = "Low Power-Read";
public final static String LOW_POWER_OPEN = "Low Power-Open";
public final static String LOW_POWER_CLOSE = "Low Power-Off";
public final static String S22_READ_DATA = "S22-Data Read";
public final static String S22_READ_STATE = "S22-State Read";
public final static String S22_SETTING_STATE_OPEN = "S22-Status Setting (Open)";
public final static String S22_SETTING_STATE_CLOSE = "S22-Status Setting (Off)";
    public final static String NONE = "NONE";
    public final static String[] oprateStr = new String[]{
            PWD_COMFIRM, PERSONINFO_SYNC, SETTING_FIRST, PWD_MODIFY,
            HEART_DETECT_START, HEART_DETECT_STOP, BP_DETECT_START, BP_DETECT_STOP, BP_DETECTMODEL_SETTING, BP_DETECTMODEL_READ,
            BP_DETECTMODEL_SETTING_ADJUSTE_CANCEL, BP_DETECTMODEL_SETTING_ADJUSTE,
            SPORT_CURRENT_READ, CAMERA_START, CAMERA_STOP, ALARM_SETTING, ALARM_READ, ALARM_NEW_READ, ALARM_NEW_ADD, ALARM_NEW_MODIFY, ALARM_NEW_DELETE,
            LONGSEAT_SETTING_OPEN, LONGSEAT_SETTING_CLOSE, LONGSEAT_READ, LANGUAGE_CHINESE, LANGUAGE_ENGLISH,
            BATTERY, NIGHT_TURN_WRIST_OPEN, NIGHT_TURN_WRIST_CLOSE, NIGHT_TURN_WRIST_READ, NIGHT_TURN_WRIST_CUSTOM_TIME, NIGHT_TURN_WRIST_CUSTOM_TIME_LEVEL,
            DEVICE_COUSTOM_READ, DEVICE_COUSTOM_SETTING, FINDPHONE,
            CHECK_WEAR_SETING_OPEN, CHECK_WEAR_SETING_CLOSE,
            FINDDEVICE_SETTING_OPEN, FINDDEVICE_SETTING_CLOSE, FINDDEVICE_READ,
            SOCIAL_MSG_SETTING, SOCIAL_MSG_READ, SOCIAL_MSG_SEND, DEVICE_CONTROL_PHONE, SOCIAL_PHONE_IDLE_OR_OFFHOOK, HEARTWRING_READ, HEARTWRING_OPEN, HEARTWRING_CLOSE,
            SPO2H_OPEN, SPO2H_CLOSE, SPO2H_AUTO_DETECT_READ, SPO2H_AUTO_DETECT_OPEN, SPO2H_AUTO_DETECT_CLOSE, FATIGUE_OPEN, FATIGUE_CLOSE, WOMEN_SETTING, WOMEN_READ, COUNT_DOWN_WATCH, COUNT_DOWN_APP, COUNT_DOWN_APP_READ, SCREEN_LIGHT_SETTING, SCREEN_LIGHT_READ, SCREEN_STYLE_READ, SCREEN_STYLE_SETTING, AIM_SPROT_CALC, INSTITUTION_TRANSLATION,
            READ_HEALTH_SLEEP, READ_HEALTH_SLEEP_FROM, READ_HEALTH_SLEEP_SINGLEDAY, READ_HEALTH_DRINK, READ_HEALTH_ORIGINAL,
            READ_HEALTH_ORIGINAL_FROM, READ_HEALTH_ORIGINAL_SINGLEDAY, READ_HEALTH,
            OAD, SHOW_SP, SPORT_MODE_ORIGIN_READ, SPORT_MODE_ORIGIN_READSTAUTS, SPORT_MODE_ORIGIN_START, SPORT_MODE_ORIGIN_END, SPO2H_ORIGIN_READ, HRV_ORIGIN_READ, CLEAR_DEVICE_DATA, DISCONNECT
            , DETECT_START_ECG, DETECT_STOP_ECG, NONE, LOW_POWER_READ, LOW_POWER_OPEN, LOW_POWER_CLOSE,S22_READ_DATA,S22_READ_STATE,S22_SETTING_STATE_OPEN,S22_SETTING_STATE_CLOSE,DETECT_PTT
    };
}
