package com.timaimee.vpdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.timaimee.vpdemo.R;
import com.timaimee.vpdemo.adapter.GridAdatper;
import com.timaimee.vpdemo.oad.activity.OadActivity;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IBleNotifyResponse;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.data.IAlarm2DataListListener;
import com.veepoo.protocol.listener.data.IAlarmDataListener;
import com.veepoo.protocol.listener.data.IAllHealthDataListener;
import com.veepoo.protocol.listener.data.IAllSetDataListener;
import com.veepoo.protocol.listener.data.IAutoDetectOriginDataListener;
import com.veepoo.protocol.listener.data.IBPDetectDataListener;
import com.veepoo.protocol.listener.data.IBPSettingDataListener;
import com.veepoo.protocol.listener.data.IBatteryDataListener;
import com.veepoo.protocol.listener.data.ICameraDataListener;
import com.veepoo.protocol.listener.data.ICheckWearDataListener;
import com.veepoo.protocol.listener.data.ICountDownListener;
import com.veepoo.protocol.listener.data.ICustomProtocolStateListener;
import com.veepoo.protocol.listener.data.ICustomSettingDataListener;
import com.veepoo.protocol.listener.data.IDeviceControlPhoneModelState;
import com.veepoo.protocol.listener.data.IDeviceFuctionDataListener;
import com.veepoo.protocol.listener.data.IDrinkDataListener;
import com.veepoo.protocol.listener.data.IECGDetectListener;
import com.veepoo.protocol.listener.data.IFatigueDataListener;
import com.veepoo.protocol.listener.data.IFindDeviceDatalistener;
import com.veepoo.protocol.listener.data.IFindPhonelistener;
import com.veepoo.protocol.listener.data.IHRVOriginDataListener;
import com.veepoo.protocol.listener.data.IHeartDataListener;
import com.veepoo.protocol.listener.data.IHeartWaringDataListener;
import com.veepoo.protocol.listener.data.ILanguageDataListener;
import com.veepoo.protocol.listener.data.ILightDataCallBack;
import com.veepoo.protocol.listener.data.ILongSeatDataListener;
import com.veepoo.protocol.listener.data.ILowPowerListener;
import com.veepoo.protocol.listener.data.INightTurnWristeDataListener;
import com.veepoo.protocol.listener.data.IOriginData3Listener;
import com.veepoo.protocol.listener.data.IOriginDataListener;
import com.veepoo.protocol.listener.data.IOriginProgressListener;
import com.veepoo.protocol.listener.data.IPersonInfoDataListener;
import com.veepoo.protocol.listener.data.IPwdDataListener;
import com.veepoo.protocol.listener.data.IScreenLightListener;
import com.veepoo.protocol.listener.data.IScreenStyleListener;
import com.veepoo.protocol.listener.data.ISleepDataListener;
import com.veepoo.protocol.listener.data.ISocialMsgDataListener;
import com.veepoo.protocol.listener.data.ISpo2hDataListener;
import com.veepoo.protocol.listener.data.ISpo2hOriginDataListener;
import com.veepoo.protocol.listener.data.ISportDataListener;
import com.veepoo.protocol.listener.data.ISportModelOriginListener;
import com.veepoo.protocol.listener.data.ISportModelStateListener;
import com.veepoo.protocol.listener.data.IWomenDataListener;
import com.veepoo.protocol.model.datas.AlarmData;
import com.veepoo.protocol.model.datas.AlarmData2;
import com.veepoo.protocol.model.datas.AllSetData;
import com.veepoo.protocol.model.datas.AutoDetectOriginData;
import com.veepoo.protocol.model.datas.AutoDetectStateData;
import com.veepoo.protocol.model.datas.BatteryData;
import com.veepoo.protocol.model.datas.BpData;
import com.veepoo.protocol.model.datas.BpSettingData;
import com.veepoo.protocol.model.datas.CheckWearData;
import com.veepoo.protocol.model.datas.CountDownData;
import com.veepoo.protocol.model.datas.DrinkData;
import com.veepoo.protocol.model.datas.EcgDetectInfo;
import com.veepoo.protocol.model.datas.EcgDetectResult;
import com.veepoo.protocol.model.datas.EcgDetectState;
import com.veepoo.protocol.model.datas.FatigueData;
import com.veepoo.protocol.model.datas.FindDeviceData;
import com.veepoo.protocol.model.datas.FunctionDeviceSupportData;
import com.veepoo.protocol.model.datas.FunctionSocailMsgData;
import com.veepoo.protocol.model.datas.HRVOriginData;
import com.veepoo.protocol.model.datas.HeartData;
import com.veepoo.protocol.model.datas.HeartWaringData;
import com.veepoo.protocol.model.datas.LanguageData;
import com.veepoo.protocol.model.datas.LongSeatData;
import com.veepoo.protocol.model.datas.LowPowerData;
import com.veepoo.protocol.model.datas.NightTurnWristeData;
import com.veepoo.protocol.model.datas.OriginData;
import com.veepoo.protocol.model.datas.OriginData3;
import com.veepoo.protocol.model.datas.OriginHalfHourData;
import com.veepoo.protocol.model.datas.PersonInfoData;
import com.veepoo.protocol.model.datas.PwdData;
import com.veepoo.protocol.model.datas.ScreenLightData;
import com.veepoo.protocol.model.datas.ScreenStyleData;
import com.veepoo.protocol.model.datas.SleepData;
import com.veepoo.protocol.model.datas.SleepPrecisionData;
import com.veepoo.protocol.model.datas.Spo2hData;
import com.veepoo.protocol.model.datas.Spo2hOriginData;
import com.veepoo.protocol.model.datas.SportData;
import com.veepoo.protocol.model.datas.SportModelOriginHeadData;
import com.veepoo.protocol.model.datas.SportModelOriginItemData;
import com.veepoo.protocol.model.datas.SportModelStateData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.datas.WomenData;
import com.veepoo.protocol.model.enums.EAllSetType;
import com.veepoo.protocol.model.enums.EBPDetectModel;
import com.veepoo.protocol.model.enums.ECameraStatus;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.ELanguage;
import com.veepoo.protocol.model.enums.EOprateStauts;
import com.veepoo.protocol.model.enums.ESex;
import com.veepoo.protocol.model.enums.ESocailMsg;
import com.veepoo.protocol.model.enums.ESpo2hDataType;
import com.veepoo.protocol.model.enums.EWomenStatus;
import com.veepoo.protocol.model.settings.Alarm2Setting;
import com.veepoo.protocol.model.settings.AlarmSetting;
import com.veepoo.protocol.model.settings.AllSetSetting;
import com.veepoo.protocol.model.settings.AutoDetectStateSetting;
import com.veepoo.protocol.model.settings.BpSetting;
import com.veepoo.protocol.model.settings.CheckWearSetting;
import com.veepoo.protocol.model.settings.ContentPhoneSetting;
import com.veepoo.protocol.model.settings.ContentSetting;
import com.veepoo.protocol.model.settings.ContentSmsSetting;
import com.veepoo.protocol.model.settings.ContentSocailSetting;
import com.veepoo.protocol.model.settings.CountDownSetting;
import com.veepoo.protocol.model.settings.CustomSetting;
import com.veepoo.protocol.model.settings.CustomSettingData;
import com.veepoo.protocol.model.settings.HeartWaringSetting;
import com.veepoo.protocol.model.settings.LongSeatSetting;
import com.veepoo.protocol.model.settings.NightTurnWristSetting;
import com.veepoo.protocol.model.settings.ScreenSetting;
import com.veepoo.protocol.model.settings.WomenSetting;
import com.veepoo.protocol.util.Spo2hOriginUtil;
import com.veepoo.protocol.util.VPLogger;
import com.veepoo.protocol.util.VpBleByteUtil;
import com.vp.cso.hrvreport.JNIChange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.timaimee.vpdemo.activity.Oprate.AIM_SPROT_CALC;
import static com.timaimee.vpdemo.activity.Oprate.ALARM_NEW_ADD;
import static com.timaimee.vpdemo.activity.Oprate.ALARM_NEW_DELETE;
import static com.timaimee.vpdemo.activity.Oprate.ALARM_NEW_MODIFY;
import static com.timaimee.vpdemo.activity.Oprate.ALARM_NEW_READ;
import static com.timaimee.vpdemo.activity.Oprate.ALARM_READ;
import static com.timaimee.vpdemo.activity.Oprate.ALARM_SETTING;
import static com.timaimee.vpdemo.activity.Oprate.BATTERY;
import static com.timaimee.vpdemo.activity.Oprate.BP_DETECTMODEL_READ;
import static com.timaimee.vpdemo.activity.Oprate.BP_DETECTMODEL_SETTING;
import static com.timaimee.vpdemo.activity.Oprate.BP_DETECTMODEL_SETTING_ADJUSTE;
import static com.timaimee.vpdemo.activity.Oprate.BP_DETECTMODEL_SETTING_ADJUSTE_CANCEL;
import static com.timaimee.vpdemo.activity.Oprate.BP_DETECT_START;
import static com.timaimee.vpdemo.activity.Oprate.BP_DETECT_STOP;
import static com.timaimee.vpdemo.activity.Oprate.CAMERA_START;
import static com.timaimee.vpdemo.activity.Oprate.CAMERA_STOP;
import static com.timaimee.vpdemo.activity.Oprate.CHECK_WEAR_SETING_CLOSE;
import static com.timaimee.vpdemo.activity.Oprate.CHECK_WEAR_SETING_OPEN;
import static com.timaimee.vpdemo.activity.Oprate.CLEAR_DEVICE_DATA;
import static com.timaimee.vpdemo.activity.Oprate.COUNT_DOWN_APP;
import static com.timaimee.vpdemo.activity.Oprate.COUNT_DOWN_APP_READ;
import static com.timaimee.vpdemo.activity.Oprate.COUNT_DOWN_WATCH;
import static com.timaimee.vpdemo.activity.Oprate.DETECT_PTT;
import static com.timaimee.vpdemo.activity.Oprate.DETECT_START_ECG;
import static com.timaimee.vpdemo.activity.Oprate.DETECT_STOP_ECG;
import static com.timaimee.vpdemo.activity.Oprate.DEVICE_CONTROL_PHONE;
import static com.timaimee.vpdemo.activity.Oprate.DEVICE_COUSTOM_READ;
import static com.timaimee.vpdemo.activity.Oprate.DEVICE_COUSTOM_SETTING;
import static com.timaimee.vpdemo.activity.Oprate.DISCONNECT;
import static com.timaimee.vpdemo.activity.Oprate.FATIGUE_CLOSE;
import static com.timaimee.vpdemo.activity.Oprate.FATIGUE_OPEN;
import static com.timaimee.vpdemo.activity.Oprate.FINDDEVICE_READ;
import static com.timaimee.vpdemo.activity.Oprate.FINDDEVICE_SETTING_CLOSE;
import static com.timaimee.vpdemo.activity.Oprate.FINDDEVICE_SETTING_OPEN;
import static com.timaimee.vpdemo.activity.Oprate.FINDPHONE;
import static com.timaimee.vpdemo.activity.Oprate.HEARTWRING_CLOSE;
import static com.timaimee.vpdemo.activity.Oprate.HEARTWRING_OPEN;
import static com.timaimee.vpdemo.activity.Oprate.HEARTWRING_READ;
import static com.timaimee.vpdemo.activity.Oprate.HEART_DETECT_START;
import static com.timaimee.vpdemo.activity.Oprate.HEART_DETECT_STOP;
import static com.timaimee.vpdemo.activity.Oprate.HRV_ORIGIN_READ;
import static com.timaimee.vpdemo.activity.Oprate.INSTITUTION_TRANSLATION;
import static com.timaimee.vpdemo.activity.Oprate.LANGUAGE_CHINESE;
import static com.timaimee.vpdemo.activity.Oprate.LANGUAGE_ENGLISH;
import static com.timaimee.vpdemo.activity.Oprate.LONGSEAT_READ;
import static com.timaimee.vpdemo.activity.Oprate.LONGSEAT_SETTING_CLOSE;
import static com.timaimee.vpdemo.activity.Oprate.LONGSEAT_SETTING_OPEN;
import static com.timaimee.vpdemo.activity.Oprate.LOW_POWER_CLOSE;
import static com.timaimee.vpdemo.activity.Oprate.LOW_POWER_OPEN;
import static com.timaimee.vpdemo.activity.Oprate.LOW_POWER_READ;
import static com.timaimee.vpdemo.activity.Oprate.NIGHT_TURN_WRIST_CLOSE;
import static com.timaimee.vpdemo.activity.Oprate.NIGHT_TURN_WRIST_CUSTOM_TIME;
import static com.timaimee.vpdemo.activity.Oprate.NIGHT_TURN_WRIST_CUSTOM_TIME_LEVEL;
import static com.timaimee.vpdemo.activity.Oprate.NIGHT_TURN_WRIST_OPEN;
import static com.timaimee.vpdemo.activity.Oprate.NIGHT_TURN_WRIST_READ;
import static com.timaimee.vpdemo.activity.Oprate.OAD;
import static com.timaimee.vpdemo.activity.Oprate.PERSONINFO_SYNC;
import static com.timaimee.vpdemo.activity.Oprate.PWD_COMFIRM;
import static com.timaimee.vpdemo.activity.Oprate.PWD_MODIFY;
import static com.timaimee.vpdemo.activity.Oprate.READ_HEALTH;
import static com.timaimee.vpdemo.activity.Oprate.READ_HEALTH_DRINK;
import static com.timaimee.vpdemo.activity.Oprate.READ_HEALTH_ORIGINAL;
import static com.timaimee.vpdemo.activity.Oprate.READ_HEALTH_ORIGINAL_FROM;
import static com.timaimee.vpdemo.activity.Oprate.READ_HEALTH_ORIGINAL_SINGLEDAY;
import static com.timaimee.vpdemo.activity.Oprate.READ_HEALTH_SLEEP;
import static com.timaimee.vpdemo.activity.Oprate.READ_HEALTH_SLEEP_FROM;
import static com.timaimee.vpdemo.activity.Oprate.READ_HEALTH_SLEEP_SINGLEDAY;
import static com.timaimee.vpdemo.activity.Oprate.S22_READ_DATA;
import static com.timaimee.vpdemo.activity.Oprate.S22_READ_STATE;
import static com.timaimee.vpdemo.activity.Oprate.S22_SETTING_STATE_CLOSE;
import static com.timaimee.vpdemo.activity.Oprate.S22_SETTING_STATE_OPEN;
import static com.timaimee.vpdemo.activity.Oprate.SCREEN_LIGHT_READ;
import static com.timaimee.vpdemo.activity.Oprate.SCREEN_LIGHT_SETTING;
import static com.timaimee.vpdemo.activity.Oprate.SCREEN_STYLE_READ;
import static com.timaimee.vpdemo.activity.Oprate.SCREEN_STYLE_SETTING;
import static com.timaimee.vpdemo.activity.Oprate.SHOW_SP;
import static com.timaimee.vpdemo.activity.Oprate.SOCIAL_MSG_READ;
import static com.timaimee.vpdemo.activity.Oprate.SOCIAL_MSG_SEND;
import static com.timaimee.vpdemo.activity.Oprate.SOCIAL_MSG_SETTING;
import static com.timaimee.vpdemo.activity.Oprate.SOCIAL_PHONE_IDLE_OR_OFFHOOK;
import static com.timaimee.vpdemo.activity.Oprate.SPO2H_AUTO_DETECT_CLOSE;
import static com.timaimee.vpdemo.activity.Oprate.SPO2H_AUTO_DETECT_OPEN;
import static com.timaimee.vpdemo.activity.Oprate.SPO2H_AUTO_DETECT_READ;
import static com.timaimee.vpdemo.activity.Oprate.SPO2H_CLOSE;
import static com.timaimee.vpdemo.activity.Oprate.SPO2H_OPEN;
import static com.timaimee.vpdemo.activity.Oprate.SPO2H_ORIGIN_READ;
import static com.timaimee.vpdemo.activity.Oprate.SPORT_CURRENT_READ;
import static com.timaimee.vpdemo.activity.Oprate.SPORT_MODE_ORIGIN_END;
import static com.timaimee.vpdemo.activity.Oprate.SPORT_MODE_ORIGIN_READ;
import static com.timaimee.vpdemo.activity.Oprate.SPORT_MODE_ORIGIN_READSTAUTS;
import static com.timaimee.vpdemo.activity.Oprate.SPORT_MODE_ORIGIN_START;
import static com.timaimee.vpdemo.activity.Oprate.WOMEN_READ;
import static com.timaimee.vpdemo.activity.Oprate.WOMEN_SETTING;
import static com.timaimee.vpdemo.activity.Oprate.oprateStr;
import static com.veepoo.protocol.model.enums.EFunctionStatus.SUPPORT;
import static com.veepoo.protocol.model.enums.EFunctionStatus.SUPPORT_CLOSE;
import static com.veepoo.protocol.model.enums.EFunctionStatus.SUPPORT_OPEN;

