package berryh.android.greijdanusalarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import berryh.android.greijdanusalarm.Enums.Dagen;
import berryh.android.greijdanusalarm.Enums.EnumDagen;
import berryh.android.greijdanusalarm.Handler.RoosterHandler;
import berryh.android.greijdanusalarm.jtRooster.WeekRooster;
import berryh.android.greijdanusalarm.lib.Constants;

public class HoofdScherm extends Activity {

    private TextView volgendeLesText;
    //private EnumDagen lesdag = Dagen.getCurrentDag();
    //private EnumDagen lesdag = EnumDagen.MAANDAG;
    private Constants constants = Constants.instance();
    private RoosterHandler rHandler = RoosterHandler.instance();
    //private HashMap<EnumDagen, DagRoosterBase> weekRooster;
    private WeekRooster weekRooster;
    //private SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
    private GreijdanusAlarm ga = (GreijdanusAlarm) super.getApplication();
    private boolean settingsExist = false;
    //private Bundle bundle;


    @SuppressWarnings("deprecated")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.bundle = savedInstanceState;
        //setContentView(R.layout.activity_hoofdscherm);
        setContentView(R.layout.activity_loading);
        //constants.setDebug(true);
        constants.setTelephonyManager((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
        constants.setGreijdanusalarm((GreijdanusAlarm) getApplication());
        constants.setHoofdscherm(this);
        //TODO Remember to turn this off on publish or real world test use
        constants.setDebug(true);
        if (constants.isDebug()) {
            constants.setLesdag(EnumDagen.MAANDAG);
        } else {
            constants.setLesdag(Dagen.getCurrentDag());
        }

        if (BuildConfig.DEBUG) {
            Log.e("GreijdanusAlarm", "This application is running in DEBUG Mode!");
        }

        System.out.println("Debug Mode: " + constants.isDebug());


        //System.out.println(getFilesDir());


        //Toast.makeText(this, "Welkom!", 5).show();




        /*
        if (lesdag != null) {
            //int lesuur = rHandler.getCurrentLesuur(weekRooster.get(lesdag));
            int lesuur = rHandler.getCurrentLesuur(weekRooster.get(1));
            if (lesuur != 0) {
                Calendar lesuurBegin = weekRooster.get(lesdag).getLesuur(lesuur).getBegin();
                volgendeLesText.setText(lesuurBegin.toString());


                if (weekRooster.get(lesdag).heeftUurLes(lesuur)) {
                    Toast.makeText(this, "Je hebt dit uur les!", 5).show();
                } else {
                    Toast.makeText(this, "Je hebt dit uur geen les!", 5).show();
                }
            } else {
                Toast.makeText(this, "Het huidige lesuur is 0, je hebt vandaag waarschijnlijk geen school meer of je hebt weekend", 5).show();
            }
        }
        */
/*
        DateTime notification = ga.dt.withTime(ga.dt.getHourOfDay(),ga.dt.getMinuteOfHour() + 2,0,0);
        //Calendar notification = Calendar.getInstance();
        //notification.add(Calendar.MINUTE, 2);
        Intent i = new Intent(super.getBaseContext(), NotificationReceiver.class);
        i.putExtra("alarm_message", "Greijdanus Alarm Melding, je hebt les");
        PendingIntent pi = PendingIntent.getBroadcast(this, 192837, i, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        System.out.println(notification.toDate().getTime());
        System.out.println(notification.toCalendar(Locale.ROOT).getTimeInMillis());
        System.out.println(System.currentTimeMillis());
        am.set(AlarmManager.RTC_WAKEUP, notification.toCalendar(Locale.ROOT).getTimeInMillis(), pi);
*/

        /*

        getBaseContext().registerReceiver(new NotificationReceiver(), new IntentFilter());

        Calendar cal = Calendar.getInstance();
        try{
            cal.setTime(parser.parse("17:30"));
            System.out.printf("Scheduled Hour: %s %s", cal.getTime().getHours(), System.getProperty("line.separator"));
            System.out.printf("Scheduled Minute: %s %s", cal.getTime().getMinutes(), System.getProperty("line.separator"));
        }catch (ParseException e){
            e.printStackTrace();
        }

        Calendar cal2 = Calendar.getInstance();
        Date cDate = new Date();
        cal2.setTime(cDate);
        try {
            cal2.setTime(parser.parse("17:40"));
        } catch (ParseException e) {
            System.out.println("Parse Exception Occured.");
            e.printStackTrace();
        }

        System.out.printf("System Hour: %s %s", cal2.getTime().getHours(), System.getProperty("line.separator"));
        System.out.printf("System Minute: %s %s", cal2.getTime().getMinutes(), System.getProperty("line.separator"));

        System.out.printf("Time of system in MS: %s %s", System.currentTimeMillis(), System.getProperty("line.separator"));
        System.out.printf("Time of cal2 in MS: %s %s", cal2.getTime(), System.getProperty("line.separator"));


        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("id", this.getTaskId());
        PendingIntent penintent = PendingIntent.getBroadcast(this, this.getTaskId(), intent, 0);
        AlarmManager alm = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        alm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), penintent);

        */

    }

    @Override
    public void onResume() {
        super.onResume();
        setContentView(R.layout.activity_hoofdscherm);
        volgendeLesText = (TextView) findViewById(R.id.hoofdscherm_textview_tijd_volgende_les_actual);
        String[] files = fileList();
        for (String file : files) {
            if (file.equals("settings.json")) {
                settingsExist = true;
            }
        }
        if (!settingsExist) {
            startActivityForResult(new Intent(this, InstellingenScherm.class), 1);
        }
        if (weekRooster == null) {
            weekRooster = rHandler.setupRooster2(ga, getApplicationContext(), this);
        }
        if (constants.getLesdag() != null) {
            rHandler.setupNotifications(constants.getLesdag());
        }
        String nextLes = rHandler.getNextLesTime();
        if (nextLes != null) {
            if (volgendeLesText != null) {
                volgendeLesText.setText(nextLes);
            } else {
                System.out.println("HoofdScherm: volgendeLesText is null");
            }
        } else {
            System.out.println("HoofdScherm: nextLes is null");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hoofd_scherm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_hoofdscherm_instellingen:
                startActivity(new Intent(this, InstellingenScherm.class));
                return true;
        }

        return false;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent data) {
        switch (requestCode) {
            case 1:
                settingsExist = true;
                System.out.println("Hoofdscherm: Activity InstellingenScherm is afgehandeld");
        }
    }

}
