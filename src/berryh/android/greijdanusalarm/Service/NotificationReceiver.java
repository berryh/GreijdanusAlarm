package berryh.android.greijdanusalarm.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import berryh.android.greijdanusalarm.R;

/**
 * Created by Berry Holtrust on 11-1-14.
 */
public class NotificationReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Notification.Builder mBuilder;
        if (intent.getExtras().getBoolean("state")) {
            mBuilder = new Notification.Builder(context).setSmallIcon(R.drawable.greijdanus).setContentTitle("Greijdanus Alarm").setContentText("Je les begint!");
        } else {
            mBuilder = new Notification.Builder(context).setSmallIcon(R.drawable.greijdanus).setContentTitle("Greijdanus Alarm").setContentText("Je les is afgelopen!");
        }
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());



        /*
        try {
            Bundle bundle = intent.getExtras();
            String message = bundle.getString("alarm_message");
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "There was an error somewhere, but we still received an alarm", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
        */
    }
}
