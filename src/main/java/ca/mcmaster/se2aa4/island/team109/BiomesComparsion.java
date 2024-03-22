package ca.mcmaster.se2aa4.island.team109;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BiomesComparsion {
    private final Logger logger = LogManager.getLogger();
    private boolean IsOcean;
    private ArrayList<String> biomes;
    
    public BiomesComparsion(ArrayList<String> biomes){
        this.biomes = biomes;
    }

    public void CompareOCEAN(){
        
        for(String terrin : biomes){
            if(!terrin.equals("OCEAN")){
                IsOcean = false;
                break;
            }else{
                IsOcean = true;
            } 
        }

        logger.info("This is scan |"+biomes.toString()+"|~~~~~");
        logger.info("This is result |"+getIsOcean()+"| ~~~~~");
        
    }

    public boolean getIsOcean(){
        return IsOcean;
    }
}
