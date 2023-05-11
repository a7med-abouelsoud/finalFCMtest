package com.ahmed.finalfcmtest;

import com.google.firebase.messaging.FirebaseMessagingService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomMessagingService extends FirebaseMessagingService {
    String CHANNEL_ID= "MyChannel";
    NotificationManager notificationManager;


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (remoteMessage != null) {

           //message without data payload
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();
            notifyUser(title, message);




        }



    }
    public void notifyUser(String title, String messageBody) {
        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.O)
        {
            String name="Channel";
            String discription="ChannelDiscription";
            int importance=NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel notificationChannel= new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(discription);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }
        NotificationCompat.Builder builder= new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setContentText(messageBody);
        builder.setContentTitle(title);
        builder.setSmallIcon(R.drawable.notification);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification));

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());


    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }
}
