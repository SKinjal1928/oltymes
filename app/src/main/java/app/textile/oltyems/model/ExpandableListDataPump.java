package app.textile.oltyems.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List technology = new ArrayList();
        technology.add("Beats sued for noise-cancelling tech");

        List entertainment = new ArrayList();
        entertainment.add("Goldfinch novel set for big screen");

        List science = new ArrayList();
        science.add("Eggshell may act like sunblock");

        expandableListDetail.put("TECHNOLOGY NEWS", technology);
        expandableListDetail.put("ENTERTAINMENT NEWS", entertainment);
        expandableListDetail.put("SCIENCE & ENVIRONMENT NEWS", science);
        return expandableListDetail;
    }
}
