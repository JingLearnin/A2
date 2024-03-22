package ca.mcmaster.se2aa4.island.team109;
import java.io.StringReader;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;


public class Drone {
    private final Logger logger = LogManager.getLogger();
    private CommandDecider commander1;
    private Direction currentDir;
    private int[] currentLocation = {0,0};
    private Information lastInformation = new Information();
    private DroneBattery batteryLevel;
    private InformationCollection infoSet = new InformationCollection();
    CoordinatesSystem coordinatesSystem;
    //private DroneNavigation droneNavigation;
    
    public Drone(Direction currentDir, DroneBattery batteryLevel){
        this.currentDir = currentDir;
        this.batteryLevel = batteryLevel;
        commander1 = new Stage1(currentDir,infoSet);
        //droneNavigation = new DroneNavigation();
        this.coordinatesSystem = new CoordinatesSystem();
        infoSet.setInitialDir(currentDir.getDirection());
    }

    public void executionStage1(JSONObject commander){
        if(commander1.changeStage()){
            commander.put("action", "scan");
            commander1 = commander1.changingStage(currentDir,infoSet); 
            logger.info("** Decision: {}",commander.toString());    
        }
        else{
            commander1.algorithem(lastInformation);
            logger.info("found "+lastInformation.getResult()+" |distance "+lastInformation.getDistance());
            String[] command = commander1.getcommand();
            if(command[0].equals("fly")|command[0].equals("stop")|command[0].equals("scan")){
                
                if (command[0].equals("fly")){
                    ///////////////////////////////////////////////
                    coordinatesSystem.moveFront(currentDir);
                    coordinatesSystem.updateCurrentCoordinate();
                    coordinatesSystem.changeCoordinate();
                    //coordinatesSystem.printPath();

                }
                commander.put("action", command[0]); 
                logger.info("** Decision: {}",commander.toString());



            }else if(command[0].equals("echo")|command[0].equals("heading")){
                JSONObject parameters = new JSONObject();

                if (currentDir.getDirection().equals("N")&& command[1].equals("east")| currentDir.getDirection().equals("E")&& command[1].equals("south")| currentDir.getDirection().equals("S")&& command[1].equals("west")| currentDir.getDirection().equals("W")&& command[1].equals("north")){
                    
                    coordinatesSystem.MoveRightFirst(currentDir);
                    //coordinatesSystem.printPath();
                }

                else if (currentDir.getDirection().equals("N")&& command[1].equals("west") | currentDir.getDirection().equals("E")&& command[1].equals("north")| currentDir.getDirection().equals("S")&& command[1].equals("east")|currentDir.getDirection().equals("W")&& command[1].equals("south")){

                    coordinatesSystem.MoveLeftFirst(currentDir);
                    
                    //coordinatesSystem.printPath();
                    
                }
          


  


                
                parameters.put("direction",command[1]);
                commander.put("action", command[0]); 
                commander.put("parameters",parameters);
                logger.info("** Decision: {}",commander.toString());
            }
        }
        batteryLevel.LowBatteryReturn(commander);

    }

    public void executionStage2(JSONObject commander){
        commander1.algorithem(lastInformation);
        commander.put("action", "stop");
        logger.info("** Decision: {}",commander.toString());
    }




    public void executionStage3(JSONObject commander){
        commander.put("action", "stop");
    }

    public void setInformation(Information i){
        this.lastInformation = i;
        if(!i.getCreeks().equals("unknown")){
            infoSet.setCreekID(i.getCreeks());
        }
        if(!i.getSites().equals("unknown")){
            infoSet.setSitesID(i.getSites());
        }
        logger.info("*********CreekID |"+infoSet.getCreekSet()+"|********SiteID |"+infoSet.getSites()+"********");
    }

    


    public CommandDecider getStage(){
        return commander1;
    }



    public void GetLastCast(int cost){
        batteryLevel.countBudget(cost);
    }

    public String GetDirection(){
        return currentDir.getDirection();
    }

}