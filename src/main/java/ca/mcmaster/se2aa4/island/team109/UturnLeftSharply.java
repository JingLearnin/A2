package ca.mcmaster.se2aa4.island.team109;

import ca.mcmaster.se2aa4.island.team109.Direction;

import ca.mcmaster.se2aa4.island.team109.UTurn;
public class UturnLeftSharply implements UTurn{
    private int turningStepLeft;
    private Direction curDirection;
    private String action;
    private String dir;

    public UturnLeftSharply(int turningStepLeft, Direction curDirection){
        this.turningStepLeft = turningStepLeft;
        this.curDirection = curDirection;
    }

    public boolean IsStillTurning(){
        if(turningStepLeft==-2 ){
            return false;
        }else{
            return true;
        }
    }
    public void setCommand(String action){
        this.action = action;
    }
    public void setDirection(String dir){
        this.dir = dir;
    }    
    public String getCommand(){
        return action;
    }
    public String getDirection(){
        return dir;
    }   

//7
    public void make_movement(){
        if(turningStepLeft==2|turningStepLeft==3){
            curDirection.changeL();
            setCommand("heading");
            setDirection(curDirection.getDirection());
        }else if(turningStepLeft==7|turningStepLeft==1){
            setCommand("fly");
        }else if(turningStepLeft<=6&&turningStepLeft>=4){
            curDirection.changeR();
            setCommand("heading");
            setDirection(curDirection.getDirection());
        }
        else{
            setCommand("echo");
        }  
    }

    public void recordMovement(){
        turningStepLeft--;
    }
    
    public boolean StopUturning(){
        return false;
    }
}
