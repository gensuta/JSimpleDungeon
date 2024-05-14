public class Game {

    boolean isRunning = true;

    Dungeon dungeon = new Dungeon();
    DialogueHandler dialogueHandler = new DialogueHandler();
    private GameEvent currentGameEvent;

    boolean isDialogueRunning;

    public void Init(){
        dungeon.GenerateDungeon();
        dialogueHandler.CreateDialogueNodes();
    }

    public void BeginEvent(Event e, int ID){
        switch(e)
        {
            case DIALOGUE:
                dialogueHandler.SetCurrentNode(ID);
                dialogueHandler.StartCurrentNode();
                isDialogueRunning = true;
                break;
            case BATTLE:
                break;
        }
    }

    public void Run(){

        Init();

        while (isRunning){
            if(isDialogueRunning)
            {
                if(dialogueHandler.atEndOfDialogue())
                {
                    currentGameEvent.setComplete(true);
                    isDialogueRunning = false;
                }
            }
            else if(dungeon.CheckForGameEvents())
            {
                currentGameEvent = dungeon.currentLocationNode.getGameEvent();
                BeginEvent(currentGameEvent.getEvent(), currentGameEvent.getId());
            }
            else {
                // Movement is the default state
                dungeon.DisplayLocationOptions();
                dungeon.MovePlayer();
            }
            //...Do I make a state machine for this too omfg

        }
    }

    public  void Quit(){
        isRunning = false;
    }
}
