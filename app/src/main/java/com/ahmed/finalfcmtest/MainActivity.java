package com.ahmed.finalfcmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    String CHANNEL_ID= "MyChannel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.notify);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT> Build.VERSION_CODES.O)
                {
                    String name="Channel";
                    String discription="ChannelDiscription";
                    int importance= NotificationManager.IMPORTANCE_HIGH;

                    NotificationChannel notificationChannel= new NotificationChannel(CHANNEL_ID, name, importance);
                    notificationChannel.setDescription(discription);

                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(notificationChannel);

                }
                NotificationCompat.Builder builder= new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
                builder.setContentText("messageBody");
                builder.setContentTitle("title");
                builder.setSmallIcon(R.drawable.notification);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification));

                NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, builder.build());
            }
        });

    }
}