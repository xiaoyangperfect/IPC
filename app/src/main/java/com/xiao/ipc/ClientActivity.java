package com.xiao.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xiao.ipc.entry.Book;

import java.lang.ref.WeakReference;

public class ClientActivity extends AppCompatActivity {

    private IAIDLBookInterface aidl;
    private Messenger serviceMessager;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setPrice(1);
                book.setBookName("a");
                book.setAuthor("aa");
                try {
                    aidl.addBook(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    Log.d("book nbum: ", String.valueOf(aidl.getBooks().size()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setPackage("com.xiao.service");
                intent.setAction("com.xiao.service.messagerservice");
                bindService(intent, conn2, BIND_AUTO_CREATE);
                Book book = new Book();
                book.setAuthor("xiaoyang");
                book.setBookName("C++");
                book.setPrice(index);
                Bundle bundle = new Bundle();
                bundle.putParcelable("book", book);
                Message msg = new Message();
                msg.what = 1;
                msg.setData(bundle);
                msg.replyTo = serviceMessager;
                try {
                    messenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setPackage("com.xiao.service");
                intent.setAction("com.xiao.service.intent");
                Bundle bundle = new Bundle();
                Book book = new Book();
                book.setAuthor("xiaoyang");
                book.setBookName("C++");
                bundle.putParcelable("book", book);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        Intent intent = new Intent();
        intent.setPackage("com.xiao.service");
        intent.setAction("com.xiao.service.bind");
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidl = IAIDLBookInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    ServiceConnection conn2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            serviceMessager = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    static class MyHandler extends Handler {
        private WeakReference<ClientActivity> mActivity;
        public MyHandler(ClientActivity activity) {
            mActivity = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    mActivity.get().index = (int) msg.obj;
                    Log.d("client", String.valueOf(mActivity.get().index));
                    break;
            }
        }
    }

    Messenger messenger = new Messenger(new MyHandler(this));
}
