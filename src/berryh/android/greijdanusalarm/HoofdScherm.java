package berryh.android.greijdanusalarm;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import berryh.android.greijdanusalarm.Enums.Dagen;
import berryh.android.greijdanusalarm.Enums.EnumDagen;
import berryh.android.greijdanusalarm.Handler.RoosterHandler;
import berryh.android.greijdanusalarm.jtRooster.WeekRooster;
import berryh.android.greijdanusalarm.lib.Constants;
import berryh.android.greijdanusalarm.lib.UrenHandler;

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
    private Dialog progressDialog;
    //private Bundle bundle;


    @SuppressWarnings("deprecated")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showProgressDialog(this);
        //this.bundle = savedInstanceState;
        //setContentView(R.layout.activity_hoofdscherm);
        setContentView(R.layout.activity_loading);
        //constants.setDebug(true);
        constants.setTelephonyManager((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
        constants.setGreijdanusalarm((GreijdanusAlarm) getApplication());
        constants.setHoofdscherm(this);
        //TODO Remember to turn this off on publish or real world test use
        constants.setDebug(false);
        if (constants.isDebug()) {
            constants.setLesdag(EnumDagen.MAANDAG);
        } else {
            constants.setLesdag(Dagen.getCurrentDag());
        }
        if (BuildConfig.DEBUG) {
            Log.e("GreijdanusAlarm", "This application is released in DEBUG mode. Please notify the author.");
        }
        System.out.println("Debug Mode: " + constants.isDebug());
        if (isNetworkOnline()) {
            UrenHandler.instance().setupUren();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle extras = super.getIntent().getExtras();
        if (extras != null && extras.getBoolean("notification")) {
            if (extras.getBoolean("les")) {
                Toast.makeText(this, "Je hebt dit uur les", 5).show();
            } else {
                Toast.makeText(this, "Je hebt nu pauze", 5).show();
            }
        }
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
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hoofd_scherm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_hoofdscherm_instellingen:
                startActivity(new Intent(this, InstellingenScherm.class));
                return true;
            case R.id.menu_item_hoofdscherm_close:
                moveTaskToBack(true);
                return true;
        }
        return false;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent data) {
        switch (requestCode) {
            case 1:
                settingsExist = true;
        }
    }

    private void showProgressDialog(Activity activity) {
        if ((progressDialog == null) || (!progressDialog.isShowing())) {
            progressDialog = new Dialog(activity);
            progressDialog.getWindow().getCurrentFocus();
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setContentView(R.layout.activity_loading);
            progressDialog.setCancelable(false);
            progressDialog.setOwnerActivity(activity);
            progressDialog.show();
        } else {
            progressDialog.setOwnerActivity(activity);
        }
    }

    public boolean isNetworkOnline() {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;

    }

}
