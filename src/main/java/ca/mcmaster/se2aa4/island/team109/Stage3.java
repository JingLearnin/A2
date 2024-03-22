package ca.mcmaster.se2aa4.island.team109;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team109.Direction;

public class Stage3 implements CommandDecider{

    private String[] command = {"stop", "EAST"};
    private final Logger logger = LogManager.getLogger();


    public Stage3(Direction curDirection, InformationCollection info){
        
    }

    public boolean changeStage(){
        return false;
    }
    public String[] getcommand(){
        return command;
    }

    public void setcommand(String cmd){command[0]=cmd;}
    public void setcommand(String cmd1, String cmd2){
        command[0]=cmd1;
        command[1]=cmd2;
    }

    public void algorithem(Information information){
        logger.info("Creeks "+information.getCreeks()+" | Biomes "+information.getBiomes()+" | Sites "+information.getSites());
    }
    
    public CommandDecider changingStage(Direction dir, InformationCollection infoset){
        return new Stage3(dir,infoset);
    }
}