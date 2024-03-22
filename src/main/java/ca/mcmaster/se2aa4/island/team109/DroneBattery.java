package ca.mcmaster.se2aa4.island.team109;
import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;

public class DroneBattery {
    private int budget;
    private JSONObject info;
    private String[] information;
    final Logger logger = LogManager.getLogger();
    //String direction;
    
    
    public DroneBattery(){
    }

    public void setInitialBudget(int budget){
        this.budget = budget;
    }

    public int getBudget(){
        return budget;
    }

    public void countBudget(int cost){
        budget -= cost;
        logger.info("The drone has batterylevel "+ budget +" left~~~");
    }

    public void LowBatteryReturn(JSONObject commander){
        //5460
        if(budget<1600){
            commander.put("action", "scan");
            logger.info("** Final Decision: {} due to low battery",commander.toString());
        }
    }
}