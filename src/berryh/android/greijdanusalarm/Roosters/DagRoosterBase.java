package berryh.android.greijdanusalarm.Roosters;

import berryh.android.greijdanusalarm.Enums.Dagen;

/**
 * Created by Berry Holtrust on 9-1-14.
 */
public class DagRoosterBase {

    private UrenBase[] uren;
    private Dagen lesDag;

    public DagRoosterBase(int blokDuur, boolean[] heeftLes, Dagen dag) {

        switch (blokDuur) {
            default:
                uren[uren.length] = new UrenBase("08:00", "09:00", heeftLes[uren.length]);
                uren[uren.length] = new UrenBase("09:00", "10:05", heeftLes[uren.length]);
                uren[uren.length] = new UrenBase("10:10", "11:10", heeftLes[uren.length]);
                uren[uren.length] = new UrenBase("11:15", "12:15", heeftLes[uren.length]);
                uren[uren.length] = new UrenBase("12:35", "13:35", heeftLes[uren.length]);
                uren[uren.length] = new UrenBase("13:55", "14:55", heeftLes[uren.length]);
                uren[uren.length] = new UrenBase("15:00", "16:00", heeftLes[uren.length]);
        }
    }

    public UrenBase getLesuur(int lesUur){
        return uren[lesUur -1];
    }

    public boolean heeftUurLes(int lesUur){
        return uren[lesUur - 1].isHeeftLes();
    }

    public Dagen getLesDag(){
        return lesDag;
    }
}
