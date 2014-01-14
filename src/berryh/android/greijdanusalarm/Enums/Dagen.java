package berryh.android.greijdanusalarm.Enums;

import java.util.Calendar;

/**
 * Created by Berry Holtrust on 10-1-14.
 */
public class Dagen {

    public static String toString(EnumDagen dag) {
        switch (dag){
            case MAANDAG:
                return "Maandag";
            case DINSDAG:
                return "Dinsdag";
            case WOENSDAG:
                return "Woensdag";
            case DONDERDAG:
                return "Donderdag";
            case VRIJDAG:
                return "Vrijdag";
            default:
                return "Weekend";
        }
    }

    public static EnumDagen intToDag(int dag) {
        switch (dag){
            case 1:
                return EnumDagen.MAANDAG;
            case 2:
                return EnumDagen.DINSDAG;
            case 3:
                return EnumDagen.WOENSDAG;
            case 4:
                return EnumDagen.DONDERDAG;
            case 5:
                return EnumDagen.VRIJDAG;
            default:
                return null;
        }
    }

    public static int dagToIntNormal(EnumDagen dag) {
        switch (dag){
            case MAANDAG:
                return 1;
            case DINSDAG:
                return 2;
            case WOENSDAG:
                return 3;
            case DONDERDAG:
                return 4;
            case VRIJDAG:
                return 5;
            default:
                return 0;
        }
    }

    public static int dagToIntArray(EnumDagen dag) {
        return dagToIntNormal(dag)+1;
    }

    public static EnumDagen getCurrentDag() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return intToDag(day);
    }
}
