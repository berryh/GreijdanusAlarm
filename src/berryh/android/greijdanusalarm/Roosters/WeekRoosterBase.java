package berryh.android.greijdanusalarm.Roosters;

import berryh.android.greijdanusalarm.Enums.Dagen;

import java.util.ArrayList;

/**
 * Created by Berry Holtrust on 9-1-14.
 */
public class WeekRoosterBase {

    private ArrayList<DagRoosterBase> lesDagen = new ArrayList<DagRoosterBase>();

    public WeekRoosterBase(boolean[] maandagHeeftLes, boolean[] dinsdagHeeftLes, boolean[] woensdagHeeftLes, boolean[] donderdagHeeftLes, boolean[] vrijdagHeeftLes) {
        this.initWeek(maandagHeeftLes, dinsdagHeeftLes, woensdagHeeftLes, donderdagHeeftLes, vrijdagHeeftLes);
    }


    private void initWeek(boolean[] maandagLessen, boolean[] dinsdagLessen, boolean[] woensdagLessen, boolean[] donderdagLessen, boolean[] vrijdagLessen) {
        DagRoosterBase maandag = new DagRoosterBase(60, maandagLessen, Dagen.MAANDAG);
        DagRoosterBase dinsdag = new DagRoosterBase(60, dinsdagLessen, Dagen.DINSDAG);
        DagRoosterBase woensdag = new DagRoosterBase(60, woensdagLessen, Dagen.WOENSDAG);
        DagRoosterBase donderdag = new DagRoosterBase(60, donderdagLessen, Dagen.DONDERDAG);
        DagRoosterBase vrijdag = new DagRoosterBase(60, vrijdagLessen, Dagen.VRIJDAG);
        lesDagen.add(maandag);
        lesDagen.add(dinsdag);
        lesDagen.add(woensdag);
        lesDagen.add(donderdag);
        lesDagen.add(vrijdag);

    }

    public DagRoosterBase getDagRoosterBase(Dagen dag){
        return lesDagen.get(dag.dagToIntArray(dag));
    }


}
