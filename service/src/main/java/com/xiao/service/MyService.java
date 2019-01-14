package com.xiao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    class MyBind extends IMyAidlInterface.Stub {

        @Override
        public String getValue() throws RemoteException {
            return "This is data which come from aidl!";
        }
    }
}
