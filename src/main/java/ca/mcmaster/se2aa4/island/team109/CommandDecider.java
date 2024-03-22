package ca.mcmaster.se2aa4.island.team109;


    public interface CommandDecider{
        boolean changeStage();
        String[] getcommand();
        void algorithem(Information record);
        CommandDecider changingStage(Direction dir, InformationCollection info);
    }
        