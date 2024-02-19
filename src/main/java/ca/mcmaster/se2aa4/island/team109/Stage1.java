package ca.mcmaster.se2aa4.island.team109;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Stage1 implements CommandDecider{
    private final Logger logger = LogManager.getLogger();

    private String[] command = {"echo", "EAST"};
    private String location = "OCEAN";
    private Detection rader;
    private int count=-1;
    private boolean findIsland = false;
    private boolean reachIsland = false;
 

    public Stage1(Direction currentdir){
        rader=new Detection(currentdir);
    }

    public boolean changeStage(){
        return reachIsland;
    }
    
    public void setcommand(String cmd){command[0]=cmd;}
    public void setcommand(String cmd1, String cmd2){
        command[0]=cmd1;
        command[1]=cmd2;
    }

    public String[] getcommand(){
        return command;
    }

    public void algorithem(String[] record){
        if(findIsland&count!=0){
            setcommand("fly");
            count--;
        }else if (count ==0){
            reachIsland = true;
            setcommand("scan");
            
            
        }
        else{
            if(record[0].equals("GROUND")){
                count = Integer.parseInt(record[1]);
                setcommand("heading",rader.getDetectDir());
                this.findIsland = true;
            }else if(rader.getIDA()){
               // setcommand("fly");
               setcommand("fly");
               rader.resetIAD();
               logger.info("Now is facing"+ rader.getDetectDir());
            }
            else{
                rader.resetDir();
                rader.detect();
                setcommand("echo",rader.getDetectDir());
            }
        }



        
        
    }

    public void testing(){
       
        
        logger.info("testing testing");
        logger.info("___________");
    }

}