package berryh.android.greijdanusalarm.lib;

import berryh.android.greijdanusalarm.GreijdanusAlarm;
import berryh.android.greijdanusalarm.HoofdScherm;
import berryh.android.greijdanusalarm.InstellingenScherm;
import org.joda.time.DateTime;

/**
 * Created by Berry Holtrust on 22-1-14.
 */
public class Constants {

    private static final Constants INSTANCE = new Constants();
    private boolean isDebug = false;
    private HoofdScherm hoofdscherm;
    private GreijdanusAlarm greijdanusalarm;
    private InstellingenScherm instellingenscherm;
    private DateTime dt = new DateTime();

    public static Constants instance() {
        return INSTANCE;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public InstellingenScherm getInstellingenscherm() {
        return instellingenscherm;
    }

    public void setInstellingenscherm(InstellingenScherm instellingenscherm) {
        this.instellingenscherm = instellingenscherm;
    }

    public GreijdanusAlarm getGreijdanusalarm() {
        return greijdanusalarm;
    }

    public void setGreijdanusalarm(GreijdanusAlarm greijdanusalarm) {
        this.greijdanusalarm = greijdanusalarm;
    }

    public HoofdScherm getHoofdscherm() {
        return hoofdscherm;
    }

    public void setHoofdscherm(HoofdScherm hoofdscherm) {
        this.hoofdscherm = hoofdscherm;
    }

    public DateTime getDt() {
        return dt;
    }
}
