package berryh.android.greijdanusalarm.Enums;

/**
 * Created by Berry Holtrust on 9-1-14.
 */
public enum Dagen {
    MAANDAG,DINSDAG,WOENSDAG,DONDERDAG,VRIJDAG;

    public String toString(Dagen dag){
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

    public Dagen intToDag(int dag){
        switch (dag){
            case 1:
                return MAANDAG;
            case 2:
                return DINSDAG;
            case 3:
                return WOENSDAG;
            case 4:
                return DONDERDAG;
            case 5:
                return VRIJDAG;
            default:
                return null;
        }
    }

    public int dagToIntNormal(Dagen dag){
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

    public int dagToIntArray(Dagen dag){
        return dagToIntNormal(dag)+1;
    }
}
