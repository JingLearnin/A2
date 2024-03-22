package ca.mcmaster.se2aa4.island.team109;

public interface UTurn {
    boolean IsStillTurning();
    String getCommand();
    String getDirection();    
    void recordMovement();
    void make_movement();
    boolean StopUturning();
}
