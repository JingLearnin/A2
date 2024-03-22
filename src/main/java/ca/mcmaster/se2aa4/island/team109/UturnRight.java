package ca.mcmaster.se2aa4.island.team109;

import ca.mcmaster.se2aa4.island.team109.UTurn;

public class UturnRight implements UTurn{
    private int turningStepLeft;
    private Direction curDirection;
    private String action;
    private String dir;

    public UturnRight(int turningStepLeft, Direction curDirection){
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

    public void make_movement(){
        if(turningStepLeft>=5|turningStepLeft==3){
            curDirection.changeR();
            setCommand("heading");
            setDirection(curDirection.getDirection());
        }else if(turningStepLeft==4|turningStepLeft==2|turningStepLeft==1){
            setCommand("fly");
        }else{
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
