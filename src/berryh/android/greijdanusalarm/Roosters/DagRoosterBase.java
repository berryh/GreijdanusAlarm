package berryh.android.greijdanusalarm.Roosters;

import berryh.android.greijdanusalarm.Enums.EnumDagen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry Holtrust on 9-1-14.
 */
public class DagRoosterBase {

    //public UrenBase[] uren;
    public List<UrenBase> uren = new ArrayList<UrenBase>();
    public EnumDagen lesDag;

    public DagRoosterBase(int blokDuur, List<Boolean> heeftLes, EnumDagen lesdag) {

        switch (blokDuur) {
            default:
                uren.add(new UrenBase("08:00", "09:00", heeftLes.get(uren.size())));
                uren.add(new UrenBase("09:00", "10:05", heeftLes.get(uren.size())));
                uren.add(new UrenBase("10:10", "11:10", heeftLes.get(uren.size())));
                uren.add(new UrenBase("11:15", "12:15", heeftLes.get(uren.size())));
                uren.add(new UrenBase("12:35", "13:35", heeftLes.get(uren.size())));
                uren.add(new UrenBase("13:55", "14:55", heeftLes.get(uren.size())));
                uren.add(new UrenBase("15:00", "16:00", heeftLes.get(uren.size())));
        }
    }

    public UrenBase getLesuur(int lesUur) {
        return uren.get(lesUur - 1);
    }

    public boolean heeftUurLes(int lesUur) {
        return uren.get(lesUur - 1).isHeeftLes();
    }

    public EnumDagen getLesDag() {
        return lesDag;
    }
}
