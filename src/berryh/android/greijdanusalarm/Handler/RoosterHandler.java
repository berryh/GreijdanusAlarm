package berryh.android.greijdanusalarm.Handler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import berryh.android.greijdanusalarm.Enums.EnumDagen;
import berryh.android.greijdanusalarm.GreijdanusAlarm;
import berryh.android.greijdanusalarm.HoofdScherm;
import berryh.android.greijdanusalarm.Service.NotificationReceiver;
import berryh.android.greijdanusalarm.jtRooster.WeekRooster;
import berryh.android.greijdanusalarm.lib.Constants;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Berry Holtrust on 10-1-14.
 */
public class RoosterHandler {

    private static final RoosterHandler INSTANCE = new RoosterHandler();
    private WeekRooster wRooster;
    private GreijdanusAlarm ga;
    private Context ct;
    private HoofdScherm hs;

    public static RoosterHandler instance() {
        return INSTANCE;
    }

    public String getCurrentTijd() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }

    public WeekRooster setupRooster2(GreijdanusAlarm gb, Context cy, HoofdScherm ht) {
        ga = gb;
        ct = cy;
        hs = ht;
        List<Boolean> lMaandag = new ArrayList<>();
        List<Boolean> lDinsdag = new ArrayList<>();
        List<Boolean> lWoensdag = new ArrayList<>();
        List<Boolean> lDonderdag = new ArrayList<>();
        List<Boolean> lVrijdag = new ArrayList<>();
        try {
            FileInputStream fis = ct.openFileInput("settings.json");
            StringBuilder builder = new StringBuilder();
            int ch;
            while ((ch = fis.read()) != -1) {
                builder.append((char) ch);
            }
            String jsonSettings = builder.toString();

            System.out.println("RoosterHandler: jsonSettings String: " + jsonSettings);
            JSONParser parser = new JSONParser();
            try {
                //JSONObject json = new JSONObject();
                JSONObject json = (JSONObject) parser.parse(jsonSettings);

                System.out.println("RoosterHandler: maandag object class type: " + json.get("maandag").getClass());


                lMaandag = (ArrayList<Boolean>) json.get("maandag");
                lDinsdag = (ArrayList<Boolean>) json.get("dinsdag");
                lWoensdag = (ArrayList<Boolean>) json.get("woensdag");
                lDonderdag = (ArrayList<Boolean>) json.get("donderdag");
                lVrijdag = (ArrayList<Boolean>) json.get("vrijdag");
            } catch (Exception e) {
                System.out.println("RoosterHandler: JSON Exception Occured");
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (lMaandag.isEmpty()) {
            System.out.println("Rooster arrays are not loading properly");
        }


        WeekRooster wr = new WeekRooster(lMaandag, lDinsdag, lWoensdag, lDonderdag, lVrijdag);
        wRooster = wr;
        return wr;
    }

    public void setupNotifications(EnumDagen lesdag) {
        AlarmManager am = (AlarmManager) ct.getSystemService(Context.ALARM_SERVICE);
        if (!Constants.instance().pendingIntents.isEmpty()) {
            Iterator iterator = Constants.instance().pendingIntents.iterator();
            while (iterator.hasNext()) {
                PendingIntent pendingIntent = (PendingIntent) iterator.next();
                am.cancel(pendingIntent);
            }
            Constants.instance().pendingIntents.clear();
        }


        if (lesdag == null) {
            System.out.println("RoosterHandler: lesdag is null");
            return;
        }
        if (wRooster == null) {
            System.out.println("RoosterHandler: wRooster is null");
        }

        for (int j = 0; j < wRooster.lesweek.get(lesdag).lesuren.size(); j++) {
            Interval jIn = wRooster.lesweek.get(lesdag).lesuren.get(j);
            System.out.println("RoosterHandler: j in setupNotifications: " + j);
            System.out.println("RoosterHandler: User heeft les op uur -1 waar uur is: " + j);
            if (jIn == null) {
                continue;
            }
            if (jIn.getStart().isAfterNow() || jIn.getStart().isEqualNow()) {
                Intent in1 = new Intent(ct, NotificationReceiver.class);
                in1.putExtra("state", true);
                in1.putExtra("notTime", jIn.getStartMillis());
                in1.setAction("berryh.android.greijdanusalarm.LES_UUR1" + j);
                PendingIntent pi1 = PendingIntent.getBroadcast(ct, j, in1, PendingIntent.FLAG_UPDATE_CURRENT);
                am.set(AlarmManager.RTC_WAKEUP, jIn.getStartMillis(), pi1);
                Constants.instance().pendingIntents.add(pi1);
            }
            if (jIn.getEnd().isAfterNow() || jIn.getEnd().isEqualNow()) {
                Intent in2 = new Intent(ct, NotificationReceiver.class);
                in2.putExtra("state", false);
                in2.putExtra("notTime", jIn.getEndMillis());
                in2.setAction("berryh.android.greijdanusalarm.LES_UUR2" + j);
                PendingIntent pi2 = PendingIntent.getBroadcast(ct, j ^ 2, in2, PendingIntent.FLAG_UPDATE_CURRENT);
                am.set(AlarmManager.RTC_WAKEUP, jIn.getEndMillis(), pi2);
                Constants.instance().pendingIntents.add(pi2);
            }
        }


    }

    public String getNextLesTime() {

        if (Constants.instance().getLesdag() == null) {
            return null;
        }
        if (wRooster == null) {
            System.out.println("RoosterHandler: wRooster is null");
        }

        for (int j = 0; j < wRooster.lesweek.get(Constants.instance().getLesdag()).lesuren.size(); j++) {
            Interval jIn = wRooster.lesweek.get(Constants.instance().getLesdag()).lesuren.get(j);
            if (jIn == null) {
                System.out.println("RoosterHandler: jIn is null");
                continue;
            }
            if (jIn.getStart().isBeforeNow() || jIn.getStart().isEqualNow()) {
                System.out.println("RoosterHandler: isBeforeNow() or isEqualNow()");
                DateTimeFormatter fmt = DateTimeFormat.forPattern("kk:mm");
                System.out.println("RoosterHandler: fmt Time: " + fmt.print(jIn.getStart()));
                return fmt.print(jIn.getStart());
            }
        }
        return null;
    }
}
