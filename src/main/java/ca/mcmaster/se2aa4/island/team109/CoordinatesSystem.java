package ca.mcmaster.se2aa4.island.team109;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class CoordinatesSystem extends Direction {
    private final Logger logger = LogManager.getLogger();
    private ArrayList<int[]> path;
    private ArrayList<ScanedLocation> importantLocation; // to store the class of ScanedLocation, including the name and x and y 
    private int x;
    private int y;
    private int[] currentCoordinate = new int[2];
    private Direction direction; // Use composition for Direction

    public CoordinatesSystem() {
        //this.direction = new Direction(); 
        path = new ArrayList<>();
        this.x = 0;
        this.y = 0;
    }

    public ArrayList<int[]> getPath() {
        return path;
    }

    public void printPath() {
        for (int[] step : path) {
            logger.info("(" + step[0] + ", " + step[1] + ")");
        }
    }

    public void changeCoordinate() {
        path.add(new int[]{x, y}); // Add a copy of the current coordinates to the path
    }


    public void moveFront(Direction dir) {
        switch (dir.getDirection()) {
            case "N":
                y++;
                break;
            case "E":
                x++;
                break;
            case "S":
                y--;
                break;
            case "W":
                x--;
                break;
        }
        // updateCurrentCoordinate();
        // changeCoordinate(); // Update path after moving
    }

    public void updateCurrentCoordinate() {
        currentCoordinate[0] = x;
        currentCoordinate[1] = y;
    }

    public void moveRight(Direction dir) {
        moveFront(dir);

        switch (dir.getDirection()) {
            case "N":
                x++;
                break;
            case "E":
                y--;
                break;
            case "S":
                x--;
                break;
            case "W":
                y++;
                break;
        }
        
    }

    public void moveLeft(Direction dir) {
        moveFront(dir);
        switch (dir.getDirection()) {
            case "N":
                x--;
                break;
            case "E":
                y++;
                break;
            case "S":
                x++;
                break;
            case "W":
                y--;
                break;
        }
        
    }

    public void AddScanedLocation(ScanedLocation location){

        importantLocation.add(location);

    }


    public void MoveRightFirst(Direction currentDir){

        moveRight(currentDir);
        updateCurrentCoordinate();
        changeCoordinate();


    }

    public void MoveLeftFirst(Direction currentDir){

        moveLeft(currentDir);
        updateCurrentCoordinate();
        changeCoordinate();


    }

    









    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
