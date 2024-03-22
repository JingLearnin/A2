package ca.mcmaster.se2aa4.island.team109;
import java.util.ArrayList;
import java.util.Arrays;

import ca.mcmaster.se2aa4.island.team109.CoordinatesSystem;

public class InformationCollection{

    private ArrayList<String> CreeksID = new ArrayList<>();
    private ArrayList<CoordinatesSystem> coordinates = new ArrayList<>();
    public String siteID="unknown";
    private String initalDir;
    private String Stage1TurningDir = "unknown";
    UTurningInstruction FirstUturnDirection = new UTurningInstruction(this);

    public InformationCollection(){}
    
    public void setCreekID(String id){
        if(!CreeksID.contains(id))
            CreeksID.add(id);
    }

    
    public void setCoordinates(CoordinatesSystem xy){
        coordinates.add(xy);
    }

    public void setSitesID(String id){
        siteID = id;
    }

    public ArrayList<String> getCreekSet(){
        return CreeksID;
    }

    public String getSites(){
        return siteID;
    }

    public void setInitialDir(String dir){
        initalDir = dir;
    }
    public void setS1TurningDir(String dir){
        Stage1TurningDir = dir;
    }

    public String getInitialDir(){
        return initalDir;
    }

    public String getS1TurningDir(){
        return Stage1TurningDir;
    }
    
    public Boolean ShouldDroneLeftTurn(){
        return FirstUturnDirection.getWhereToTurn();
    }

    public void SetUTurningInstruction(){
        FirstUturnDirection.IsLeftTurn();
    }
}