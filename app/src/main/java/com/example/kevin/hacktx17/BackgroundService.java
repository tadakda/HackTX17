package com.example.kevin.hacktx17;

/**
 * Created by SriramHariharan on 10/28/17.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class BackgroundService extends Service {

    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                //      Toast.makeText(getApplicationContext(), "ran!", Toast.LENGTH_SHORT).show();
                try {
                    //            ClssPkg p = ClssPkg.getFromServer(muser, mPassword);

                    String message = ""; //whether to buy or sell, and at what intervals.
                    if(true){
                        message = "buy";
                    }
                    else if(true){
                        message = "sell";
                    }
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("crypto.ai")
                                    .setContentText(message);
                    Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                    stackBuilder.addParentStack(MainActivity.class);
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), mBuilder.build());


                } catch (Exception e) {
                    Log.e("THE ERROR", e.toString());
                }
                handler.postDelayed(runnable, 10000);
                //3600000 is 1 hour

            }
        };

        handler.postDelayed(runnable, 15000);
    }

    @Override
    public void onDestroy() {
      //  handler.removeCallbacks(runnable); //to kill service with app
        // Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        //     Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
    }
}