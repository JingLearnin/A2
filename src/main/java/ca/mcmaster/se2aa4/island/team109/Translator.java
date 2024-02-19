package ca.mcmaster.se2aa4.island.team109;

import ca.mcmaster.se2aa4.island.team109.Information;
import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;
public class Translator {

    
    public Information parse(JSONObject result){
        String[] a = {"0","1"};
        a = (result).toString().replaceAll("\"found\":\"", "").replaceAll("range\":", "").replaceAll("\"","").replaceAll("\\{","").replaceAll("\\}","").split(",");
        try {
            return new Information(a[0],a[1]);    
        } catch (Exception e) {
            e.printStackTrace();
            return new Information(a[0]);
            
        }    
        
    }

}
