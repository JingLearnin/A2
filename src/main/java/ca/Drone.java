package ca.mcmaster.se2aa4.island.team109;
import java.io.StringReader;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.team109.Direction;

public class Drone {
    private final Logger logger = LogManager.getLogger();
    CommandDecider commander1;
    Direction currentDir;
    private int[] currentLocation = {0,0};
    String[] lastInformation = {"",""};

    
;
    

    public Drone(){
        currentDir = new Direction();
        commander1 = new Stage1(currentDir);
    }

    public void execution(JSONObject commander){
        if(commander1.changeStage()){
            commander.put("action", "stop");       
        }
        else{
            commander1.algorithem(lastInformation);
            logger.info(Arrays.toString(lastInformation));
            String[] command = commander1.getcommand();
            if(command[0]=="fly"|command[0] =="stop"|command[0]=="scan"){
                commander.put("action", command[0]); 
                logger.info("** Decision: {}",commander.toString());
            }else if(command[0]=="echo"|command[0] =="heading"){
                JSONObject parameters = new JSONObject();
                parameters.put("direction",command[1]);
                commander.put("action", command[0]); 
                commander.put("parameters",parameters);
                logger.info("** Decision: {}",commander.toString());
            }
        }

        
    }

    public void setInformation(Information i){
        lastInformation[0]=i.getResult();
        lastInformation[1]=i.getDistance();
    }
    





}
