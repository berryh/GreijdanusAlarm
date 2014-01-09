package berryh.android.greijdanusalarm.roosterTypes;

/**
 * Created by Berry Holtrust on 9-1-14.
 */
public class RoosterTemplateLesUur {

    protected RoosterTemplateTimeHelper beginLesUur;
    protected RoosterTemplateTimeHelper eindLesUur;

    public RoosterTemplateLesUur(RoosterTemplateTimeHelper beginTijd, RoosterTemplateTimeHelper eindTijd){
        this.beginLesUur = beginTijd;
        this.eindLesUur = eindTijd;
    }

    public RoosterTemplateTimeHelper getBeginLesUur(){
        return beginLesUur;
    }

    public RoosterTemplateTimeHelper getEindLesUur(){
        return eindLesUur;
    }



}
