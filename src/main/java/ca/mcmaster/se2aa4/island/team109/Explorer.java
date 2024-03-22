package ca.mcmaster.se2aa4.island.team109;

import java.io.StringReader;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import ca.mcmaster.se2aa4.island.team109.CoordinatesSystem;
import ca.mcmaster.se2aa4.island.team109.Direction.DirectionEnum;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private DroneBattery batteryLevel = new DroneBattery();
    private Direction currentDir = new Direction();
    Translator t = new Translator();
    Information i;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        t.storeValue(t.parseInitialInfo(info));
        this.batteryLevel.setInitialBudget(t.getBudget());
        currentDir.resetDir(currentDir.valueOf(t.getInitalDir()));
        logger.info(t.getInitalDir());
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
        drone = new Drone(currentDir,this.batteryLevel);
        logger.info("here is battery and direction"+this.batteryLevel.getBudget()+" | "+currentDir.getDirection());
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();

        if (drone.getStage() instanceof Stage1) {
            drone.executionStage1(decision);
        } else if (drone.getStage() instanceof Stage2) {
            drone.executionStage1(decision);
        } else if (drone.getStage() instanceof Stage3) {
            drone.executionStage3(decision);
        } else {
            decision.put("action", "stop"); 
            logger.info("** Decision: {}",decision.toString());
        }
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        drone.GetLastCast(cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        i = t.parse(extraInfo);
        logger.info("Additional information received: {}", extraInfo);
        logger.info(extraInfo.toString());
        i.decode();
        logger.info("Creeks "+i.getCreeks()+" | Biomes "+i.getBiomes().toString()+" | Sites "+i.getSites()+" !!!!!!!!!!");
        drone.setInformation(i);
        logger.info("~~~~~current direction is |"+drone.GetDirection()+"| ~~~~~~~~");
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}