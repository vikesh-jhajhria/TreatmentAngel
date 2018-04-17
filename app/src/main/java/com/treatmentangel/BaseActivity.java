package com.treatmentangel;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.treatmentangel.utils.AppPreferences;
import com.treatmentangel.utils.Config;

import java.util.List;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isExit;
    private ProgressDialog dialog;
    public String TAG = "BUSINESS_MANAGER";
    private Context mContext;
    AppPreferences preferences;
    protected String CURRENT_PAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        preferences = AppPreferences.getAppPreferences(this);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


    }


    public void showProgessDialog() {
        if (dialog != null && !dialog.isShowing() && !this.isFinishing())
            try {
                dialog.show();
                dialog.setContentView(R.layout.progress_dialogue);
            } catch (WindowManager.BadTokenException e) {

            }
    }

    public void showProgessDialog(String message) {
        if (dialog != null && !dialog.isShowing() && !this.isFinishing()) {
            dialog.setMessage(message);
            try {
                dialog.show();
                dialog.setContentView(R.layout.progress_dialogue);
            } catch (WindowManager.BadTokenException e) {

            }
        }
    }

    public void writeToLog(String msg){
        Log.v(TAG,msg);
    }
    public void dismissProgressDialog() {
        if (dialog != null && dialog.isShowing() && !this.isFinishing())
            try {
                dialog.dismiss();
            } catch (WindowManager.BadTokenException e) {

            }
    }


    @Override
    public void onBackPressed() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = activityManager.getRunningTasks(10);
        if (taskList.get(0).numActivities == 1 && taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {
            if (isExit) {
                finish();
            } else {
                Toast.makeText(this, "Press back again to Exit !", Toast.LENGTH_SHORT).show();
                isExit = true;
            }
        } else {
            super.onBackPressed();
            overridePendingTransition(0, 0);
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                isExit = false;
            }
        }, 2000);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }


    public void hideKeyboard() {
        View v = getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public boolean hasPermission(int permissionType) {
        if (Build.VERSION.SDK_INT >= 23) {
            switch (permissionType) {
                case Config.LOCATION_PERMISSION:
                    int hasLocationPermission = ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION);
                    if (hasLocationPermission != PackageManager.PERMISSION_GRANTED) {

                        return false;
                    } else {
                        return true;
                    }
                case Config.MEDIA_PERMISSION:
                    int hasCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                    int hasStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (hasCameraPermission != PackageManager.PERMISSION_GRANTED || hasStoragePermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(BaseActivity.this,
                                new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                Config.MEDIA_PERMISSION);
                        return false;
                    } else {
                        return true;
                    }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Config.LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == 0) {

                } else {

                }
                break;
            case Config.MEDIA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == 0) {

                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Config.REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:

                        break;
                    case Activity.RESULT_CANCELED:

                        break;
                }
                break;
        }
    }



    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
