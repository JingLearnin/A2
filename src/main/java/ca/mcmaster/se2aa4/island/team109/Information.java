package ca.mcmaster.se2aa4.island.team109;
import java.util.ArrayList;
import java.util.Arrays;

public class Information {
    private String[] information;
    private String found = "unknown";
    private String distance = "unknown";
    private String creeks = "unknown";
    private ArrayList<String> biomes = new ArrayList<>();
    private boolean findCreek = false;
    private boolean findSites = false;
    private String sites = "unknown";

    public Information(){
    }
    
    public Information(String[] information){
        this.information = information;
        biomes.add("unknown");
    }

    public String getResult(){
        return found;
    }
    public String getDistance(){
        return distance;
    }

    public ArrayList<String> getBiomes(){
        return biomes;
    }
    public String getCreeks(){
        if(findCreek){
            return creeks;
        }
        else{return "unknown";}
    }
    public String getSites(){
        if(findSites){
            return sites;
        }
        else{return "unknown";}
    }

    public void decode(){
        for ( String i : information ) {            
            if(i.contains("biomes")){
                biomes.set(0, i.replaceAll("\"biomes\":", "").replaceAll("\\[","").replaceAll("\\]","").replaceAll("\"",""));

            }
            
            if(i.contains("\"]")){
                String temp = i.replaceAll("\\]","").replaceAll("\"","");
                if(temp.length()>10){
                    if(i.contains("creeks")){
                        findCreek = true;
                        creeks = i.replaceAll("creeks:", "").replaceAll("\\[","").replaceAll("\\]","");
                    }

                    if(i.contains("sites")){
                        findSites = true;
                        sites = i.replaceAll("sites:", "").replaceAll("\\[","").replaceAll("\\]","");
                    }
                }
                else{
                    biomes.add(i.replaceAll("\\]","").replaceAll("\"",""));
                }
            } 
            



            
            if(i.contains("found")){
                found = i.replaceAll("\"found\":\"", "").replaceAll("\"","");
            }
            if(i.contains("range")){
                distance = i.replaceAll("\"range\":", "");
            }

        }
        
    }
}
