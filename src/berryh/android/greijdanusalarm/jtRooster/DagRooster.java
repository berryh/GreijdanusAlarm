package berryh.android.greijdanusalarm.jtRooster;

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

    public DagRooster(List<Boolean> heeftles) {
        heeftLes = heeftles;
        if (heeftles.get(0)) {
            lesuren.add(0, new Interval(new DateTime().withTime(8, 0, 0, 0), new DateTime().withTime(9, 0, 0, 0)));
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
    }
}
