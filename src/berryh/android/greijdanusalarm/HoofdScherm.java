package berryh.android.greijdanusalarm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import berryh.android.greijdanusalarm.Roosters.DagRoosterBase;
import berryh.android.greijdanusalarm.Roosters.WeekRoosterBase;

import java.util.Calendar;

public class HoofdScherm extends Activity {

    private TextView volgendeLesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoofdscherm);
        volgendeLesText = (TextView)findViewById(R.id.hoofdscherm_textview_tijd_volgende_les_actual);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hoofd_scherm, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch(item.getItemId()){
    	case R.id.menu_item_hoofdscherm_instellingen:
    		startActivity(new Intent(this, InstellingenScherm.class));
            return true;
    	case R.id.menu_item_instellingen_hoofdscherm:
    		startActivity(new Intent(this, this.getClass()));
            return true;
    	}
    	
    	return false;
    	
    }
    
}
