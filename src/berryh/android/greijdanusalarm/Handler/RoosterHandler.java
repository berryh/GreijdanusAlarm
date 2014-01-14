package berryh.android.greijdanusalarm.Handler;

import berryh.android.greijdanusalarm.Enums.EnumDagen;
import berryh.android.greijdanusalarm.Roosters.DagRoosterBase;
import berryh.android.greijdanusalarm.Roosters.UrenBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Berry Holtrust on 10-1-14.
 */
public class RoosterHandler {

    public HashMap<EnumDagen, DagRoosterBase> setupRooster() {
        HashMap<EnumDagen, DagRoosterBase> weekRooster = new HashMap<EnumDagen, DagRoosterBase>();

        List<Boolean> maandagHeeftles = new ArrayList<Boolean>(Arrays.asList(false, true, true, true, true, true, true));
        DagRoosterBase maandag = new DagRoosterBase(60, maandagHeeftles, EnumDagen.MAANDAG);

        List<Boolean> vrijdagHeeftles = new ArrayList<Boolean>(Arrays.asList(false, true, true, true, true, true, true));
        DagRoosterBase vrijdag = new DagRoosterBase(60, vrijdagHeeftles, EnumDagen.VRIJDAG);

        weekRooster.put(EnumDagen.MAANDAG, maandag);
        weekRooster.put(EnumDagen.VRIJDAG, vrijdag);

        return weekRooster;
    }

    public String getCurrentTijd() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }

    public int getCurrentLesuur(DagRoosterBase dagrooster) {
        String huidigeTijd = getCurrentTijd();
        Calendar tijd = Calendar.getInstance();
        try {
            tijd.setTime(new SimpleDateFormat("HH:mm").parse(huidigeTijd));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        for (int uur = 0; uur < dagrooster.uren.size(); uur++) {
            UrenBase lesuur = null;
            try {
                lesuur = dagrooster.uren.get(uur);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if (lesuur == null) {
                continue;
            }
            if ((lesuur.getBegin().equals(tijd) || lesuur.getBegin().before(tijd)) && (lesuur.getEind().equals(tijd) || lesuur.getEind().after(tijd))) {
                return uur;
            }
        }


        return 0;
    }

}
