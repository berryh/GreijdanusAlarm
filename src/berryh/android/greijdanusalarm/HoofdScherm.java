package berryh.android.greijdanusalarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import berryh.android.greijdanusalarm.Enums.Dagen;
import berryh.android.greijdanusalarm.Enums.EnumDagen;
import berryh.android.greijdanusalarm.Handler.RoosterHandler;
import berryh.android.greijdanusalarm.Roosters.DagRoosterBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class HoofdScherm extends Activity {

    private TextView volgendeLesText;
    private EnumDagen lesdag = Dagen.getCurrentDag();
    private RoosterHandler rHandler = new RoosterHandler();
    private HashMap<EnumDagen, DagRoosterBase> weekRooster;
    private SimpleDateFormat parser = new SimpleDateFormat("HH:mm");


    @SuppressWarnings("deprecated")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoofdscherm);
        volgendeLesText = (TextView) findViewById(R.id.hoofdscherm_textview_tijd_volgende_les_actual);

        Toast.makeText(this, "Welkom!", 5).show();

        weekRooster = rHandler.setupRooster();
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


        /*

        getBaseContext().registerReceiver(new AlarmReciever(), new IntentFilter());

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


        Intent intent = new Intent(this, AlarmReciever.class);
        intent.putExtra("id", this.getTaskId());
        PendingIntent penintent = PendingIntent.getBroadcast(this, this.getTaskId(), intent, 0);
        AlarmManager alm = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        alm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), penintent);

        */

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

}
