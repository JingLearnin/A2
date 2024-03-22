package ca.mcmaster.se2aa4.island.team109;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;





public class Stage2 implements CommandDecider{

    private String[] command = {"stop", "EAST"};
    private final Logger logger = LogManager.getLogger();
    private boolean IsScan = true;
    private Direction curDirection;
    private int GroundCount=-1;
    private int OutofRangeCount = 100;
    private boolean DoingFlying = false;
    private boolean DoingTurning = false;
    private int turningStepLeft = 0;
    private UTurn turn;
    private boolean ShouldDroneLeftTurn;


    public Stage2(Direction curDirection, InformationCollection info){
        this.curDirection = curDirection;
        ShouldDroneLeftTurn = info.ShouldDroneLeftTurn();
    }

    public boolean changeStage(){
        return false;
    }
    public String[] getcommand(){
        return command;
    }

    public void setcommand(String cmd){command[0]=cmd;}
    public void setcommand(String cmd1, String cmd2){
        command[0]=cmd1;
        command[1]=cmd2;
    }

    public void algorithem(Information information){
        if(DoingFlying){
            if(GroundCount==0){
                DoingFlying = false;
                setcommand("scan");
                IsScan = true;
            }else{
                setcommand("fly");
                GroundCount--;
            }
        }
        else if(DoingTurning){
            if(information.getResult().equals("OUT_OF_RANGE")){
                setcommand("stop");
            }else{
                if(IsScan){
                    turn.make_movement();
                    if(turn.getCommand().equals("heading")){
                        setcommand("heading", turn.getDirection());
                    }else{
                        setcommand(turn.getCommand());
                    }
                    turn.recordMovement();
                    DoingTurning = turn.IsStillTurning();
                    IsScan=!IsScan;
                }else{
                    setcommand("scan");
                    IsScan=!IsScan;
                }
            }
            


        }
        else{
            if(information.getResult().equals("GROUND")){
                logger.info(information.getDistance());
                GroundCount = Integer.parseInt(information.getDistance());
                setcommand("fly");
                DoingFlying =true;
            }
            else if(information.getResult().equals("OUT_OF_RANGE")){
                logger.info("Start turning process");  
                OutofRangeCount = Integer.parseInt(information.getDistance());
                if(ShouldDroneLeftTurn&OutofRangeCount>3){//going to make Uturn toward Left
                    turningStepLeft = 6;
                    turn = new UturnLeft(turningStepLeft,curDirection);
                    curDirection.changeR();

                }else if(!ShouldDroneLeftTurn&OutofRangeCount>3){//going to make Uturn toward right
                    turningStepLeft = 6;
                    turn = new UturnRight(turningStepLeft,curDirection);
                    curDirection.changeL();
                }else if(ShouldDroneLeftTurn){//Left U turn sharply
                    turningStepLeft = 7;
                    turn = new UturnLeftSharply(turningStepLeft,curDirection);
                    curDirection.changeR();
                }else if(!ShouldDroneLeftTurn){
                    turningStepLeft = 7;
                    turn = new UturnRightSharply(turningStepLeft,curDirection);
                    curDirection.changeL();
                }
                setcommand("heading", curDirection.getDirection());
                DoingTurning = true;
                IsScan =false;
                ShouldDroneLeftTurn = !ShouldDroneLeftTurn;

            }
            else{
                BiomesComparsion comparator = new BiomesComparsion(information.getBiomes());
                comparator.CompareOCEAN();
                //Arrays.stream(information.getBiomes()).allMatch(biomes -> biomes.equals("OCEAN"))
                if(comparator.getIsOcean()){
                    setcommand("echo",curDirection.getDirection());
                }
                else if(IsScan){
                    setcommand("fly");
                    IsScan = !IsScan;           
                }else {
                    setcommand("scan");
                    IsScan = !IsScan; 
                }
                //Arrays.stream(information.getBiomes()).forEach(biome -> logger.info("Biome: " + biome +" The condition result "+information.getBiomes().equals(OnlyOcean) ));
            }
        }
        

        logger.info("~~~This is echo |"+information.getResult()+"| This is scan |"+information.getBiomes().toString()+"|~~~~~");
        
    }
    
    public CommandDecider changingStage(Direction dir,InformationCollection infoset){
        return new Stage3(dir,infoset);
    }
}