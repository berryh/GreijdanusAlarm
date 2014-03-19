package berryh.android.greijdanusalarm.lib;

import android.app.PendingIntent;
import android.telephony.TelephonyManager;
import berryh.android.greijdanusalarm.Enums.EnumDagen;
import berryh.android.greijdanusalarm.GreijdanusAlarm;
import berryh.android.greijdanusalarm.HoofdScherm;
import berryh.android.greijdanusalarm.InstellingenScherm;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry Holtrust on 22-1-14.
 */
public class Constants {

    private static final Constants INSTANCE = new Constants();
    public List<PendingIntent> pendingIntents = new ArrayList<>();
    public List<Interval> lestijden = new ArrayList<>();
    private boolean isDebug = false;
    private HoofdScherm hoofdscherm;
    private GreijdanusAlarm greijdanusalarm;
    private InstellingenScherm instellingenscherm;
    private DateTime dt = new DateTime();
    private TelephonyManager telephonyManager;
    private EnumDagen lesdag;
    private DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("ddMMyyyy");
    private boolean isVMinuten = false;

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

    public TelephonyManager getTelephonyManager() {
        return telephonyManager;
    }

    public void setTelephonyManager(TelephonyManager telephonyManager) {
        this.telephonyManager = telephonyManager;
    }

    public EnumDagen getLesdag() {
        return lesdag;
    }

    public void setLesdag(EnumDagen lesdag) {
        this.lesdag = lesdag;
    }

    public DateTimeFormatter getDtFormatter() {
        return dtFormatter;
    }

    public boolean isVMinuten() {
        return isVMinuten;
    }

    public void setVMinuten(boolean isVMinuten) {
        this.isVMinuten = isVMinuten;
    }
}