/**
 * Created by timaimee on 2017/2/8.
 */
public class OperaterActivity extends Activity implements AdapterView.OnItemClickListener {
    private final static String TAG = OperaterActivity.class.getSimpleName();
    TextView tv1, tv2, tv3;
    GridView mGridView;
    List<Map<String, String>> mGridData = new ArrayList<>();
    GridAdatper mGridAdapter;
    Context mContext = OperaterActivity.this;
    private String deviceaddress;
    boolean isSleepPrecision = false;
    Message msg;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s = msg.obj.toString();
//            Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();

            switch (msg.what) {
                case 1:
                    tv1.setText(s + "\n");
                    break;
                case 2:
                    tv2.setText(s + "\n");
                    break;
                case 3:
                    tv3.setText(s + "\n");
                    break;
            }
        }
    };
    WriteResponse writeResponse = new WriteResponse();


    /**
     * Password verification to get the following information
     */
    int watchDataDay = 3;
    int contactMsgLength = 0;
    int allMsgLenght = 4;
    private int deviceNumber = -1;
    private String deviceVersion;
    private String deviceTestVersion;
    boolean isOadModel = false;
    boolean isNewSportCalc = false;
    boolean isInPttModel = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);
        mContext = getApplicationContext();
        deviceaddress = getIntent().getStringExtra("deviceaddress");
        tv1 = (TextView) super.findViewById(R.id.tv1);
        tv2 = (TextView) super.findViewById(R.id.tv2);
        tv3 = (TextView) super.findViewById(R.id.tv3);
        initGridView();
        listenDeviceCallbackData();
    }

    private void initGridView() {
        mGridView = (GridView) findViewById(R.id.main_gridview);
        for (int i = 0; i < oprateStr.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("str", oprateStr[i]);
            mGridData.add(map);
        }
        mGridAdapter = new GridAdatper(this, mGridData);
        mGridView.setAdapter(mGridAdapter);
        mGridView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
        String oprater = mGridData.get(position).get("str");
        Toast.makeText(mContext, oprater, Toast.LENGTH_SHORT).show();
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        if (oprater.equals(HEART_DETECT_START)) {
            VPOperateManager.getMangerInstance(mContext).startDetectHeart(writeResponse, new IHeartDataListener() {

                @Override
                public void onDataChange(HeartData heart) {
                    String message = "heart:\n" + heart.toString();

                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(LOW_POWER_READ)) {
            VPOperateManager.getMangerInstance(mContext).readLowPower(writeResponse, new ILowPowerListener() {
                @Override
                public void onLowpowerDataDataChange(LowPowerData lowPowerData) {
                    String message = "onLowpowerDataDataChange read:\n" + lowPowerData.toString();
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(LOW_POWER_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).settingLowpower(writeResponse, new ILowPowerListener() {
                @Override
                public void onLowpowerDataDataChange(LowPowerData lowPowerData) {
                    String message = "onLowpowerDataDataChange open:\n" + lowPowerData.toString();
                    Logger.t(TAG).i(message);
                }
            }, true);
        } else if (oprater.equals(LOW_POWER_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).settingLowpower(writeResponse, new ILowPowerListener() {
                @Override
                public void onLowpowerDataDataChange(LowPowerData lowPowerData) {
                    String message = "onLowpowerDataDataChange close:\n" + lowPowerData.toString();
                    Logger.t(TAG).i(message);
                }
            }, false);
        } else if (oprater.equals(DETECT_PTT)) {

            Intent intent = new Intent(OperaterActivity.this, PttActivity.class);
            intent.putExtra("inPttModel", isInPttModel);
            startActivity(intent);

        } else if (oprater.equals(DETECT_START_ECG) || oprater.equals(DETECT_STOP_ECG)) {
            startActivity(new Intent(OperaterActivity.this, EcgDetectActivity.class));
        } else if (oprater.equals(HEART_DETECT_STOP)) {
            Logger.t(TAG).i("HEART_DETECT_STOP");
            VPOperateManager.getMangerInstance(mContext).stopDetectHeart(writeResponse);
        } else if (oprater.equals(BP_DETECT_START)) {
            tv1.setText(BP_DETECT_START + ",等待50s...");
            VPOperateManager.getMangerInstance(mContext).startDetectBP(writeResponse, new IBPDetectDataListener() {
                @Override
                public void onDataChange(BpData bpData) {
                    String message = "BpData date statues:\n" + bpData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, EBPDetectModel.DETECT_MODEL_PUBLIC);
        } else if (oprater.equals(BP_DETECT_STOP)) {
            tv1.setText(BP_DETECT_STOP);
            VPOperateManager.getMangerInstance(mContext).stopDetectBP(writeResponse, EBPDetectModel.DETECT_MODEL_PUBLIC);
        } else if (oprater.equals(BP_DETECTMODEL_SETTING)) {
            boolean isOpenPrivateModel = true;
            boolean isAngioAdjuste = false;
            BpSetting bpSetting = new BpSetting(isOpenPrivateModel, 111, 88);
            //Whether to enable the dynamic blood pressure adjustment mode, the function flag is returned in the password verification
            bpSetting.setAngioAdjuste(isAngioAdjuste);
            VPOperateManager.getMangerInstance(mContext).settingDetectBP(writeResponse, new IBPSettingDataListener() {
                @Override
                public void onDataChange(BpSettingData bpSettingData) {
                    String message = "BpSettingData:\n" + bpSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, bpSetting);
        } else if (oprater.equals(BP_DETECTMODEL_READ)) {
            VPOperateManager.getMangerInstance(mContext).readDetectBP(writeResponse, new IBPSettingDataListener() {
                @Override
                public void onDataChange(BpSettingData bpSettingData) {

                    String message = "BpSettingData:\n" + bpSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);

                }
            });
        } else if (oprater.equals(BP_DETECTMODEL_SETTING_ADJUSTE)) {
            boolean isOpenPrivateModel = false;
            boolean isAngioAdjuste = true;
            BpSetting bpSetting = new BpSetting(isOpenPrivateModel, 111, 88);
            //Whether to enable the dynamic blood pressure adjustment mode, the function flag is returned in the password verification
            bpSetting.setAngioAdjuste(isAngioAdjuste);
            VPOperateManager.getMangerInstance(mContext).settingDetectBP(writeResponse, new IBPSettingDataListener() {
                @Override
                public void onDataChange(BpSettingData bpSettingData) {
                    String message = "BpSettingData:\n" + bpSettingData.toString();
                    Logger.t(TAG).i(message);
//                    sendMsg(message, 1);
                }
            }, bpSetting);
        } else if (oprater.equals(BP_DETECTMODEL_SETTING_ADJUSTE_CANCEL)) {
            boolean isOpenPrivateModel = false;
            boolean isAngioAdjuste = true;
            BpSetting bpSetting = new BpSetting(isOpenPrivateModel, 111, 88);
            //Whether to enable the dynamic blood pressure adjustment mode, the function flag is returned in the password verification
            bpSetting.setAngioAdjuste(isAngioAdjuste);
            VPOperateManager.getMangerInstance(mContext).cancelAngioAdjust(writeResponse, new IBPSettingDataListener() {
                @Override
                public void onDataChange(BpSettingData bpSettingData) {
                    String message = "BpSettingData:\n" + bpSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, bpSetting);
        } else if (oprater.equals(PWD_COMFIRM)) {
            boolean is24Hourmodel = false;
            VPOperateManager.getMangerInstance(mContext).confirmDevicePwd(writeResponse, new IPwdDataListener() {
                @Override
                public void onPwdDataChange(PwdData pwdData) {
                    String message = "PwdData:\n" + pwdData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);

                    deviceNumber = pwdData.getDeviceNumber();
                    deviceVersion = pwdData.getDeviceVersion();
                    deviceTestVersion = pwdData.getDeviceTestVersion();
                }
            }, new IDeviceFuctionDataListener() {
                @Override
                public void onFunctionSupportDataChange(FunctionDeviceSupportData functionSupport) {
                    String message = "FunctionDeviceSupportData:\n" + functionSupport.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 2);
                    EFunctionStatus newCalcSport = functionSupport.getNewCalcSport();
                    if (newCalcSport != null && newCalcSport.equals(SUPPORT)) {
                        isNewSportCalc = true;
                    } else {
                        isNewSportCalc = false;
                    }
                    watchDataDay = functionSupport.getWathcDay();
                    contactMsgLength = functionSupport.getContactMsgLength();
                    allMsgLenght = functionSupport.getAllMsgLength();
                    isSleepPrecision = functionSupport.getPrecisionSleep() == SUPPORT;
                }
            }, new ISocialMsgDataListener() {
                @Override
                public void onSocialMsgSupportDataChange(FunctionSocailMsgData socailMsgData) {
                    String message = "FunctionSocailMsgData:\n" + socailMsgData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 3);
                }
            }, new ICustomSettingDataListener() {
                @Override
                public void OnSettingDataChange(CustomSettingData customSettingData) {
                    String message = "FunctionCustomSettingData:\n" + customSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 4);
                }
            }, "0000", is24Hourmodel);

        } else if (oprater.equals(PWD_MODIFY)) {
            VPOperateManager.getMangerInstance(mContext).modifyDevicePwd(writeResponse, new IPwdDataListener() {
                @Override
                public void onPwdDataChange(PwdData pwd) {
                    String message = "PwdData:\n" + pwd.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, "0000");
        } else if (oprater.equals(SPORT_CURRENT_READ)) {
            VPOperateManager.getMangerInstance(mContext).readSportStep(writeResponse, new ISportDataListener() {
                @Override
                public void onSportDataChange(SportData sportData) {
                    String message = "Current step count:\n" + sportData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(PERSONINFO_SYNC)) {
            VPOperateManager.getMangerInstance(mContext).syncPersonInfo(writeResponse, new IPersonInfoDataListener() {
                @Override
                public void OnPersoninfoDataChange(EOprateStauts EOprateStauts) {
                    String message = "Sync personal information:\n" + EOprateStauts.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new PersonInfoData(ESex.MAN, 178, 60, 20, 8000));
        } else if (oprater.equals(CAMERA_START)) {
            VPOperateManager.getMangerInstance(mContext).startCamera(writeResponse, new ICameraDataListener() {
                @Override
                public void OnCameraDataChange(ECameraStatus oprateStauts) {

                }


            });
        } else if (oprater.equals(CAMERA_STOP)) {
            VPOperateManager.getMangerInstance(mContext).stopCamera(writeResponse, new ICameraDataListener() {
                @Override
                public void OnCameraDataChange(ECameraStatus oprateStauts) {

                }


            });
        } else if (oprater.equals(ALARM_SETTING)) {
            List<AlarmSetting> alarmSettingList = new ArrayList<>(3);

            AlarmSetting alarmSetting1 = new AlarmSetting(14, 10, true);
            AlarmSetting alarmSetting2 = new AlarmSetting(15, 20, true);
            AlarmSetting alarmSetting3 = new AlarmSetting(16, 30, true);

            alarmSettingList.add(alarmSetting1);
            alarmSettingList.add(alarmSetting2);
            alarmSettingList.add(alarmSetting3);

            VPOperateManager.getMangerInstance(mContext).settingAlarm(writeResponse, new IAlarmDataListener() {
                @Override
                public void onAlarmDataChangeListener(AlarmData alarmData) {
                    String message = "Set alarm:\n" + alarmData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, alarmSettingList);
        } else if (oprater.equals(ALARM_READ)) {
            VPOperateManager.getMangerInstance(mContext).readAlarm(writeResponse, new IAlarmDataListener() {
                @Override
                public void onAlarmDataChangeListener(AlarmData alarmData) {
                    String message = "Read alarm:\n" + alarmData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(ALARM_NEW_READ)) {
            VPOperateManager.getMangerInstance(mContext).readAlarm2(writeResponse, new IAlarm2DataListListener() {
                @Override
                public void onAlarmDataChangeListListener(AlarmData2 alarmData2) {
                    String message = "Read alarm [new version]:\n" + alarmData2.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(ALARM_NEW_DELETE)) {
            int deleteID = 1;
            Alarm2Setting alarm2Setting = getMultiAlarmSetting();
            alarm2Setting.setAlarmId(deleteID);
            VPOperateManager.getMangerInstance(mContext).deleteAlarm2(writeResponse, new IAlarm2DataListListener() {
                @Override
                public void onAlarmDataChangeListListener(AlarmData2 alarmData2) {
                    String message = "Delete alarm [new version]:\n" + alarmData2.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
                //String bluetoothAddress, int alarmId, int alarmHour, int alarmMinute, String repeatStatus, int scene, String unRepeatDate, boolean isOpen
            }, alarm2Setting);
        } else if (oprater.equals(ALARM_NEW_ADD)) {
            Alarm2Setting alarm2Setting = getMultiAlarmSetting();
            VPOperateManager.getMangerInstance(mContext).addAlarm2(writeResponse, new IAlarm2DataListListener() {
                @Override
                public void onAlarmDataChangeListListener(AlarmData2 alarmData2) {
                    String message = "Add alarm clock [new version]:\n" + alarmData2.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, alarm2Setting);
        } else if (oprater.equals(ALARM_NEW_MODIFY)) {
            Alarm2Setting alarm2Setting = getMultiAlarmSetting();
            int modifyID = 2;
            alarm2Setting.setAlarmId(modifyID);
            alarm2Setting.setAlarmHour(10);
            alarm2Setting.setOpen(false);
            VPOperateManager.getMangerInstance(mContext).modifyAlarm2(writeResponse, new IAlarm2DataListListener() {
                @Override
                public void onAlarmDataChangeListListener(AlarmData2 alarmData2) {
                    String message = "Modify the alarm clock [new version]:\n" + alarmData2.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, alarm2Setting);
        } else if (oprater.equals(LONGSEAT_SETTING_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).settingLongSeat(writeResponse, new LongSeatSetting(10, 35, 11, 45, 60, true), new ILongSeatDataListener() {
                @Override
                public void onLongSeatDataChange(LongSeatData longSeat) {
                    String message = "Set sedentary-open:\n" + longSeat.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(LONGSEAT_SETTING_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).settingLongSeat(writeResponse, new LongSeatSetting(10, 40, 12, 40, 40, false), new ILongSeatDataListener() {

                @Override
                public void onLongSeatDataChange(LongSeatData longSeat) {
                    String message = "Set sedentary-off:\n" + longSeat.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(LONGSEAT_READ)) {
            VPOperateManager.getMangerInstance(mContext).readLongSeat(writeResponse, new ILongSeatDataListener() {
                @Override
                public void onLongSeatDataChange(LongSeatData longSeat) {
                    String message = "Set sedentary-read:\n" + longSeat.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(LANGUAGE_CHINESE)) {
            VPOperateManager.getMangerInstance(mContext).settingDeviceLanguage(writeResponse, new ILanguageDataListener() {
                @Override
                public void onLanguageDataChange(LanguageData languageData) {
                    String message = "Set language (Chinese):\n" + languageData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, ELanguage.CHINA);
        } else if (oprater.equals(LANGUAGE_ENGLISH)) {
            VPOperateManager.getMangerInstance(mContext).settingDeviceLanguage(writeResponse, new ILanguageDataListener() {
                @Override
                public void onLanguageDataChange(LanguageData languageData) {
                    String message = "Set language (English):\n" + languageData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, ELanguage.ENGLISH);
        } else if (oprater.equals(BATTERY)) {
            VPOperateManager.getMangerInstance(mContext).readBattery(writeResponse, new IBatteryDataListener() {
                @Override
                public void onDataChange(BatteryData batteryData) {
                    String message = "Battery level:\n" + batteryData.getBatteryLevel() + "\n" + "Battery:" + batteryData.getBatteryLevel() * 25 + "%";
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(NIGHT_TURN_WRIST_READ)) {
            VPOperateManager.getMangerInstance(mContext).readNightTurnWriste(writeResponse, new INightTurnWristeDataListener() {
                @Override
                public void onNightTurnWristeDataChange(NightTurnWristeData nightTurnWristeData) {
                    String message = "Wrist Turn at Night-Read:\n" + nightTurnWristeData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(NIGHT_TURN_WRIST_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).settingNightTurnWriste(writeResponse, new INightTurnWristeDataListener() {
                @Override
                public void onNightTurnWristeDataChange(NightTurnWristeData nightTurnWristeData) {
                    String message = "Turn wrist at night-open:\n" + nightTurnWristeData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, true);
        } else if (oprater.equals(NIGHT_TURN_WRIST_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).settingNightTurnWriste(writeResponse, new INightTurnWristeDataListener() {
                @Override
                public void onNightTurnWristeDataChange(NightTurnWristeData nightTurnWristeData) {
                    String message = "Night wrist turn-close:\n" + nightTurnWristeData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, false);
        } else if (oprater.equals(NIGHT_TURN_WRIST_CUSTOM_TIME)) {
            final boolean isOpen = true;
            TimeData startTime = new TimeData(10, 0);
            TimeData endTime = new TimeData(20, 0);
            VPOperateManager.getMangerInstance(mContext).settingNightTurnWriste(writeResponse, new INightTurnWristeDataListener() {
                @Override
                public void onNightTurnWristeDataChange(NightTurnWristeData nightTurnWristeData) {
                    String message = "Turn wrist at night-" + isOpen + ":\n" + nightTurnWristeData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, isOpen, startTime, endTime);
        } else if (oprater.equals(NIGHT_TURN_WRIST_CUSTOM_TIME_LEVEL)) {
            final boolean isOpen = true;
            TimeData startTime = new TimeData(10, 0);
            TimeData endTime = new TimeData(20, 0);
            int level = 2;
            VPOperateManager.getMangerInstance(mContext).settingNightTurnWriste(writeResponse, new INightTurnWristeDataListener() {
                @Override
                public void onNightTurnWristeDataChange(NightTurnWristeData nightTurnWristeData) {
                    String message = "Turn wrist at night-" + isOpen + ":\n" + nightTurnWristeData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new NightTurnWristSetting(isOpen, startTime, endTime, level));
        } else if (oprater.equals(DISCONNECT)) {
            VPOperateManager.getMangerInstance(mContext).disconnectWatch(writeResponse);
            finish();
        } else if (oprater.equals(FINDPHONE)) {
            VPOperateManager.getMangerInstance(mContext).settingFindPhoneListener(new IFindPhonelistener() {
                @Override
                public void findPhone() {
                    String message = "(Listening to the phone to find the phone)-where is the phone,make some noise!";
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(DEVICE_COUSTOM_READ)) {
            VPOperateManager.getMangerInstance(mContext).readCustomSetting(writeResponse, new ICustomSettingDataListener() {
                @Override
                public void OnSettingDataChange(CustomSettingData customSettingData) {
                    String message = "Personalized status-metric / English / hour (12/24) / 5 minute measurement switch (heart rate / blood pressure)-read:\n" + customSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(DEVICE_COUSTOM_SETTING)) {
            boolean isHaveMetricSystem = true;
            boolean isMetric = true;
            boolean is24Hour = true;
            boolean isOpenAutoHeartDetect = true;
            boolean isOpenAutoBpDetect = true;
            EFunctionStatus isOpenSportRemain = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenVoiceBpHeart = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenFindPhoneUI = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenStopWatch = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenSpo2hLowRemind = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenWearDetectSkin = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenAutoInCall = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenAutoHRV = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenDisconnectRemind = EFunctionStatus.UNSUPPORT;
            CustomSetting customSetting = new CustomSetting(isHaveMetricSystem, isMetric, is24Hour, isOpenAutoHeartDetect, isOpenAutoBpDetect
                    , isOpenSportRemain, isOpenVoiceBpHeart, isOpenFindPhoneUI, isOpenStopWatch,
                    isOpenSpo2hLowRemind, isOpenWearDetectSkin, isOpenAutoInCall, isOpenAutoHRV, isOpenDisconnectRemind
            );
            customSetting.setIsOpenLongClickLockScreen(SUPPORT_CLOSE);
            VPOperateManager.getMangerInstance(mContext).changeCustomSetting(writeResponse, new ICustomSettingDataListener() {
                @Override
                public void OnSettingDataChange(CustomSettingData customSettingData) {
                    String message = "Personalized status-metric / English / hour (12/24) / 5 minute measurement switch (heart rate / blood pressure)-setting:\n" + customSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, customSetting);
        } else if (oprater.equals(CHECK_WEAR_SETING_OPEN)) {
            CheckWearSetting checkWearSetting = new CheckWearSetting();
            checkWearSetting.setOpen(true);
            VPOperateManager.getMangerInstance(mContext).setttingCheckWear(writeResponse, new ICheckWearDataListener() {
                @Override
                public void onCheckWearDataChange(CheckWearData checkWearData) {
                    String message = "Wear detection-open:\n" + checkWearData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, checkWearSetting);
        } else if (oprater.equals(CHECK_WEAR_SETING_CLOSE)) {
            CheckWearSetting checkWearSetting = new CheckWearSetting();
            checkWearSetting.setOpen(false);
            VPOperateManager.getMangerInstance(mContext).setttingCheckWear(writeResponse, new ICheckWearDataListener() {
                @Override
                public void onCheckWearDataChange(CheckWearData checkWearData) {
                    String message = "Wear detection-off:\n" + checkWearData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, checkWearSetting);
        } else if (oprater.equals(FINDDEVICE_SETTING_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).settingFindDevice(writeResponse, new IFindDeviceDatalistener() {
                @Override
                public void onFindDevice(FindDeviceData findDeviceData) {
                    String message = "Anti-lost-open:\n" + findDeviceData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, true);
        } else if (oprater.equals(FINDDEVICE_SETTING_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).settingFindDevice(writeResponse, new IFindDeviceDatalistener() {
                @Override
                public void onFindDevice(FindDeviceData findDeviceData) {
                    String message = "Anti-lost-close:\n" + findDeviceData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, false);
        } else if (oprater.equals(FINDDEVICE_READ)) {
            VPOperateManager.getMangerInstance(mContext).readFindDevice(writeResponse, new IFindDeviceDatalistener() {
                @Override
                public void onFindDevice(FindDeviceData findDeviceData) {
                    String message = "Anti-lost-read:\n" + findDeviceData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(SOCIAL_MSG_READ)) {
            VPOperateManager.getMangerInstance(mContext).readSocialMsg(writeResponse, new ISocialMsgDataListener() {
                @Override
                public void onSocialMsgSupportDataChange(FunctionSocailMsgData socailMsgData) {
                    String message = " Social Information Reminder-Read:\n" + socailMsgData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(SOCIAL_MSG_SETTING)) {
            FunctionSocailMsgData socailMsgData = new FunctionSocailMsgData();
            socailMsgData.setPhone(SUPPORT);
            socailMsgData.setMsg(SUPPORT);
            socailMsgData.setWechat(SUPPORT_OPEN);
            socailMsgData.setQq(SUPPORT_OPEN);
            socailMsgData.setFacebook(SUPPORT_CLOSE);
            socailMsgData.setTwitter(SUPPORT_OPEN);
            socailMsgData.setWhats(SUPPORT_OPEN);
            socailMsgData.setSina(EFunctionStatus.UNSUPPORT);
            socailMsgData.setFlickr(EFunctionStatus.UNSUPPORT);
            socailMsgData.setLinkin(EFunctionStatus.UNSUPPORT);
            socailMsgData.setLine(EFunctionStatus.UNSUPPORT);
            socailMsgData.setInstagram(EFunctionStatus.UNSUPPORT);
            socailMsgData.setSnapchat(EFunctionStatus.UNSUPPORT);
            socailMsgData.setSkype(EFunctionStatus.UNSUPPORT);
            VPOperateManager.getMangerInstance(mContext).settingSocialMsg(writeResponse, new ISocialMsgDataListener() {
                @Override
                public void onSocialMsgSupportDataChange(FunctionSocailMsgData socailMsgData) {
                    String message = "Social Information Reminder-Settings:\n" + socailMsgData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, socailMsgData);
        } else if (oprater.equals(SOCIAL_MSG_SEND)) {
            /**Phone, can only pass phone number**/
            ContentSetting contentphoneSetting0 = new ContentPhoneSetting(ESocailMsg.PHONE, "0755-86562490");
            /**Phone number, contact name and phone number**/
            ContentSetting contentphoneSetting1 = new ContentPhoneSetting(ESocailMsg.PHONE, "Shenzhen Weiyibo Technology Co., Ltd.", "0755-86562490");

            /**SMS, you can just send the phone number**/
            ContentSetting contentsmsSetting2 = new ContentSmsSetting(ESocailMsg.SMS, "0755-86562490", "sms text");
            /**Text message, contact name and phone number**/
            ContentSetting contentsmsSetting3 = new ContentSmsSetting(ESocailMsg.SMS, "Shenzhen Weiyibo Technology Co., Ltd.", "0755-86562490", "text、智能家居、新型智能交友产品、飞机航模、智能安全锁五个领域方面");

            /**Pushed by a third-party app. Get the status of FunctionSocailMsgData through password verification before sending.**/
            ContentSetting contentsociaSetting4 = new ContentSocailSetting(ESocailMsg.WECHAT, "veepoo", "The company's research and development projects are mainly in five areas: medical and health smart wearables, smart homes, new smart dating products, aircraft models, and smart security locks.");
            VPOperateManager.getMangerInstance(mContext).sendSocialMsgContent(writeResponse, contentphoneSetting0);
        } else if (oprater.equals(SOCIAL_PHONE_IDLE_OR_OFFHOOK)) {
            VPOperateManager.getMangerInstance(mContext).offhookOrIdlePhone(writeResponse);
        } else if (oprater.equals(DEVICE_CONTROL_PHONE)) {
            VPOperateManager.getMangerInstance(mContext).settingDeviceControlPhone(new IDeviceControlPhoneModelState() {
                @Override
                public void inPttModel() {
                    String message = "Watch Tips: Watch Enters PTT Mode\n";
                    Logger.t(TAG).i(message);
                }

                @Override
                public void outPttModel() {
                    String message = "Watch tip: watch exits ptt mode\n";
                    Logger.t(TAG).i(message);
                }

                @Override
                public void rejectPhone() {
                    String message = "Watch Tip: Please hang up the call\n";
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }

                @Override
                public void cliencePhone() {
                    String message = "Watch Tip: Please mute the call\n";
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }

                @Override
                public void knocknotify(int type) {
                    String message = "Watch tips: tap reminder, 1 means click, 2 means double click\n";
                    Logger.t(TAG).i(message);
                }

                @Override
                public void sos() {
                    String message = "Watch tips:sos\n";
                    Logger.t(TAG).i(message);
                }

                public void nextMusic() {
                    String message = "Watch Tip: Next Song\n";
                    Logger.t(TAG).i(message);
                }

                public void previousMusic() {
                    String message = "Watch Tips: Previous\n";
                    Logger.t(TAG).i(message);
                }

                public void pauseAndPlayMusic() {
                    String message = "Watch Tips: Pause and Play\n";
                    Logger.t(TAG).i(message);
                }

            });
        } else if (oprater.equals(CLEAR_DEVICE_DATA)) {
            VPOperateManager.getMangerInstance(mContext).clearDeviceData(writeResponse);
            finish();
        } else if (oprater.equals(HEARTWRING_READ)) {
            VPOperateManager.getMangerInstance(mContext).readHeartWarning(writeResponse, new IHeartWaringDataListener() {
                @Override
                public void onHeartWaringDataChange(HeartWaringData heartWaringData) {
                    String message = "Heart rate alarm-read:\n" + heartWaringData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(HEARTWRING_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).settingHeartWarning(writeResponse, new IHeartWaringDataListener() {
                @Override
                public void onHeartWaringDataChange(HeartWaringData heartWaringData) {
                    String message = "Heart rate alarm-on:\n" + heartWaringData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new HeartWaringSetting(120, 110, true));
        } else if (oprater.equals(HEARTWRING_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).settingHeartWarning(writeResponse, new IHeartWaringDataListener() {
                @Override
                public void onHeartWaringDataChange(HeartWaringData heartWaringData) {
                    String message = "Heart rate alarm-off:\n" + heartWaringData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new HeartWaringSetting(120, 110, false));
        } else if (oprater.equals(SPO2H_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).startDetectSPO2H(writeResponse, new ISpo2hDataListener() {
                @Override
                public void onSpO2HADataChange(Spo2hData spo2HData) {
                    String message = "SpO2-Start:\n" + spo2HData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new ILightDataCallBack() {
                @Override
                public void onGreenLightDataChange(int[] data) {
                    String message = "Blood oxygen-optical signal:\n" + Arrays.toString(data);
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(SPO2H_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).stopDetectSPO2H(writeResponse, new ISpo2hDataListener() {
                @Override
                public void onSpO2HADataChange(Spo2hData spo2HData) {
                    String message = "SpO2-End:\n" + spo2HData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(SPO2H_AUTO_DETECT_READ))
            VPOperateManager.getMangerInstance(mContext).readSpo2hAutoDetect(writeResponse, new IAllSetDataListener() {
                @Override
                public void onAllSetDataChangeListener(AllSetData allSetData) {
                    String message = "SpO2 automatic detection-read\n" + allSetData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        else if (oprater.equals(SPO2H_AUTO_DETECT_OPEN)) {
            int setting = 0, open = 1;
            AllSetSetting mAlarmSetting = new AllSetSetting(EAllSetType.SPO2H_NIGHT_AUTO_DETECT, 22, 0, 8, 0, setting, open);
            VPOperateManager.getMangerInstance(mContext).settingSpo2hAutoDetect(writeResponse, new IAllSetDataListener() {
                @Override
                public void onAllSetDataChangeListener(AllSetData allSetData) {
                    String message = "Blood oxygen automatic detection-open\n" + allSetData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, mAlarmSetting);
        } else if (oprater.equals(SPO2H_AUTO_DETECT_CLOSE)) {
            int setting = 0, colse = 0;
            AllSetSetting mAlarmSetting = new AllSetSetting(EAllSetType.SPO2H_NIGHT_AUTO_DETECT, 22, 0, 8, 0, setting, colse);
            VPOperateManager.getMangerInstance(mContext).settingSpo2hAutoDetect(writeResponse, new IAllSetDataListener() {
                @Override
                public void onAllSetDataChangeListener(AllSetData allSetData) {
                    String message = "Blood oxygen automatic detection-open\n" + allSetData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, mAlarmSetting);
        } else if (oprater.equals(FATIGUE_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).startDetectFatigue(writeResponse, new IFatigueDataListener() {
                @Override
                public void onFatigueDataListener(FatigueData fatigueData) {
                    String message = "Fatigue-Start:\n" + fatigueData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(FATIGUE_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).stopDetectFatigue(writeResponse, new IFatigueDataListener() {
                @Override
                public void onFatigueDataListener(FatigueData fatigueData) {
                    String message = "Fatigue-end:\n" + fatigueData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(WOMEN_SETTING)) {
            VPOperateManager.getMangerInstance(mContext).settingWomenState(writeResponse, new IWomenDataListener() {
                @Override
                public void onWomenDataChange(WomenData womenData) {
                    String message = "Female status-set:\n" + womenData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new WomenSetting(EWomenStatus.PREING, new TimeData(2016, 3, 1), new TimeData(2017, 1, 14)));
        } else if (oprater.equals(WOMEN_READ)) {
            VPOperateManager.getMangerInstance(mContext).readWomenState(writeResponse, new IWomenDataListener() {
                @Override
                public void onWomenDataChange(WomenData womenData) {
                    String message = "Female status-reading:\n" + womenData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(COUNT_DOWN_WATCH)) {
            int second = 7;
            boolean isOpenWatchUI = false;
            boolean isCountDownByWatch = false;
            CountDownSetting countDownSetting = new CountDownSetting(second, isOpenWatchUI, isCountDownByWatch);
            VPOperateManager.getMangerInstance(mContext).settingCountDown(writeResponse, countDownSetting, new ICountDownListener() {
                @Override
                public void OnCountDownDataChange(CountDownData countDownData) {
                    String message = "Countdown-watch:\n" + countDownData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(COUNT_DOWN_APP_READ)) {
            VPOperateManager.getMangerInstance(mContext).readCountDown(writeResponse, new ICountDownListener() {
                @Override
                public void OnCountDownDataChange(CountDownData countDownData) {
                    String message = "Countdown-read:\n" + countDownData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(COUNT_DOWN_APP)) {
            int second = 11;
            boolean isOpenWatchUI = true;
            boolean isCountDownByWatch = false;
            CountDownSetting countDownSetting = new CountDownSetting(second, isOpenWatchUI, isCountDownByWatch);
            VPOperateManager.getMangerInstance(mContext).settingCountDown(writeResponse, countDownSetting, new ICountDownListener() {
                @Override
                public void OnCountDownDataChange(CountDownData countDownData) {
                    String message = "Countdown-App:\n" + countDownData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(AIM_SPROT_CALC)) {
            Intent intent = new Intent(OperaterActivity.this, AimSportCalcActivity.class);
            intent.putExtra("isnewsportcalc", isNewSportCalc);
            startActivity(intent);
        } else if (oprater.equals(SCREEN_LIGHT_SETTING)) {
            // The default setting is [22: 00-07: 00]. It is set to 2 levels, and other times is set to 4 levels. The user can customize it.
            VPOperateManager.getMangerInstance(mContext).settingScreenLight(writeResponse, new IScreenLightListener() {
                @Override
                public void onScreenLightDataChange(ScreenLightData screenLightData) {
                    String message = "Screen adjustment data-settings:" + screenLightData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new ScreenSetting(22, 0, 7, 0, 2, 4));
        } else if (oprater.equals(SCREEN_LIGHT_READ)) {
            VPOperateManager.getMangerInstance(mContext).readScreenLight(writeResponse, new IScreenLightListener() {
                @Override
                public void onScreenLightDataChange(ScreenLightData screenLightData) {
                    String message = "Screen Adjustment Data-Read:" + screenLightData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(SCREEN_STYLE_READ)) {
            VPOperateManager.getMangerInstance(mContext).readScreenStyle(writeResponse, new IScreenStyleListener() {
                @Override
                public void onScreenStyleDataChange(ScreenStyleData screenLightData) {
                    String message = "Screen style-read:" + screenLightData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(SCREEN_STYLE_SETTING)) {
            int screenstyle = 2;
            VPOperateManager.getMangerInstance(mContext).settingScreenStyle(writeResponse, new IScreenStyleListener() {
                @Override
                public void onScreenStyleDataChange(ScreenStyleData screenLightData) {
                    String message = "Screen style-settings:" + screenLightData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, screenstyle);
        } else if (oprater.equals(INSTITUTION_TRANSLATION)) {
            Intent intent = new Intent(OperaterActivity.this, InstitutionActivity.class);
            startActivity(intent);

        } else if (oprater.equals(READ_HEALTH_SLEEP)) {
            VPOperateManager.getMangerInstance(mContext).readSleepData(writeResponse, new ISleepDataListener() {
                        @Override
                        public void onSleepDataChange(SleepData sleepData) {
                            String message = "";
                            if (sleepData instanceof SleepPrecisionData && isSleepPrecision) {
                                SleepPrecisionData sleepPrecisionData = (SleepPrecisionData) sleepData;
                                message = "Precision sleep data-back:" + sleepPrecisionData.toString();
                            } else {
                                message = "Normal Sleep Data-Return:" + sleepData.toString();
                            }
                            Logger.t(TAG).i(message);
                            sendMsg(message, 1);
                        }

                        @Override
                        public void onSleepProgress(float progress) {

                            String message = "Sleep data-reading progress:" + "progress=" + progress;
                            Logger.t(TAG).i(message);
                        }

                        @Override
                        public void onSleepProgressDetail(String day, int packagenumber) {
                            String message = "Sleep data-reading progress:" + "day=" + day + ",packagenumber=" + packagenumber;
                            Logger.t(TAG).i(message);
                        }

                        @Override
                        public void onReadSleepComplete() {
                                String message = "Sleep data-end of reading";
                            Logger.t(TAG).i(message);
                        }
                    }, watchDataDay
            );
        } else if (oprater.equals(READ_HEALTH_SLEEP_FROM)) {
            int beforeYesterday = 2;
            VPOperateManager.getMangerInstance(mContext).readSleepDataFromDay(writeResponse, new ISleepDataListener() {
                        @Override
                        public void onSleepDataChange(SleepData sleepData) {
                            String message = "Sleep data-return:" + sleepData.toString();
                            Logger.t(TAG).i(message);
                            sendMsg(message, 1);
                        }

                        @Override
                        public void onSleepProgress(float progress) {
                                String message = "Sleep data-reading progress:" + "progress=" + progress;
                            Logger.t(TAG).i(message);
                        }

                        @Override
                        public void onSleepProgressDetail(String day, int packagenumber) {
                            String message = "Sleep data-reading progress:" + "day=" + day + ",packagenumber=" + packagenumber;
                            Logger.t(TAG).i(message);
                        }

                        @Override
                        public void onReadSleepComplete() {
                                String message = "Sleep data-end of reading";
                            Logger.t(TAG).i(message);
                        }
                    }
                    , beforeYesterday, watchDataDay);
        } else if (oprater.equals(READ_HEALTH_SLEEP_SINGLEDAY)) {
            int yesterday = 1;
            VPOperateManager.getMangerInstance(mContext).readSleepDataSingleDay(writeResponse, new ISleepDataListener() {
                @Override
                public void onSleepDataChange(SleepData sleepData) {
                    String message = "Sleep data-return:" + sleepData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }

                @Override
                public void onSleepProgress(float progress) {
                    String message = "sleep progress:" + "progress=" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onSleepProgressDetail(String day, int packagenumber) {
                    String message = "sleep progress detail:" + "day=" + day + ",packagenumber=" + packagenumber;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadSleepComplete() {
                    String message = "睡眠数据-读取结束";
                    Logger.t(TAG).i(message);
                }
            }, yesterday, watchDataDay);
        } else if (oprater.equals(READ_HEALTH_DRINK)) {
            VPOperateManager.getMangerInstance(mContext).readDrinkData(writeResponse, new IDrinkDataListener() {
                @Override
                public void onDrinkDataChange(int packagenumber, DrinkData drinkdata) {
                    String message = "drink data back:" + drinkdata.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }

                @Override
                public void onReadDrinkComplete() {
                    String message = "read drink completed";
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(READ_HEALTH_ORIGINAL)) {
            VPOperateManager.getMangerInstance(mContext).readOriginData(writeResponse, new IOriginData3Listener() {
                @Override
                public void onOriginFiveMinuteListDataChange(List<OriginData3> originDataList) {
                    String message = "Health data-returned:" + originDataList.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOriginHalfHourDataChange(OriginHalfHourData originHalfHourDataList) {
                    String message = "Health data [30 minutes]-back:" + originHalfHourDataList.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOriginHRVOriginListDataChange(List<HRVOriginData> originHrvDataList) {

                }

                @Override
                public void onOriginSpo2OriginListDataChange(List<Spo2hOriginData> originSpo2hDataList) {

                }


                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "Health data [5 minutes]-reading progress:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgressDetail(int date, String dates, int all, int num) {
                    String message = "Health data [5 minutes]-read progress detail:" + date + ",dates=" + dates;
                    Logger.t(TAG).i(message);
                }


                @Override
                public void onReadOriginComplete() {
                    String message = "Health data-end of reading";
                    Logger.t(TAG).i(message);
                }
            }, 3);
        } else if (oprater.equals(READ_HEALTH_ORIGINAL_FROM)) {
            int yesterday = 1;
            VPOperateManager.getMangerInstance(mContext).readOriginDataFromDay(writeResponse, new IOriginDataListener() {
                @Override
                public void onOringinFiveMinuteDataChange(OriginData originData) {
                    String message = "Health data [5 minutes]-back:" + originData.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOringinHalfHourDataChange(OriginHalfHourData originHalfHourData) {
                        String message = "Health Data [30 minutes]-Return:" + originHalfHourData.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "Health data [5 minutes]-reading progress:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgressDetail(int date, String dates, int all, int num) {

                }

                @Override
                public void onReadOriginComplete() {
                    String message = "Health data-end of reading";
                    Logger.t(TAG).i(message);
                }
            }, yesterday, 10, watchDataDay);
        } else if (oprater.equals(READ_HEALTH_ORIGINAL_SINGLEDAY)) {
            int today = 0;
            int originProtocolVersion = 3;
            IOriginProgressListener originDataListener = new IOriginDataListener() {
                @Override
                public void onOringinFiveMinuteDataChange(OriginData originData) {
                    String message = "Health data [5 minutes]-back:" + originData.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOringinHalfHourDataChange(OriginHalfHourData originHalfHourData) {
                    String message = "Health data [30 minutes]-back:" + originHalfHourData.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "Health data [5 minutes]-reading progress:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgressDetail(int date, String dates, int all, int num) {

                }

                @Override
                public void onReadOriginComplete() {
                    String message = "Health data-end of reading";
                    Logger.t(TAG).i(message);
                }
            };
            IOriginProgressListener originData3Listener = new IOriginData3Listener() {
                @Override
                public void onOriginFiveMinuteListDataChange(List<OriginData3> originData3List) {
                    String message = "Health data [5 minutes]-back:" + originData3List.size();
//                    for (int i = 0; i < originData3List.size(); i++) {
//                        String s = originData3List.get(i).toString();
//                        Logger.t(TAG).i(s);
//                    }
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOriginHalfHourDataChange(OriginHalfHourData originHalfHourData) {
                    String message = "Health data [30 minutes]-back:" + originHalfHourData.toString();
                    Logger.t(TAG).i(message);

                }

                @Override
                public void onOriginHRVOriginListDataChange(List<HRVOriginData> originHrvDataList) {
                    String message = "Health Data [HRV] -Return:" + originHrvDataList.size();
//                    for (int i = 0; i < originHrvDataList.size(); i++) {
//                        String s = originHrvDataList.get(i).toString();
//                        Logger.t(TAG).i(s);
//                    }
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOriginSpo2OriginListDataChange(List<Spo2hOriginData> originSpo2hDataList) {
                    String message = "Health Data [Spo2h] -Return:" + originSpo2hDataList.size();
                    Spo2hOriginUtil spo2hOriginUtil = new Spo2hOriginUtil(originSpo2hDataList);
                    List<Map<String, Float>> tenMinuteData = spo2hOriginUtil.getTenMinuteData(ESpo2hDataType.TYPE_SLEEP);
                    for (int i = 0; i < originSpo2hDataList.size(); i++) {
                        String s = originSpo2hDataList.get(i).toString();
                        Logger.t(TAG).i(s);
                    }
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgressDetail(int day, String date, int allPackage, int currentPackage) {

                }

                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "Health data [5 minutes]-reading progress:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginComplete() {
                    String message = "Health data-end of reading";
                    Logger.t(TAG).i(message);
                }
            };
            IOriginProgressListener originProgressListener;
            if (originProtocolVersion == 3) {
                originProgressListener = originData3Listener;
            } else {
                originProgressListener = originDataListener;
            }
            VPOperateManager.getMangerInstance(mContext).readOriginDataSingleDay(writeResponse, originProgressListener, today, 1, watchDataDay);
        } else if (oprater.equals(READ_HEALTH)) {
            VPOperateManager.getMangerInstance(mContext).readAllHealthData(new IAllHealthDataListener() {
                @Override
                public void onProgress(float progress) {
                    String message = "onAllProgress:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOringinFiveMinuteDataChange(OriginData originData) {
                    String message = "onOringinFiveMinuteDataChange:" + originData;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOringinHalfHourDataChange(OriginHalfHourData originHalfHourData) {
                    String message = "onOringinHalfHourDataChange:" + originHalfHourData;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginComplete() {
                    String message = "onReadOriginComplete";
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onSleepDataChange(SleepData sleepData) {
                    String message = "onSleepDataChange:" + sleepData;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadSleepComplete() {
                    String message = "onReadSleepComplete";
                    Logger.t(TAG).i(message);

                }
            }, watchDataDay);
        } else if (oprater.equals(OAD)) {
            if (deviceNumber < 0) {
                Toast.makeText(mContext, "Please pass the password verification first to get the version number!", Toast.LENGTH_LONG).show();
                return;
            }
            boolean isOadModel = getIntent().getBooleanExtra("isoadmodel", false);
            deviceaddress = getIntent().getStringExtra("deviceaddress");

            Intent intent = new Intent(OperaterActivity.this, OadActivity.class);
            intent.putExtra("deviceaddress", deviceaddress);
            intent.putExtra("isoadmodel", isOadModel);
            intent.putExtra("devicenumber", deviceNumber);
            intent.putExtra("deviceversion", deviceVersion);
            intent.putExtra("devicetestversion", deviceTestVersion);
            startActivity(intent);

        } else if (oprater.equals(SHOW_SP)) {
            String shareperence = VPOperateManager.getMangerInstance(mContext).traversalShareperence();
            Logger.t(TAG).i(shareperence);
        } else if (oprater.equals(SPORT_MODE_ORIGIN_END)) {
            VPOperateManager.getMangerInstance(mContext).stopSportModel(writeResponse, new ISportModelStateListener() {
                @Override
                public void onSportModelStateChange(SportModelStateData sportModelStateData) {
                    String message = "Sport mode status:" + sportModelStateData.toString();
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(SPORT_MODE_ORIGIN_READSTAUTS)) {
            VPOperateManager.getMangerInstance(mContext).readSportModelState(writeResponse, new ISportModelStateListener() {
                @Override
                public void onSportModelStateChange(SportModelStateData sportModelStateData) {
                    String message = "Sport mode status" + sportModelStateData.toString();
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(SPORT_MODE_ORIGIN_START)) {
            VPOperateManager.getMangerInstance(mContext).startSportModel(writeResponse, new ISportModelStateListener() {
                @Override
                public void onSportModelStateChange(SportModelStateData sportModelStateData) {
                    String message = "Sport mode status" + sportModelStateData.toString();
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(SPORT_MODE_ORIGIN_READ)) {
            VPOperateManager.getMangerInstance(mContext).readSportModelOrigin(writeResponse, new ISportModelOriginListener() {
                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "Sports mode data [read progress]:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgressDetail(int day, String date, int allPackage, int currentPackage) {
                    String message = "Sports mode data [read details]:" + day +
                            ",allPackage=" + allPackage + ",currentPackage=" + currentPackage;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onHeadChangeListListener(SportModelOriginHeadData sportModelHeadData) {
                    String message = "Motion pattern data [head]:" + sportModelHeadData.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onItemChangeListListener(List<SportModelOriginItemData> sportModelItemData) {
                    StringBuffer message = new StringBuffer();
                    message.append("Sports mode data [detailed]:");
                    for (SportModelOriginItemData sportModelOriginItemData : sportModelItemData) {
                        message.append("\n");
                        message.append(sportModelOriginItemData.toString());
                    }
                    Logger.t(TAG).i(message.toString());

                }

                @Override
                public void onReadOriginComplete() {
                    String message = "Sports mode data [read end]";
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(HRV_ORIGIN_READ)) {
            VPOperateManager.getMangerInstance(mContext).readHRVOrigin(writeResponse, new IHRVOriginDataListener() {
                @Override
                public void onReadOriginProgress(float progress) {
                    Logger.t(TAG).i("onReadOriginProgress=" + progress);
                }

                @Override
                public void onReadOriginProgressDetail(int day, String date, int allPackage, int currentPackage) {
                    Logger.t(TAG).i("onReadOriginProgressDetail,day=" + day + ",date=" + date + ",allPackage=" + allPackage + ",currentPackage=" + currentPackage);
                }

                @Override
                public void onHRVOriginListener(HRVOriginData hrvOriginData) {
                    Logger.t(TAG).i("onHRVOriginListener=" + hrvOriginData.toString());
                }

                @Override
                public void onDayHrvScore(int day, String date, int hrvSocre) {

                }

                @Override
                public void onReadOriginComplete() {
                    Logger.t(TAG).i("onReadOriginComplete");

                }
            }, watchDataDay);
        } else if (oprater.equals(S22_READ_DATA)) {
            TimeData timeData = new TimeData(2017, 9, 11, 8, 13, 20);
//            timeData.setCurrentTime();
            Logger.t(TAG).i("timeData:" + timeData.toString());
            VPOperateManager.getMangerInstance(mContext).readAutoDetectOriginDataFromS22(writeResponse, new IAutoDetectOriginDataListener() {

                @Override
                public void onAutoDetectOriginDataChangeListener(List<AutoDetectOriginData> autoDetectOriginDataList) {
                    for (AutoDetectOriginData autoDetectOriginData : autoDetectOriginDataList) {
                        Logger.t(TAG).i("autoDetectOriginData:" + autoDetectOriginData.toString());
                    }
                }
            }, timeData);
        } else if (oprater.equals(S22_READ_STATE)) {
            VPOperateManager.getMangerInstance(mContext).readAutoDetectStateFromS22(writeResponse, new ICustomProtocolStateListener() {

                @Override
                public void onS22AutoDetectStateChangeListener(AutoDetectStateData autoDetectStateData) {
                    Logger.t(TAG).i("autoDetectStateData:" + autoDetectStateData.toString());
                }
            });
        } else if (oprater.equals(S22_SETTING_STATE_OPEN)) {
            AutoDetectStateSetting autoDetectStateSetting = new AutoDetectStateSetting();
            autoDetectStateSetting.setSpo2h24Hour(SUPPORT_OPEN);
            VPOperateManager.getMangerInstance(mContext).setAutoDetectStateToS22(writeResponse, new ICustomProtocolStateListener() {
                @Override
                public void onS22AutoDetectStateChangeListener(AutoDetectStateData autoDetectStateData) {
                    Logger.t(TAG).i("autoDetectStateData:" + autoDetectStateData.toString());
                }
            }, autoDetectStateSetting);
        } else if (oprater.equals(S22_SETTING_STATE_CLOSE)) {
            AutoDetectStateSetting autoDetectStateSetting = new AutoDetectStateSetting();
            autoDetectStateSetting.setSpo2h24Hour(SUPPORT_CLOSE);
            VPOperateManager.getMangerInstance(mContext).setAutoDetectStateToS22(writeResponse, new ICustomProtocolStateListener() {
                @Override
                public void onS22AutoDetectStateChangeListener(AutoDetectStateData autoDetectStateData) {
                    Logger.t(TAG).i("autoDetectStateData:" + autoDetectStateData.toString());
                }
            }, autoDetectStateSetting);
        } else if (oprater.equals(SPO2H_ORIGIN_READ)) {
            VPOperateManager.getMangerInstance(mContext).readSpo2hOrigin(writeResponse, new ISpo2hOriginDataListener() {
                @Override
                public void onReadOriginProgress(float progress) {
                    Logger.t(TAG).i("onReadOriginProgress:" + progress);
                }

                @Override
                public void onReadOriginProgressDetail(int day, String date, int allPackage, int currentPackage) {
                    Logger.t(TAG).i("onReadOriginProgressDetail:allPackage=" + allPackage + ",currentPackage=" + currentPackage);
                }

                @Override
                public void onSpo2hOriginListener(Spo2hOriginData sportOriginData) {
                    Logger.t(TAG).i("Spo2hOriginData:" + sportOriginData.toString());
                }

                @Override
                public void onReadOriginComplete() {
                    Logger.t(TAG).i("onReadOriginComplete");
                }
            }, watchDataDay);
        }

    }

    @NonNull
    private Alarm2Setting getMultiAlarmSetting() {
        int hour = 16;
        int minute = 33;
        int scene = 1;
        boolean isOpen = true;
        String repestStr = "1000010";
        String unRepeatDdate = "0000-00-00";
        return new Alarm2Setting(hour, minute, repestStr, scene, unRepeatDdate, isOpen);
    }

    private void sendMsg(String message, int what) {
        msg = Message.obtain();
        msg.what = what;
        msg.obj = message;
        mHandler.sendMessage(msg);
    }

    /**
     * Write status returned
     */
    class WriteResponse implements IBleWriteResponse {

        @Override
        public void onResponse(int code) {
            Logger.t(TAG).i("write cmd status:" + code);

        }
    }

    /**
     *
     * 	Call this method before password verification
     *       * Because after password verification, one of inPttModel / outPttModel will have a callback
     */
    public void listenDeviceCallbackData() {
        VPOperateManager.getMangerInstance(mContext).settingDeviceControlPhone(new IDeviceControlPhoneModelState() {
            @Override
            public void knocknotify(int type) {

            }

            @Override
            public void nextMusic() {

            }

            @Override
            public void previousMusic() {

            }

            @Override
            public void pauseAndPlayMusic() {

            }

            @Override
            public void rejectPhone() {

            }

            @Override
            public void cliencePhone() {

            }

            @Override
            public void inPttModel() {
                Logger.t(TAG).i("inPttModel");
                isInPttModel = true;
            }

            @Override
            public void outPttModel() {
                Logger.t(TAG).i("outPttModel");
                isInPttModel = false;
            }

            @Override
            public void sos() {

            }
        });
    }
}
