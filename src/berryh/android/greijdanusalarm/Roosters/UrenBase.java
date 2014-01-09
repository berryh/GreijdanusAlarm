package berryh.android.greijdanusalarm.Roosters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Berry Holtrust on 9-1-14.
 */
public class UrenBase {

    private SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
    private Calendar begin;
    private Calendar eind;
    private boolean heeftLes;

    public UrenBase(String beginLes, String eindLes, boolean heeftLes) {
        try {
            this.begin.setTime(parser.parse(beginLes));
            this.eind.setTime(parser.parse(eindLes));
            this.heeftLes = heeftLes;
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public Calendar getBegin(){
        return begin;
    }

    public Calendar getEind(){
        return eind;
    }

    public boolean isHeeftLes(){
        return heeftLes;
    }

}
