package kr.co.reo.pendingintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import kr.co.reo.pendingintent.databinding.ActivityNotification1Binding;

public class Notification1Activity extends AppCompatActivity {
    ActivityNotification1Binding activityNotification1Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNotification1Binding = ActivityNotification1Binding.inflate(getLayoutInflater());
        setContentView(activityNotification1Binding.getRoot());

        //Activity 실행을 위해 사용되어진 Intent를 추출한다.
        Intent intent = getIntent();

        //데이터를 추출한다.
        int data1 = intent.getIntExtra("data1",0);
        int data2 = intent.getIntExtra("data2",0);

        activityNotification1Binding.textView.setText("data1 : "+data1 +"\n");
        activityNotification1Binding.textView.append("data2 : "+data2 +"\n");
    }
}