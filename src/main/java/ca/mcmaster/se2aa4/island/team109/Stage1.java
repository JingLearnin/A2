package ca.mcmaster.se2aa4.island.team109;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class Stage1 implements CommandDecider{
    private final Logger logger = LogManager.getLogger();

    private String[] command = {"echo", "EAST"};
    private Detection rader;
    private int count=-1;
    private boolean findIsland = false;
    private boolean reachIsland = false;
    private Direction dir;
    private String turningDirection="unknown"; //will be use in stage2
    private InformationCollection infoset;
 

    public Stage1(Direction currentdir, InformationCollection infoset){
        rader=new Detection(currentdir);
        dir = currentdir;
        this.infoset = infoset;
    }

    public boolean changeStage(){
        return reachIsland;
    }

    public CommandDecider changingStage(Direction dir,InformationCollection infoset){
        infoset.SetUTurningInstruction();
        return new Stage2(dir,infoset);
    }
    
    public void setcommand(String cmd){command[0]=cmd;}
    public void setcommand(String cmd1, String cmd2){
        command[0]=cmd1;
        command[1]=cmd2;
    }

    public String[] getcommand(){
        return command;
    }

    public void algorithem(Information record){
        String found = record.getResult();
        if(findIsland&count!=0){
            setcommand("fly");
            count--;
        }else if (count ==0){
            reachIsland = true;
            setcommand("scan");
            
            
        }
        else{
            if(found.equals("GROUND")){
                logger.info(record.getDistance());
                count = Integer.parseInt(record.getDistance());
                if(rader.getCurrentDir().equals(rader.getDetectDir())){
                    setcommand("fly");
                }
                else{
                    setcommand("heading",rader.getDetectDir());
                    turningDirection = rader.getDetectDir();
                    infoset.setS1TurningDir(turningDirection);
                }

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