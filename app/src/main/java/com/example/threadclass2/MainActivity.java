package com.example.threadclass2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.android.material.progressindicator.CircularProgressIndicator;

public class MainActivity extends AppCompatActivity {

    private CircularProgressIndicator progressBar;

    private Handler mainThreadHandler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    int progress = (int) msg.obj;
                    progressBar.setProgress(progress);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        WorkerThread workerThread=new WorkerThread("async",mainThreadHandler);
        workerThread.start();
    }

    private void initViews() {
        progressBar=findViewById(R.id.progressBar);
    }
}