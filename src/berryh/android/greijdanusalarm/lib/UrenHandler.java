package berryh.android.greijdanusalarm.lib;

import android.util.Log;
import berryh.android.greijdanusalarm.Service.DownloadTask;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry Holtrust on 17-3-14.
 */
public class UrenHandler {

    private static UrenHandler INSTANCE = new UrenHandler();
    private boolean isVMinuten = false;
    private File dataDir = Constants.instance().getGreijdanusalarm().getCacheDir();
    private File vMinutenFile = new File(dataDir, "50minuten.json");
    private List<String> dagen = null;

    public static UrenHandler instance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public void setupUren() {
        if (vMinutenFile.exists()) {
            if (vMinutenFile.delete()) {
                System.out.println("UrenHandler: setupUren: Cache Cleared");
            }
        }
        final DownloadTask dTask = new DownloadTask();
        dTask.execute("https://dl.dropboxusercontent.com/u/131330503/GreijdanusAlarm/50minuten.json", vMinutenFile.getPath());
        try {
            dTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("GreijdanusAlarm", "Roosterdata could not be downloaded! This is an ERROR!");
        }
        if (vMinutenFile.exists()) {
            System.out.println("UrenHandler: vMinuten File Exists!");
        } else {
            System.out.println("UrenHandler: vMinuten File Does Not Exist!");
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(vMinutenFile);
            StringBuilder builder = new StringBuilder();
            int ch;
            while ((ch = fis.read()) != -1) {
                builder.append((char) ch);
            }
            String jsonDagen = builder.toString();

            System.out.println("UrenHandler: jsonSettings String: " + jsonDagen);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonDagen);
            dagen = (ArrayList<String>) json.get("dagen");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("UrenHandler: getDT Format: " + Constants.instance().getDtFormatter().print(Constants.instance().getDt()));
        for (int i = 0; i < dagen.size(); i++) {
            System.out.println("UrenHandler: vMinuten.get(" + i + "): " + dagen.get(i));
            if (dagen.get(i).equals(Constants.instance().getDtFormatter().print(Constants.instance().getDt()))) {
                isVMinuten = true;
            }
            System.out.println(isVMinuten);
            if (isVMinuten) break;
        }
        Constants.instance().setVMinuten(isVMinuten);
    }
}
