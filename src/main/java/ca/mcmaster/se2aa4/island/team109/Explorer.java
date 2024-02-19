package ca.mcmaster.se2aa4.island.team109;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private static int stage =1;
    private Drone drone = new Drone();
    Translator t = new Translator();
    Information i;
//    AreaMap m = new AreaMap();
//  w3 version for competitioni

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        drone.execution(decision);
        /*
        decision.put("action", "stop"); 
        logger.info("** Decision: {}",decision.toString());
        */
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        i = t.parse(extraInfo);
        logger.info("Additional information received: {}", extraInfo);
        logger.info("THIS is result "+i.getResult()+" This is distance"+i.getDistance());
        logger.info(extraInfo.toString());
        drone.setInformation(i);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
