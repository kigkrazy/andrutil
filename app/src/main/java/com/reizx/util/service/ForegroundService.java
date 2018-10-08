package com.reizx.util.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.reizx.util.R;
import com.reizx.util.constant.Constants;
import com.reizx.util.util.AsfLog;

import static android.app.NotificationManager.IMPORTANCE_LOW;

/**
 * Created by kigkrazy on 18-5-10.
 */

public class ForegroundService extends Service {
    public final static String TAG = "ForegroundService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AsfLog.d(TAG, "--------->onStartCommand: ");
        //启动前台服务
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationOver26(Constants.FORGROUND_SERVICE_CHANNEL_ID,
                    Constants.FORGROUND_SERVICE_TITILE,
                    Constants.FORGROUND_SERVICE_CONTENT_TEXT,
                    R.mipmap.ic_launcher,
                    Constants.FORGROUND_SERVICE_ID);
        } else {
            setNotificationBelow26(Constants.FORGROUND_SERVICE_TITILE,
                    Constants.FORGROUND_SERVICE_CONTENT_TEXT,
                    R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher,
                    Constants.FORGROUND_SERVICE_ID);
        }
        //notificationManager.notify(1, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    /**
     * 8.0以上通知
     * @param channelId
     * @param title
     * @param description
     * @param icon
     * @param forgroundServiceId
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setNotificationOver26(String channelId, String title, String description, int icon, int forgroundServiceId) {

        NotificationChannel channel = new NotificationChannel(channelId, title, IMPORTANCE_LOW);
        channel.setDescription(description);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

        //在创建的通知渠道上发送通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        builder.setSmallIcon(icon) //设置通知图标
                .setContentTitle(title)//设置通知标题
                .setContentText(description)//设置通知内容
                .setAutoCancel(true) //用户触摸时，自动关闭
                .setOngoing(true);//设置处于运行状态
        builder.setContentIntent(PendingIntent.getBroadcast(this, forgroundServiceId, new Intent(this, NotificationClickReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT));
        //将服务置于启动状态 NOTIFICATION_ID指的是创建的通知的ID
        startForeground(forgroundServiceId, builder.build());
    }

    /**
     * 8.0以下服务通知
     * @param title
     * @param description
     * @param smallIcon
     * @param bigIcon
     * @param forgroundServiceId
     */
    @SuppressLint({"NewApi", "LocalSuppress"})
    public void setNotificationBelow26(String title, String description, int smallIcon, int bigIcon, int forgroundServiceId) {
        // 在API11之后构建Notification的方式
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), bigIcon)) // 设置下拉列表中的图标(大图标)
                .setContentTitle(title) // 设置下拉列表里的标题
                .setSmallIcon(smallIcon) // 设置状态栏内的小图标
                .setContentText(description) // 设置描述
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间

        //设置广播通知
        builder.setContentIntent(PendingIntent.getBroadcast(this, 1011, new Intent(this, NotificationClickReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT));
        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        startForeground(forgroundServiceId, notification);
    }

    /**
     * 前台服务广播接收器
     */
    public static class NotificationClickReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            AsfLog.d(TAG, "NotificationClickReceiver --------->onReceive: stop service");
            context.stopService(new Intent(context, ForegroundService.class));
        }
    }
}
