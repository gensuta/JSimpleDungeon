import java.util.*;

public class Game {

    boolean isRunning = true;

    private BattleHandler battleHandler = new BattleHandler();
    private Dungeon dungeon = new Dungeon();
    private DialogueHandler dialogueHandler = new DialogueHandler();


    private GameEvent currentGameEvent;
    private State currentState;

    //battlers to grab from haha
    public Player player = new Player(100,new Stats(25,22,18),"Player",85);
    public Enemy slime = new Enemy(100,new Stats(12,17,10),"Slime",72,10,30,30,1);
    public Enemy bat = new Enemy(100,new Stats(18,13,20),"Bat",90,50,30,20,2);

    //Enemies all have id numbers tied to them to be grabbed
    public Map<Integer,Enemy> enemyMap = new HashMap<>();

    public void Init(){
        dungeon.GenerateDungeon();
        dialogueHandler.CreateDialogueNodes();
        currentState = State.MOVE_STATE;  // Movement is the default state

        //TODO: Create new JSON with all the enemy info and have the JSONParser return a map with all the correct info
        //For now I'm just gonna add stuff to the map for testing purposes. The battle working is the main priority

        //TODO: Another one! I need to figure out how to have an id for a multi enemy battle!
        enemyMap.put(slime.ID(),slime);
        enemyMap.put(bat.ID(),bat);

    }

    public void BeginEvent(Event e, int ID){
        switch(e)
        {
            case DIALOGUE_EVENT:
                dialogueHandler.SetCurrentNode(ID);
                dialogueHandler.StartCurrentNode();
                currentState = State.DIALOGUE_STATE;
                break;
            case BATTLE_EVENT:
                currentState = State.BATTLE_STATE;
                battleHandler.StartBattle(player,enemyMap.get(ID));
                break;
        }
    }

    public void Run(){

        Init();

        while (isRunning){

            //TODO: Clean this up so the Game doesn't even have to call CheckForGameEvents
            //TODO: We need a way to handle repeatable events without being stuck on that node 5ever
            switch (currentState) {
                case MOVE_STATE:
                    if(dungeon.CheckForGameEvents())
                    {
                        currentGameEvent = dungeon.currentLocationNode.getGameEvent();
                        BeginEvent(currentGameEvent.getEvent(), currentGameEvent.getId());
                    }
                    else {
                        dungeon.DisplayLocationOptions();
                        dungeon.MovePlayer();
                    }
                    break;
                case INPUT_STATE:
                    //TODO: Figure out what I want to do here so we can actually type quit to quit haha
                    break;
                case BATTLE_STATE:
                    if(!battleHandler.InBattle()){
                        currentGameEvent.setComplete(true);
                        currentState = State.MOVE_STATE;
                    }
                    break;
                case DIALOGUE_STATE:
                    if(dialogueHandler.atEndOfDialogue()) {
                        currentGameEvent.setComplete(true);
                        currentState= State.MOVE_STATE;
                    }
                    break;

            }
        }
    }

    public enum State { DIALOGUE_STATE,BATTLE_STATE,MOVE_STATE,INPUT_STATE}


    public  void Quit(){
        isRunning = false;
    }
}
