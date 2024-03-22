package ca.mcmaster.se2aa4.island.team109;

public class ScanedLocation {

    private String name; // to store the creeek and emergency site

    private int x;
    
    private int y; 

    public ScanedLocation(String name, int x, int y){

        this.name=name;

        this.x = x;

        this.y = y ; 




    }


    public int getX(){
        return x;


    }


    public int getY(){

        return y;


    }

    public String getName(){

        return name;


    }

    
}
