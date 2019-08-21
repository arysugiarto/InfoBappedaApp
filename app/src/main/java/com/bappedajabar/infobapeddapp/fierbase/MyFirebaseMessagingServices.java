package com.bappedajabar.infobapeddapp.fierbase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.bappedajabar.infobapeddapp.R;
import com.bappedajabar.infobapeddapp.activity.DetailActivity;
import com.bappedajabar.infobapeddapp.activity.MainActivity;
import com.bappedajabar.infobapeddapp.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingServices extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    SessionManager sessionManager;
    Context context;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Log.d(TAG, "REMOTEDATA: " + remoteMessage.getData().size());

        if(remoteMessage.getData().size()>0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData().size());

            String activity = remoteMessage.getData().get("click_action");
            String body = remoteMessage.getData().get("body");
            sendNotification(body,activity , remoteMessage);
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }
    }

    private void sendNotification(String body , String activity , RemoteMessage remoteMessage ) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent();
        Intent intentNew = sendMessage(activity,intent,remoteMessage);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentNew,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Info Bappeda")
                .setContentText("Notifikasi")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(body, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[] {100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationBuilder.setChannelId(body);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        notificationManager.notify(1, notificationBuilder.build());
    }


    private Intent sendMessage(String activity, Intent intent , RemoteMessage remoteMessage){
        if(activity.equals("KEGIATANACTIVITY")){
            intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id_kegiatan",remoteMessage.getData().get("id_kegiatan"));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        return intent;
    }
}
