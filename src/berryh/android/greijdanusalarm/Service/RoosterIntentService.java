package berryh.android.greijdanusalarm.Service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Berry Holtrust on 28-1-14.
 */
public class RoosterIntentService extends IntentService {

    public RoosterIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        System.out.println("RoosterIntentService: onHandleIntent Called!");


    }
}
