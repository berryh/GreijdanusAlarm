package berryh.android.greijdanusalarm.Handler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import berryh.android.greijdanusalarm.Enums.EnumDagen;
import berryh.android.greijdanusalarm.GreijdanusAlarm;
import berryh.android.greijdanusalarm.Roosters.DagRoosterBase;
import berryh.android.greijdanusalarm.Roosters.UrenBase;
import berryh.android.greijdanusalarm.Service.NotificationReceiver;
import berryh.android.greijdanusalarm.jtRooster.WeekRooster;
import org.joda.time.Interval;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Berry Holtrust on 10-1-14.
 */
public class RoosterHandler {

    private WeekRooster wRooster;
    private GreijdanusAlarm ga;
    private Context ct;

    public HashMap<EnumDagen, DagRoosterBase> setupRooster() {
        HashMap<EnumDagen, DagRoosterBase> weekRooster = new HashMap<EnumDagen, DagRoosterBase>();

        List<Boolean> maandagHeeftles = new ArrayList<Boolean>(Arrays.asList(false, true, true, true, true, true, true));
        DagRoosterBase maandag = new DagRoosterBase(60, maandagHeeftles, EnumDagen.MAANDAG);

        List<Boolean> vrijdagHeeftles = new ArrayList<Boolean>(Arrays.asList(false, true, true, true, true, true, true));
        DagRoosterBase vrijdag = new DagRoosterBase(60, vrijdagHeeftles, EnumDagen.VRIJDAG);

        weekRooster.put(EnumDagen.MAANDAG, maandag);
        weekRooster.put(EnumDagen.VRIJDAG, vrijdag);

        return weekRooster;
    }

    public String getCurrentTijd() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }

    public int getCurrentLesuur(DagRoosterBase dagrooster) {
        String huidigeTijd = getCurrentTijd();
        Calendar tijd = Calendar.getInstance();
        try {
            tijd.setTime(new SimpleDateFormat("HH:mm").parse(huidigeTijd));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        try {
            if (dagrooster.uren.size() == 0) {
                System.out.println("Dagrooster Uren Size == 0");
                return 0;
            }
        } catch (NullPointerException e) {

            System.out.println("For some reason, dagrooster or dagrooster.uren is null");

            //e.printStackTrace();
            return 0;
        }

        for (int uur = 1; uur < dagrooster.uren.size(); uur++) {
            UrenBase lesuur = null;
            try {
                lesuur = dagrooster.uren.get(uur);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if (lesuur == null) {
                continue;
            }
            if ((lesuur.getBegin().equals(tijd) || lesuur.getBegin().before(tijd)) && (lesuur.getEind().equals(tijd) || lesuur.getEind().after(tijd))) {
                return uur;
            }
        }


        return 0;
    }

    public WeekRooster setupRooster2(GreijdanusAlarm gb, Context cy) {
        ga = gb;
        ct = cy;
        List<Boolean> lMaandag = new ArrayList<>(Arrays.asList(false, true, true, true, true, true, true));
        List<Boolean> lDinsdag = new ArrayList<>(Arrays.asList(false, true, true, true, true, true, true));
        List<Boolean> lWoensdag = new ArrayList<>(Arrays.asList(false, true, true, true, true, true, true));
        List<Boolean> lDonderdag = new ArrayList<>(Arrays.asList(false, true, true, true, true, true, true));
        List<Boolean> lVrijdag = new ArrayList<>(Arrays.asList(false, true, true, true, true, true, true));

        WeekRooster wr = new WeekRooster(lMaandag, lDinsdag, lWoensdag, lDonderdag, lVrijdag);
        wRooster = wr;
        return wr;
    }

    public void setupNotifications(EnumDagen lesdag) {
        //DateTime notification = ga.dt.withTime(ga.dt.getHourOfDay(),ga.dt.getMinuteOfHour() + 2,0,0);
        //Calendar notification = Calendar.getInstance();
        //notification.add(Calendar.MINUTE, 2);
        // Intent i = new Intent(ct, NotificationReceiver.class);
        //i.putExtra("alarm_message", "Greijdanus Alarm Melding, je hebt les");
        //PendingIntent pi = PendingIntent.getBroadcast(ct, 192837, i, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) ct.getSystemService(Context.ALARM_SERVICE);
        //System.out.println(notification.toDate().getTime());
        //System.out.println(notification.toCalendar(Locale.ROOT).getTimeInMillis());
        //System.out.println(System.currentTimeMillis());
        // am.set(AlarmManager.RTC_WAKEUP, notification.toCalendar(Locale.ROOT).getTimeInMillis(), pi);

        if (lesdag == EnumDagen.MAANDAG) {
            for (int j = 0; j < wRooster.lesweek.get(EnumDagen.MAANDAG).lesuren.size(); j++) {
                Interval jIn = wRooster.lesweek.get(EnumDagen.MAANDAG).lesuren.get(j);
                if (jIn == null) {
                    continue;
                }
                if (jIn.getStart().isAfterNow()) {
                    Intent in1 = new Intent(ct, NotificationReceiver.class);
                    in1.putExtra("state", true);
                    PendingIntent pi1 = PendingIntent.getBroadcast(ct, j, in1, PendingIntent.FLAG_UPDATE_CURRENT);
                    am.set(AlarmManager.RTC_WAKEUP, jIn.getStart().toCalendar(Locale.ROOT).getTimeInMillis(), pi1);
                }
                if (jIn.getEnd().isAfterNow()) {
                    Intent in2 = new Intent(ct, NotificationReceiver.class);
                    in2.putExtra("state", false);
                    PendingIntent pi2 = PendingIntent.getBroadcast(ct, j ^ 2, in2, PendingIntent.FLAG_UPDATE_CURRENT);
                    am.set(AlarmManager.RTC_WAKEUP, jIn.getEnd().toCalendar(Locale.ROOT).getTimeInMillis(), pi2);
                }
            }
        }


    }

}
