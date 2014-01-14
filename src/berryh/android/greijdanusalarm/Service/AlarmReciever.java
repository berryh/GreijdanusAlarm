package berryh.android.greijdanusalarm.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import berryh.android.greijdanusalarm.HoofdScherm;
import berryh.android.greijdanusalarm.R;

/**
 * Created by Berry Holtrust on 11-1-14.
 */
public class AlarmReciever extends BroadcastReceiver {

    private static final int MODE_WORLD_READABLE = 0;
    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        String descr = null;

        int id = intent.getIntExtra("id", 0);


        nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        CharSequence from = "Greijdanus Alarm";
        Intent disintent = new Intent(context, HoofdScherm.class);

        disintent.putExtra("id", id);
        CharSequence message = "Je Hebt Les";
        Notification notif = new Notification(R.drawable.greijdanus, "Greijdanus Alarm", System.currentTimeMillis());
        notif.flags = Notification.FLAG_AUTO_CANCEL;

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                disintent, Notification.FLAG_AUTO_CANCEL);

        notif.setLatestEventInfo(context, from, message, contentIntent);
        nm.notify(1, notif);
        //nm.cancel(1);
        //notif.defaults |= Notification.FLAG_AUTO_CANCEL;


    }
}
