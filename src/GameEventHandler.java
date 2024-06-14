import java.util.List;

public class GameEventHandler {




    public Game.State CheckForEventState(SaveData saveData, LocationNode locationNode){

        if(CheckForGameEvents(saveData.getvisitedEventIDs(),locationNode))
        {
            var gameEvent = locationNode.getGameEvent();
            switch (gameEvent.getEvent()){
                case BATTLE_EVENT:
                    return Game.State.BATTLE_STATE;
                case DIALOGUE_EVENT:
                    return Game.State.DIALOGUE_STATE;
            }
        }

        return Game.State.MOVE_STATE;
    }

    public boolean CheckForGameEvents(List<String> visited_events, LocationNode currentLocationNode){

        GameEvent e = currentLocationNode.getGameEvent();
        if(e == null){
            return false;
        }
        if(e.getEvent() != Event.NONE)
        {
            if(visited_events.contains(e.getId()) || e.isComplete()){
                //TODO: We need a way to handle repeatable events without being stuck on that node 5ever
                return e.isRepeatable();
            }
            else {
                return true;
            }
        }

        return false;
    }




}
