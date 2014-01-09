package berryh.android.greijdanusalarm.roosterTypes;

/**
 * Created by Berry Holtrust on 9-1-14.
 */
public class RoosterTemplateDag {

    protected RoosterTemplateLesUur[] uren;
    protected String roosterDag;

    public RoosterTemplateDag(RoosterTemplateLesUur[] uren, String lesdag){

        this.uren = uren;
        this.roosterDag = lesdag;

    }

    public String getDag(){
        return this.roosterDag;
    }

    public RoosterTemplateTimeHelper getDagBeginTijd(){
        return this.uren[0].getBeginLesUur();
    }

    public RoosterTemplateTimeHelper getDagEindTijd(){
        return this.uren[this.uren.length - 1].getEindLesUur();
    }
}
