package ca.mcmaster.se2aa4.island.team109;

public class Information {
    private String result;
    private String Distance = "Shit";
    public String[] a;

    public Information(String s, String i){
        result = s;
        Distance = i;
        
    } 
    public Information(String s){
        result = s;
    } 

    public String getResult(){
        return result;
    }
    public String getDistance(){
        return Distance;
    }

   // public int getDistance(){
   //     return Distance;
    //}
}
