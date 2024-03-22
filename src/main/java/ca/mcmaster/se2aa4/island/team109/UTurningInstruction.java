package ca.mcmaster.se2aa4.island.team109;

import ca.mcmaster.se2aa4.island.team109.InformationCollection;

public class UTurningInstruction {
    InformationCollection infoSet;
    private boolean IsLeftTurn;
    

    public UTurningInstruction(InformationCollection infoSet){
        this.infoSet=infoSet;
    }

    public void IsLeftTurn(){
        if(infoSet.getS1TurningDir().equals("unknown")){
            

        }else{
            switch (infoSet.getInitialDir()) {
                case "E":
                    if(infoSet.getS1TurningDir()=="N"){
                        IsLeftTurn=false;
                    }else{
                        IsLeftTurn=true;
                    }
                    break;
                case "W":
                    if(infoSet.getS1TurningDir().equals("S")){
                        IsLeftTurn=false;
                    }else{
                        IsLeftTurn=true;
                    }                   
                    break;
            
                case "N":
                    if(infoSet.getS1TurningDir().equals("W")){
                        IsLeftTurn=false;
                    }else{
                        IsLeftTurn=true;
                    }
                    
                    break;
            
                case "S":
                    if(infoSet.getS1TurningDir().equals("E")){
                        IsLeftTurn=false;
                    }else{
                        IsLeftTurn=true;
                    }
                    break;
                    
                default:
                    IsLeftTurn=true;
                    break;
            }
    
        }
    }

    public Boolean getWhereToTurn(){
        return IsLeftTurn;
    }

}
