package berryh.android.greijdanusalarm.roosterTypes;

/**
 * Created by Berry Holtrust on 9-1-14.
 */
public class RoosterTemplateTimeHelper {

    protected int hour;
    protected int minute;

    public RoosterTemplateTimeHelper(int hour, int minute){
        this.hour = hour;
        this.minute= minute;
    }

    public int getHour(){
        return this.hour;
    }

    public int getMinute(){
        return  this.minute;
    }
}
