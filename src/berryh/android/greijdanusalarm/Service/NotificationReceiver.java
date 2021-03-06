package berryh.android.greijdanusalarm.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import berryh.android.greijdanusalarm.HoofdScherm;
import berryh.android.greijdanusalarm.R;

/**
 * Created by Berry Holtrust on 11-1-14.
 */
public class NotificationReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("NotificationReceiver: onReceive is called");
        Notification.Builder mBuilder;

        if (!checkTime(intent.getExtras().getLong("notTime"))) {
            System.out.println("NotificationReceiver: checkTime says it's not the right time!");
            return;
        }

        if (intent.getExtras().getBoolean("state")) {
            mBuilder = new Notification.Builder(context).setSmallIcon(R.drawable.greijdanus).setContentTitle("Greijdanus Alarm").setContentText("Je les begint!");
        } else {
            mBuilder = new Notification.Builder(context).setSmallIcon(R.drawable.greijdanus).setContentTitle("Greijdanus Alarm").setContentText("Je les is afgelopen!");
        }
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        Intent resultIntent = new Intent(context, HoofdScherm.class);
        resultIntent.putExtra("notification", true);
        resultIntent.putExtra("les", intent.getExtras().getBoolean("state"));
        stackBuilder.addParentStack(HoofdScherm.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        Notification notification = mBuilder.build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        mNotificationManager.notify(1, notification);
    }

    private boolean checkTime(Long time) {
        System.out.println("NotificationReceiver: checkTime: Starting Notification Time Check");


        if (((System.currentTimeMillis() - 300000) < time) && ((System.currentTimeMillis() + 300000) > time)) {
            System.out.println("NotificationReceiver: checkTime: Timestamp Is Valid");
            return true;
        }

        System.out.println("NotificationReceiver: checkTime: Timestamp Is Invalid! This is an ERROR!");
        return false;
    }
}
