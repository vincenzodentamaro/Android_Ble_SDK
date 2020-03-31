
package com.timaimee.vpdemo.oad.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.timaimee.vpdemo.R;
import com.timaimee.vpdemo.oad.service.DfuService;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.oad.OnFindOadDeviceListener;
import com.veepoo.protocol.model.settings.OadSetting;
import com.veepoo.protocol.err.OadErrorState;
import com.veepoo.protocol.listener.oad.OnUpdateCheckListener;

import vpno.nordicsemi.android.dfu.DfuLogListener;
import vpno.nordicsemi.android.dfu.DfuProgressListener;
import vpno.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import vpno.nordicsemi.android.dfu.DfuServiceInitiator;
import vpno.nordicsemi.android.dfu.DfuServiceListenerHelper;


/**
 * Created by timaimee on 2016/8/17.
 */
public class OadActivity extends Activity implements View.OnClickListener {
    private Context mContext = null;
    private final static String TAG = OadActivity.class.getSimpleName();

    OadSetting oadSetting;
    TextView deviceStateTv, upLoadingTv, textPercentTv;
    Button oadButton;
    ProgressBar progressBar;

    private String mOadAddress, mOadFileName;
    private int failCount = 0;
    private boolean isCanEnterOadModel = false, isFindOadDevice = false;
    private final static int MAX_ALLOW_FAIL_COUNT = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oad_custom);
        mContext = getApplicationContext();
        oadSetting = getIntData();
        initView();
        registerListener();
    }

    private void registerListener() {
        DfuServiceListenerHelper.registerProgressListener(this, mDfuProgressListener);
        DfuServiceListenerHelper.registerLogListener(this, mDfuLogListener);
    }

    private OadSetting getIntData() {
        boolean isOadModel = getIntent().getBooleanExtra("isoadmodel", false);
        int deviceNumber = getIntent().getIntExtra("devicenumber", 0);
        String deviceAddress = getIntent().getStringExtra("deviceaddress");
        String deviceVersion = getIntent().getStringExtra("deviceversion");
        String deviceTestVersion = getIntent().getStringExtra("devicetestversion");
        OadSetting oadSetting = new OadSetting(deviceAddress, deviceVersion, deviceTestVersion, deviceNumber, isOadModel);
        return oadSetting;
    }

    private void initView() {
        deviceStateTv = (TextView) findViewById(R.id.oad_devicestate);
        upLoadingTv = (TextView) findViewById(R.id.oad_uploading);
        textPercentTv = (TextView) findViewById(R.id.oad_upload_progress_gv);
        oadButton = (Button) findViewById(R.id.oad_update);
        progressBar = (ProgressBar) findViewById(R.id.oad_upload_progress);
        oadButton.setOnClickListener(this);

        initDeviceStateView();
    }

    private void initDeviceStateView() {
        StringBuffer sb = new StringBuffer();
        sb.append ("device address:" + oadSetting.getDeviceAddress ());
         sb.append ("\n");
 sb.append ("device number:" + oadSetting.getDeviceNumber ());
 sb.append ("\n");
 sb.append ("Official version number:" + oadSetting.getDeviceVersion ());
 sb.append ("\n");
 sb.append ("Test version number:" + oadSetting.getDeviceTestVersion ());
 sb.append ("\n");
 sb.append ("Whether DFU mode:" + oadSetting.isOadModel ());
        deviceStateTv.setText(sb.toString());

    }

    @Override
    public void onClick(View v) {
        if (isCanEnterOadModel) {
            if (isFindOadDevice) {
                startOad();
            } else {
                findOadModelDevice();
            }
        } else {
            checkVersionAndFile();
        }
    }


    private void checkVersionAndFile() {
        oadSetting.setDeviceVersion("00.23.00");
        oadSetting.setDeviceTestVersion("00.23.00.00");
        Logger.t(TAG).i("Before upgrading: version verification-> file verification-> find target device");
        VPOperateManager.getMangerInstance(mContext).checkVersionAndFile(oadSetting, new OnUpdateCheckListener() {
            @Override
            public void onNetVersionInfo(int deviceNumber, String deviceVersion, String des) {
                Logger.t(TAG).i("Server version information, device number=" + deviceNumber + ",The latest version of=" + deviceVersion + ",Upgrade description=" + des);
            }

            @Override
            public void onDownLoadOadFile(float progress) {
                Logger.t(TAG).i("Download file from server, progress=" + progress);
            }

            @Override
            public void onCheckFail(int endState) {
                switch (endState) {
                    case OadErrorState.UNCONNECT_NETWORK:
                        Logger.t (TAG) .i ("Network error");
                         break;
                         case OadErrorState.UNCONNECT_SERVER:
                         Logger.t (TAG) .i ("The server cannot connect");
                         break;
                         case OadErrorState.SERVER_NOT_HAVE_NEW:
                         Logger.t (TAG) .i ("The server does not have this version");
                         break;
                         case OadErrorState.DEVICE_IS_NEW:
                         Logger.t (TAG) .i ("The device is the latest version");
                         break;
                         case OadErrorState.OAD_FILE_UNEXITS:
                         Logger.t (TAG) .i ("File does not exist");
                         break;
                         case OadErrorState.OAD_FILE_MD5_UNSAME:
                         Logger.t (TAG) .i ("File md5 is inconsistent");
                         break;
                }
            }

            @Override
            public void onCheckSuccess(String oadFileName) {
                Logger.t(TAG).i("Version confirmation is correct, file confirmation is correct");
                mOadFileName = oadFileName;
                if (!TextUtils.isEmpty(mOadFileName)) {
                    isCanEnterOadModel = true;
                }
            }

            @Override
            public void findOadDevice(String oadAddress) {
                Logger.t(TAG).i("Found the device in OAD mode");
                mOadAddress = oadAddress;
                if (!TextUtils.isEmpty(mOadAddress)) {
                    isFindOadDevice = true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startOad();
                        }
                    });
                }
            }
        });
    }

    private void findOadModelDevice() {
        VPOperateManager.getMangerInstance(mContext).findOadModelDevice(oadSetting, new OnFindOadDeviceListener() {
            @Override
            public void findOadDevice(String oadAddress) {
                startOad();
            }
        });
    }


    private void startOad() {
        Logger.t(TAG).i("Perform upgrade procedure, try up to 5 times");
        showProgressBar();
        Boolean isBinder = false;
        final DfuServiceInitiator dfuServiceInitiator = new DfuServiceInitiator(mOadAddress)
                .setDeviceName("veepoo")
                .setKeepBond(isBinder);
        dfuServiceInitiator.setZip(null, mOadFileName);
        dfuServiceInitiator.start(this, DfuService.class);

    }

    private final DfuProgressListener mDfuProgressListener = new DfuProgressListenerAdapter() {
        @Override
        public void onDeviceConnecting(final String deviceAddress) {
            //   progressBar.setIndeterminate(true);
            //   textPercentTv.setText(R.string.dfu_status_connecting);
            progressBar.setIndeterminate(true);
            textPercentTv.setText(getResources().getString(R.string.dfu_status_connecting));
            Logger.t(TAG).e("onDeviceConnecting");
        }

        @Override
        public void onDfuProcessStarting(final String deviceAddress) {
            //    progressBar.setIndeterminate(true);
            //    textPercentTv.setText(R.string.dfu_status_starting);
            progressBar.setIndeterminate(true);
            textPercentTv.setText(getResources().getString(R.string.dfu_status_starting));
            Logger.t(TAG).e("onDfuProcessStarting");
        }

        @Override
        public void onDfuProcessStarted(String deviceAddress) {
            Logger.t(TAG).e("onDfuProcessStarted");
            super.onDfuProcessStarted(deviceAddress);
        }

        @Override
        public void onProgressChanged(String deviceAddress, int percent, float speed, float avgSpeed, int currentPart, int partsTotal) {
            Logger.t(TAG).e("onProgressChanged-" + percent);
            super.onProgressChanged(deviceAddress, percent, speed, avgSpeed, currentPart, partsTotal);
            progressBar.setIndeterminate(false);
            progressBar.setProgress(percent);
            textPercentTv.setText(percent + "%");
            progressBar.setIndeterminate(false);
        }

        @Override
        public void onDeviceConnected(String deviceAddress) {
            super.onDeviceConnected(deviceAddress);

        }


        @Override
        public void onEnablingDfuMode(String deviceAddress) {
            super.onEnablingDfuMode(deviceAddress);

        }

        @Override
        public void onFirmwareValidating(String deviceAddress) {
            progressBar.setIndeterminate(true);
            textPercentTv.setText(getResources().getString(R.string.dfu_status_validating));
            super.onFirmwareValidating(deviceAddress);
            Logger.t(TAG).e("onFirmwareValidating");
        }

        @Override
        public void onDeviceDisconnecting(String deviceAddress) {
            progressBar.setIndeterminate(true);
            textPercentTv.setText(getResources().getString(R.string.dfu_status_disconnecting));
            super.onDeviceDisconnecting(deviceAddress);
            Logger.t(TAG).e("onDeviceDisconnecting");
        }

        @Override
        public void onDeviceDisconnected(String deviceAddress) {
            super.onDeviceDisconnected(deviceAddress);
            Logger.t(TAG).e("onDeviceDisconnected");
        }

        @Override
        public void onDfuCompleted(String deviceAddress) {
            super.onDfuCompleted(deviceAddress);
            Logger.t(TAG).e("onDfuCompleted");
            textPercentTv.setText(getResources().getString(R.string.dfu_status_completed));

            // let's wait a bit until we cancel the notification. When canceled
            // immediately it will be recreated by service again.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    oadSuccess();

                    // if this activity is still open and upload process was
                    // completed, cancel the notification
                    final NotificationManager manager = (NotificationManager) getSystemService(
                            Context.NOTIFICATION_SERVICE);
                    manager.cancel(DfuService.NOTIFICATION_ID);
                }
            }, 200);

        }

        @Override
        public void onDfuAborted(String deviceAddress) {
            Logger.t(TAG).e("onDfuAborted");
            super.onDfuAborted(deviceAddress);
        }

        @Override
        public void onError(String deviceAddress, int error, int errorType, String message) {
            Logger.t(TAG).e("onError=" + error + ",errorType=" + errorType + ",message=" + message);
            super.onError(deviceAddress, error, errorType, message);
            oadFail();

        }
    };

    private final DfuLogListener mDfuLogListener = new DfuLogListener() {
        @Override
        public void onLogEvent(String deviceAddress, int level, String message) {
            Logger.t(TAG).e("deviceAddress=" + deviceAddress + ",message=" + message);
        }
    };

    protected void onDestroy() {
        super.onDestroy();
        DfuServiceListenerHelper.unregisterProgressListener(this, mDfuProgressListener);
        DfuServiceListenerHelper.unregisterLogListener(this, mDfuLogListener);
        this.finish();
    }


    private void showProgressBar() {
        oadButton.setEnabled(false);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        textPercentTv.setVisibility(View.VISIBLE);
        upLoadingTv.setVisibility(View.GONE);
    }

    private void disMissProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
        textPercentTv.setVisibility(View.INVISIBLE);
        upLoadingTv.setVisibility(View.GONE);
        oadButton.setEnabled(true);
    }

    private void oadSuccess() {
        isFindOadDevice = false;
        disMissProgressBar();
        Toast.makeText(mContext, "update successed", Toast.LENGTH_SHORT).show();
        Logger.t(TAG).e("update successed");
        this.finish();
    }

    private void oadFail() {
        failCount++;
        disMissProgressBar();
        Logger.t(TAG).e("showErrorMessage");
        try {
            Thread.currentThread();
            Thread.sleep(300);
        } catch (Exception e) {
        }

        if (failCount < MAX_ALLOW_FAIL_COUNT) {
            Logger.t(TAG).e("再试一次=" + failCount);
            showProgressBar();
            startOad();
        } else {
            showOadFailDialog();
        }
    }


    private void showOadFailDialog() {
        isFindOadDevice = false;
        String mStringContent = "The upgrade fails and the device name becomes DfuLang";
        String mStringTitle = "prompt";
        String mStringOk = "OK";
        AlertDialog oadFailDialog = new AlertDialog.Builder(mContext).setTitle(mStringTitle)
                .setIconAttribute(android.R.attr.alertDialogIcon).setCancelable(false)
                .setMessage(mStringContent)
                .setPositiveButton(mStringOk, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OadActivity.this.finish();
                    }
                }).create();
        oadFailDialog.setCanceledOnTouchOutside(false);
        oadFailDialog.show();
    }
}
