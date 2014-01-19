package berryh.android.greijdanusalarm.jtRooster;

import berryh.android.greijdanusalarm.Enums.EnumDagen;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Berry Holtrust on 19-1-14.
 */
public class WeekRooster {

    public HashMap<EnumDagen, DagRooster> lesweek = new HashMap<>();

    public WeekRooster(List<Boolean> lMaandag, List<Boolean> lDinsdag, List<Boolean> lWoensdag, List<Boolean> lDonderdag, List<Boolean> lVrijdag) {
        lesweek.put(EnumDagen.MAANDAG, new DagRooster(lMaandag));
        lesweek.put(EnumDagen.DINSDAG, new DagRooster(lDinsdag));
        lesweek.put(EnumDagen.WOENSDAG, new DagRooster(lWoensdag));
        lesweek.put(EnumDagen.DONDERDAG, new DagRooster(lDonderdag));
        lesweek.put(EnumDagen.VRIJDAG, new DagRooster(lVrijdag));
    }


}
