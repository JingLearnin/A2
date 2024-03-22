package ca.mcmaster.se2aa4.island.team109;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Detection{


    private boolean IsdetectedR = false;
    private boolean IsdetectedL = false;
    private boolean IsdetectedF = false;
    private boolean IsdetectedAll = false;
    Direction currentDir;
    Direction detectDir;
    private final Logger logger = LogManager.getLogger();

    
    
    

    public Detection(Direction dir){
        detectDir = dir;
        currentDir = new Direction(dir.getDir());

    }

    public void detect(){
    if(!IsdetectedL){
        detectDir.changeL();
        this.IsdetectedL = true;
    }else if(!IsdetectedF){
        IsdetectedF = true;
    }
    else if(!IsdetectedR){
        detectDir.changeR();
        this.IsdetectedR = true;
        this.IsdetectedAll = true; //at this point, three direction has all been detected
        this.IsdetectedR = false;
        this.IsdetectedL = false;
        this.IsdetectedF = false; 
        }
   // detectDir = currentDir; 
    }
 
    public String getDetectDir(){
        return detectDir.getDirection();
    }
    
    public String getCurrentDir(){
        return currentDir.getDirection();
    }
    
    public void resetDir(){
        detectDir.resetDir(currentDir.getDir());
    }

    public boolean getIDA(){
        return IsdetectedAll;
    }
    public void resetIAD(){
        IsdetectedAll = false;
    }
}