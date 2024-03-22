package ca.mcmaster.se2aa4.island.team109;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Translator {
    private final Logger logger = LogManager.getLogger();
    int initial_budget;
    String initial_direction;
    
    public Information parse(JSONObject result){
        
        String[] information;
        information = (result).toString().replaceAll("\\{","").replaceAll("\\}","").toString().split(",");
        logger.info(Arrays.toString(information));
        return new Information(information);
    }

    public String[] parseInitialInfo(JSONObject info){
        String[] information = info.toString(2).replaceAll("\\{","").replaceAll("\\}","").toString().split(",");
        return information;
    }

    public void storeValue(String[] information){
        for ( String i : information ) {
            if(i.contains("budget")){
                initial_budget =Integer.parseInt(i.replaceAll("\"budget\":", "").replaceAll("\n", "").replaceAll(" ", ""));
            }
            if(i.contains("heading")){
                initial_direction = i.replaceAll("\"heading\":", "").replaceAll("\n", "").replaceAll(" ", "").replaceAll("\"","");
            }
        }
    }

    public int getBudget(){
        return initial_budget;
    }
    public String getInitalDir(){
        return initial_direction;
    }
}
