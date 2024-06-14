import java.util.*;

public class Game {

    boolean isRunning = true;

    private final BattleHandler battleHandler = new BattleHandler();
    private final Dungeon dungeon = new Dungeon();
    private final DialogueHandler dialogueHandler = new DialogueHandler();
    private final GameEventHandler gameEventHandler = new GameEventHandler();
    private final EnemyManager enemyManager = new EnemyManager();

    private SaveData currentSaveData;
    private State currentState;

    public Map<Integer,Player> players = new HashMap<>();

    private GameEvent currentGameEvent;

    public void Init(){
        dungeon.GenerateDungeon();
        dialogueHandler.CreateDialogueNodes();
        currentState = State.MOVE_STATE;  // Movement is the default state
        currentSaveData = new SaveData(players,new ArrayList<>(),0);

        currentSaveData = currentSaveData.Load();

        dungeon.currentLocationNode = dungeon.getLocation(currentSaveData.getLocationNode());

        players = currentSaveData.getPlayers();

    }

    public void Run(){

        Init();

        while (isRunning){

            currentState = gameEventHandler.CheckForEventState(currentSaveData,dungeon.currentLocationNode);
            currentGameEvent = (currentState != State.MOVE_STATE) ? dungeon.currentLocationNode.getGameEvent() : null;
            if(currentGameEvent != null)
            {
                BeginEvent(currentGameEvent);
            }

            switch (currentState) {
                case MOVE_STATE:
                    dungeon.DisplayLocationOptions();
                    dungeon.ChangeLocation();
                    break;
                case INPUT_STATE:
                    break;
                case BATTLE_STATE:
                    if (!battleHandler.InBattle()) {
                        currentGameEvent.setComplete(true);
                        currentState = Game.State.MOVE_STATE;
                    }
                    break;
                case DIALOGUE_STATE:
                    if (dialogueHandler.atEndOfDialogue()) {
                        currentGameEvent.setComplete(true);
                        currentState = State.MOVE_STATE;
                    }
                    break;

            }

            //save after completing the actions in a state
            currentSaveData.Save(dungeon.currentLocationNode.getId());
        }
    }

    //TODO: I want to move this really bad, but I'll leave this here until I properly refactor this code some more
    public void BeginEvent(GameEvent e){
        currentSaveData.addNewEventID(e.getId());

        switch(e.getEvent())
        {
            case DIALOGUE_EVENT:
                dialogueHandler.SetCurrentNode(e.getId());
                dialogueHandler.StartCurrentNode();
                currentState = Game.State.DIALOGUE_STATE;
                break;
            case BATTLE_EVENT:
                currentState = Game.State.BATTLE_STATE;
                battleHandler.StartBattle(players.get(1),enemyManager.getEnemy(e.getId()));
                break;
        }
    }

    public enum State { DIALOGUE_STATE,BATTLE_STATE,MOVE_STATE,INPUT_STATE}


    public  void Quit(){
        isRunning = false;
    }
}
