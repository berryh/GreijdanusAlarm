package berryh.android.greijdanusalarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import berryh.android.greijdanusalarm.lib.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InstellingenScherm extends Activity {

    private Button btnSave;
    private CheckBox ma1;
    private CheckBox ma2;
    private CheckBox ma3;
    private CheckBox ma4;
    private CheckBox ma5;
    private CheckBox ma6;
    private CheckBox ma7;
    private CheckBox di1;
    private CheckBox di2;
    private CheckBox di3;
    private CheckBox di4;
    private CheckBox di5;
    private CheckBox di6;
    private CheckBox di7;
    private CheckBox wo1;
    private CheckBox wo2;
    private CheckBox wo3;
    private CheckBox wo4;
    private CheckBox wo5;
    private CheckBox wo6;
    private CheckBox wo7;
    private CheckBox do1;
    private CheckBox do2;
    private CheckBox do3;
    private CheckBox do4;
    private CheckBox do5;
    private CheckBox do6;
    private CheckBox do7;
    private CheckBox vr1;
    private CheckBox vr2;
    private CheckBox vr3;
    private CheckBox vr4;
    private CheckBox vr5;
    private CheckBox vr6;
    private CheckBox vr7;
    private GreijdanusAlarm ga = Constants.instance().getGreijdanusalarm();
    private List<Boolean> maandag = new ArrayList<>();
    private List<Boolean> dinsdag = new ArrayList<>();
    private List<Boolean> woensdag = new ArrayList<>();
    private List<Boolean> donderdag = new ArrayList<>();
    private List<Boolean> vrijdag = new ArrayList<>();
    private JSONObject settings = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instellingen);
        btnSave = (Button) findViewById(R.id.btnSave);
        ma1 = (CheckBox) findViewById(R.id.ma1);
        ma2 = (CheckBox) findViewById(R.id.ma2);
        ma3 = (CheckBox) findViewById(R.id.ma3);
        ma4 = (CheckBox) findViewById(R.id.ma4);
        ma5 = (CheckBox) findViewById(R.id.ma5);
        ma6 = (CheckBox) findViewById(R.id.ma6);
        ma7 = (CheckBox) findViewById(R.id.ma7);

        di1 = (CheckBox) findViewById(R.id.di1);
        di2 = (CheckBox) findViewById(R.id.di2);
        di3 = (CheckBox) findViewById(R.id.di3);
        di4 = (CheckBox) findViewById(R.id.di4);
        di5 = (CheckBox) findViewById(R.id.di5);
        di6 = (CheckBox) findViewById(R.id.di6);
        di7 = (CheckBox) findViewById(R.id.di7);

        wo1 = (CheckBox) findViewById(R.id.wo1);
        wo2 = (CheckBox) findViewById(R.id.wo2);
        wo3 = (CheckBox) findViewById(R.id.wo3);
        wo4 = (CheckBox) findViewById(R.id.wo4);
        wo5 = (CheckBox) findViewById(R.id.wo5);
        wo6 = (CheckBox) findViewById(R.id.wo6);
        wo7 = (CheckBox) findViewById(R.id.wo7);

        do1 = (CheckBox) findViewById(R.id.do1);
        do2 = (CheckBox) findViewById(R.id.do2);
        do3 = (CheckBox) findViewById(R.id.do3);
        do4 = (CheckBox) findViewById(R.id.do4);
        do5 = (CheckBox) findViewById(R.id.do5);
        do6 = (CheckBox) findViewById(R.id.do6);
        do7 = (CheckBox) findViewById(R.id.do7);

        vr1 = (CheckBox) findViewById(R.id.vr1);
        vr2 = (CheckBox) findViewById(R.id.vr2);
        vr3 = (CheckBox) findViewById(R.id.vr3);
        vr4 = (CheckBox) findViewById(R.id.vr4);
        vr5 = (CheckBox) findViewById(R.id.vr5);
        vr6 = (CheckBox) findViewById(R.id.vr6);
        vr7 = (CheckBox) findViewById(R.id.vr7);

        setupFromFile();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

    }

    @SuppressWarnings("all")
    private void setupFromFile() {
        List<Boolean> lMaandag = null;
        List<Boolean> lDinsdag = null;
        List<Boolean> lWoensdag = null;
        List<Boolean> lDonderdag = null;
        List<Boolean> lVrijdag = null;
        try {
            FileInputStream fis = openFileInput("settings.json");
            StringBuilder builder = new StringBuilder();
            int ch;
            while ((ch = fis.read()) != -1) {
                builder.append((char) ch);
            }
            String jsonSettings = builder.toString();

            System.out.println("RoosterHandler: jsonSettings String: " + jsonSettings);
            JSONParser parser = new JSONParser();
            //JSONObject json = new JSONObject();
            JSONObject json = (JSONObject) parser.parse(jsonSettings);

            System.out.println("RoosterHandler: maandag object class type: " + json.get("maandag").getClass());


            lMaandag = (ArrayList<Boolean>) json.get("maandag");
            lDinsdag = (ArrayList<Boolean>) json.get("dinsdag");
            lWoensdag = (ArrayList<Boolean>) json.get("woensdag");
            lDonderdag = (ArrayList<Boolean>) json.get("donderdag");
            lVrijdag = (ArrayList<Boolean>) json.get("vrijdag");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lMaandag == null) {
            return;
        }

        ma1.setChecked(lMaandag.get(0));
        ma2.setChecked(lMaandag.get(1));
        ma3.setChecked(lMaandag.get(2));
        ma4.setChecked(lMaandag.get(3));
        ma5.setChecked(lMaandag.get(4));
        ma6.setChecked(lMaandag.get(5));
        ma7.setChecked(lMaandag.get(6));

        di1.setChecked(lDinsdag.get(0));
        di2.setChecked(lDinsdag.get(1));
        di3.setChecked(lDinsdag.get(2));
        di4.setChecked(lDinsdag.get(3));
        di5.setChecked(lDinsdag.get(4));
        di6.setChecked(lDinsdag.get(5));
        di7.setChecked(lDinsdag.get(6));

        wo1.setChecked(lWoensdag.get(0));
        wo2.setChecked(lWoensdag.get(1));
        wo3.setChecked(lWoensdag.get(2));
        wo4.setChecked(lWoensdag.get(3));
        wo5.setChecked(lWoensdag.get(4));
        wo6.setChecked(lWoensdag.get(5));
        wo7.setChecked(lWoensdag.get(6));

        do1.setChecked(lDonderdag.get(0));
        do2.setChecked(lDonderdag.get(1));
        do3.setChecked(lDonderdag.get(2));
        do4.setChecked(lDonderdag.get(3));
        do5.setChecked(lDonderdag.get(4));
        do6.setChecked(lDonderdag.get(5));
        do7.setChecked(lDonderdag.get(6));

        vr1.setChecked(lVrijdag.get(0));
        vr2.setChecked(lVrijdag.get(1));
        vr3.setChecked(lVrijdag.get(2));
        vr4.setChecked(lVrijdag.get(3));
        vr5.setChecked(lVrijdag.get(4));
        vr6.setChecked(lVrijdag.get(5));
        vr7.setChecked(lVrijdag.get(6));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.instellingen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_instellingen_hoofdscherm:
                save();
                startActivity(new Intent(this, HoofdScherm.class));
                return true;
        }

        return false;

    }

    @SuppressWarnings("unchecked")
    private void save() {

        TextView status = (TextView) findViewById(R.id.status);

        status.setText("Bezig met opslaan...");

        maandag.add(ma1.isChecked());
        maandag.add(ma2.isChecked());
        maandag.add(ma3.isChecked());
        maandag.add(ma4.isChecked());
        maandag.add(ma5.isChecked());
        maandag.add(ma6.isChecked());
        maandag.add(ma7.isChecked());

        dinsdag.add(di1.isChecked());
        dinsdag.add(di2.isChecked());
        dinsdag.add(di3.isChecked());
        dinsdag.add(di4.isChecked());
        dinsdag.add(di5.isChecked());
        dinsdag.add(di6.isChecked());
        dinsdag.add(di7.isChecked());

        woensdag.add(wo1.isChecked());
        woensdag.add(wo2.isChecked());
        woensdag.add(wo3.isChecked());
        woensdag.add(wo4.isChecked());
        woensdag.add(wo5.isChecked());
        woensdag.add(wo6.isChecked());
        woensdag.add(wo7.isChecked());

        donderdag.add(do1.isChecked());
        donderdag.add(do2.isChecked());
        donderdag.add(do3.isChecked());
        donderdag.add(do4.isChecked());
        donderdag.add(do5.isChecked());
        donderdag.add(do6.isChecked());
        donderdag.add(do7.isChecked());

        vrijdag.add(vr1.isChecked());
        vrijdag.add(vr2.isChecked());
        vrijdag.add(vr3.isChecked());
        vrijdag.add(vr4.isChecked());
        vrijdag.add(vr5.isChecked());
        vrijdag.add(vr6.isChecked());
        vrijdag.add(vr7.isChecked());


        if (settings == null) {
            System.out.println("InstellingenScherm: settings is null");
            return;
        }

        settings.put("maandag", maandag);
        settings.put("dinsdag", dinsdag);
        settings.put("woensdag", woensdag);
        settings.put("donderdag", donderdag);
        settings.put("vrijdag", vrijdag);

        try {
            FileOutputStream fos = openFileOutput("settings.json", 0);
            BufferedWriter JSONSettings = new BufferedWriter(new OutputStreamWriter(fos));
            JSONSettings.write(settings.toJSONString());
            JSONSettings.flush();
            JSONSettings.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        status.setText("Instellingen Opgeslagen!");
    }
}
