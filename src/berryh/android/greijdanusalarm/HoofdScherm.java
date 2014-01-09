package berryh.android.greijdanusalarm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class HoofdScherm extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoofdscherm);
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
    	case R.id.menu_item_instellingen_hoofdscherm:
    		startActivity(new Intent(this, this.getClass()));
    	}
    	
    	return false;
    	
    }
    
}
