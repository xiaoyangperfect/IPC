package com.xiao.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xiao.ipc.entry.Book;

public class ServiceUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_ui);

        TextView tv = findViewById(R.id.textView2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Book book = bundle.getParcelable("book");

            tv.setText(book.getBookName());
        }

    }
}
