package com.xiao.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.lang.ref.WeakReference;

public class MessengerService extends Service {
    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    static class ServiceHandler extends Handler {
        private WeakReference<MessengerService> mService;
        public ServiceHandler(MessengerService service) {
            mService = new WeakReference<>(service);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Messenger messenger = msg.replyTo;
                    int i = (int) msg.obj;
                    Message message = new Message();
                    message.what = 2;
                    message.obj = String.valueOf(++i);
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    Messenger messenger = new Messenger(new ServiceHandler(this));
}
