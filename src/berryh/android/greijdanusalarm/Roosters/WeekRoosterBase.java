package berryh.android.greijdanusalarm.Roosters;

import berryh.android.greijdanusalarm.Enums.Dagen;
import berryh.android.greijdanusalarm.Enums.EnumDagen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry Holtrust on 9-1-14.
 */
public class WeekRoosterBase {

    private ArrayList<DagRoosterBase> lesDagen = new ArrayList<DagRoosterBase>();

    public WeekRoosterBase(List<Boolean> maandagHeeftLes, List<Boolean> dinsdagHeeftLes, List<Boolean> woensdagHeeftLes, List<Boolean> donderdagHeeftLes, List<Boolean> vrijdagHeeftLes) {
        this.initWeek(maandagHeeftLes, dinsdagHeeftLes, woensdagHeeftLes, donderdagHeeftLes, vrijdagHeeftLes);
    }


    private void initWeek(List<Boolean> maandagLessen, List<Boolean> dinsdagLessen, List<Boolean> woensdagLessen, List<Boolean> donderdagLessen, List<Boolean> vrijdagLessen) {
        DagRoosterBase maandag = new DagRoosterBase(60, maandagLessen, EnumDagen.MAANDAG);
        DagRoosterBase dinsdag = new DagRoosterBase(60, dinsdagLessen, EnumDagen.DINSDAG);
        DagRoosterBase woensdag = new DagRoosterBase(60, woensdagLessen, EnumDagen.WOENSDAG);
        DagRoosterBase donderdag = new DagRoosterBase(60, donderdagLessen, EnumDagen.DONDERDAG);
        DagRoosterBase vrijdag = new DagRoosterBase(60, vrijdagLessen, EnumDagen.VRIJDAG);
        lesDagen.add(maandag);
        lesDagen.add(dinsdag);
        lesDagen.add(woensdag);
        lesDagen.add(donderdag);
        lesDagen.add(vrijdag);

    }

    public DagRoosterBase getDagRoosterBase(EnumDagen dag) {
        return lesDagen.get(Dagen.dagToIntArray(dag));
    }


}
