package berryh.android.greijdanusalarm.jtRooster;

import berryh.android.greijdanusalarm.lib.Constants;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry Holtrust on 19-1-14.
 */
public class DagRooster {

    public List<Interval> lesuren = new ArrayList<Interval>();
    public List<Boolean> heeftLes;
    private DateTime dt = Constants.instance().getDt();

    public DagRooster(List<Boolean> heeftles) {
        heeftLes = heeftles;
        if (heeftles.size() == 0) {
            System.out.println("DagRooster: heeftles size is 0");
            return;
        }
        for (boolean bool : heeftles) {
            System.out.println(bool);
        }
        //TODO Debug
        System.out.println("DagRooster: isDebug: " + Constants.instance().isDebug());
        if (!Constants.instance().isDebug()) {
            if (heeftles.get(0)) {
                lesuren.add(lesuren.size(), new Interval(new DateTime().withTime(8, 0, 0, 0), new DateTime().withTime(9, 0, 0, 0)));
            }
            if (heeftles.get(1)) {
                lesuren.add(lesuren.size(), new Interval(new DateTime().withTime(9, 0, 0, 0), new DateTime().withTime(10, 5, 0, 0)));
            }
            if (heeftles.get(2)) {
                lesuren.add(lesuren.size(), new Interval(new DateTime().withTime(10, 10, 0, 0), new DateTime().withTime(11, 10, 0, 0)));
            }
            if (heeftles.get(3)) {
                lesuren.add(lesuren.size(), new Interval(new DateTime().withTime(11, 15, 0, 0), new DateTime().withTime(12, 15, 0, 0)));
            }
            if (heeftles.get(4)) {
                lesuren.add(lesuren.size(), new Interval(new DateTime().withTime(12, 35, 0, 0), new DateTime().withTime(13, 35, 0, 0)));
            }

            if (heeftles.get(5)) {
                lesuren.add(lesuren.size(), new Interval(new DateTime().withTime(13, 55, 0, 0), new DateTime().withTime(14, 55, 0, 0)));
            }
            if (heeftles.get(6)) {
                lesuren.add(lesuren.size(), new Interval(new DateTime().withTime(15, 0, 0, 0), new DateTime().withTime(16, 0, 0, 0)));
            }
        } else {
            System.out.println("RoosterHandler: Begin DagRooster: " + dt.getHourOfDay() + ":" + dt.getMinuteOfHour());
            if (heeftles.get(0)) {
                lesuren.add(lesuren.size(), new Interval(dt.plusMinutes(2), dt.plusMinutes(3)));
            }
            if (heeftles.get(1)) {
                lesuren.add(lesuren.size(), new Interval(dt.plusMinutes(4), dt.plusMinutes(5)));
            }
            if (heeftles.get(2)) {
                lesuren.add(lesuren.size(), new Interval(dt.plusMinutes(6), dt.plusMinutes(7)));
            }
            if (heeftles.get(3)) {
                lesuren.add(lesuren.size(), new Interval(dt.plusMinutes(8), dt.plusMinutes(9)));
            }
            if (heeftles.get(4)) {
                lesuren.add(lesuren.size(), new Interval(dt.plusMinutes(10), dt.plusMinutes(11)));
            }

            if (heeftles.get(5)) {
                lesuren.add(lesuren.size(), new Interval(dt.plusMinutes(12), dt.plusMinutes(13)));
            }
            if (heeftles.get(6)) {
                lesuren.add(lesuren.size(), new Interval(dt.plusMinutes(14), dt.plusMinutes(15)));
            }
        }
    }
}
