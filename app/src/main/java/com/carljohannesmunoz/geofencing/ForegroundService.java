package com.carljohannesmunoz.geofencing;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.carljohannesmunoz.geofencing.R;

public class ForegroundService extends Service {

    private static final int NOTIFICATION_ID = 123; // Unique ID for the notification

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Handle your geofencing logic here

        // Create a notification to keep the service running in the foreground
        Notification notification = createNotification();
        startForeground(NOTIFICATION_ID, notification);

        // Return START_STICKY to ensure the service is restarted if it's killed
        return START_STICKY;
    }

    private Notification createNotification() {
        // Create a notification to indicate that the service is running
        Intent notificationIntent = new Intent(this, MapsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE); // Set the mutability flag

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle("Geofencing Service")
                .setContentText("Running in the background")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);

        return builder.build();
    }

}
