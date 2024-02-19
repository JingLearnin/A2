package ca.mcmaster.se2aa4.island.team109;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Direction{

    private int direction;
    //the direction of Maza:
    // up is 0, right is 1, down is 2 and left is 3;
    //since the default positioin of entrance and exit are left and right correspondingly, so default direction is right which is 1


    public Direction(){
        direction = 1;      //this constructor is the situation which start from East;
    }

    public void changeL(){          // make a left turn on direction 
        direction-=1;
        if(direction<0)
        direction+=4;
        direction%=4;
    }

    public void changeR(){          // make a right turn on direction
        direction+=1;
        direction%=4;
    }

    public void Uturn(){            // make a U turn(180 degree turn or turn arround) on direction
        direction-=2;               //Actually it justs make left turn twice
        if(direction<0)
        direction+=4;
        direction%=4;
    }

    public String getDirection(){
        //method to get current direction
        switch (direction) {
            case 0:
                return "N";
            case 1:
                return "E";
            case 2:
                return "S";
            case 3:
                return "W";
            default:
                throw new IllegalArgumentException();
        }
    }
    public void resetDir(int dir){
        direction = dir;
    }

    public int getDir(){
        return direction;
    }
}