package com.keymessage.mark.mytest;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.tv_appname);
        boolean isRunning = isOpen("com.keymessage.mark.mytest");
        textView.setText("" + isRunning);
    }


    /**
     * 判断程序是否在前台运行
     *
     * @param packageName
     * @return
     */
    public boolean isOpen(String packageName) {
        if (packageName.equals("") | packageName == null)
            return false;
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runinfo : runningAppProcesses) {
            String pn = runinfo.processName;
            if (pn.equals(packageName) && runinfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
                return true;
        }
        return false;
    }
}
