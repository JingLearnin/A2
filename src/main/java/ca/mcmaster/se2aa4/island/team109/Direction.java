package ca.mcmaster.se2aa4.island.team109;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Direction {

    private final Logger logger = LogManager.getLogger();

    DirectionEnum currentDirection; // all share the same currentDirection 
    
    //private DroneProtection droneProtection;

  

    public enum DirectionEnum {

        NORTH(0),
        EAST(1),
        SOUTH(2),
        WEST(3);

        private final int dir;

        private DirectionEnum(int dir) {
            this.dir = dir;
        }

        public int getDir() {
            return dir;
        }

        public DirectionEnum turnRight() {
            switch (this) {
                case NORTH:
                    return EAST;
                case EAST:
                    return SOUTH;
                case SOUTH:
                    return WEST;
                case WEST:
                    return NORTH;
                default:
                    throw new IllegalStateException("Unexpected value: " + this);
            }
        }

        public DirectionEnum turnLeft() {
            switch (this) {
                case NORTH:
                    return WEST;
                case EAST:
                    return NORTH;
                case SOUTH:
                    return EAST;
                case WEST:
                    return SOUTH;
                default:
                    throw new IllegalStateException("Unexpected value: " + this);
            }
        }

        public DirectionEnum uTurn() {
            return turnLeft().turnLeft();

        }
    }
    
    public Direction() {

        this.currentDirection = DirectionEnum.WEST; 
    }
    public Direction(DirectionEnum enum1) {
        this.currentDirection = enum1; 

        //this.droneProtection = new DroneProtection();
    }

    public void changeL() { // Turn left
        this.currentDirection = this.currentDirection.turnLeft();
 


    }

    public void changeR() { // Turn right
        this.currentDirection = this.currentDirection.turnRight();

    }

    public void Uturn() { // Make a U-turn
        this.currentDirection = this.currentDirection.uTurn();
    }

    public String getDirection() { 
        switch (currentDirection) {
            case NORTH:
                return "N";
            case EAST:
                return "E";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            default:
                return "Unknown";
        }
    }

    public void resetDir(DirectionEnum dir) { // Reset current direction
        currentDirection = dir;
    }

    public DirectionEnum getDir() { // Get current direction as an enum
        return currentDirection;
    }

    public DirectionEnum valueOf(String s){
        switch (s) {
            case "E":
                return DirectionEnum.EAST;
            case "N":
                return DirectionEnum.NORTH;

            case "S":
                return DirectionEnum.SOUTH;
            case "W":
                return DirectionEnum.WEST;
            default:
                return DirectionEnum.EAST;
        }

    }
}
