import java.util.*;

public class SaveData {
    private List<Integer> visitedEventIDs;
    private Player player;
    private int locationNode;

    private JSONParser jsonParser = new JSONParser();

    public SaveData(){

    }

    public SaveData(Player player,List<Integer> visitedEventIDs, int locationNode) {
        this.player = player;
        this.visitedEventIDs = visitedEventIDs;
        this.locationNode = locationNode;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Integer> getvisitedEventIDs() {
        return visitedEventIDs;
    }

    public void addNewEventID(int id){
        if(!didVistEvent(id))
        {
            visitedEventIDs.add(id);
        }
    }

    public boolean didVistEvent(int id) {
        return visitedEventIDs.contains(id);
    }

    public void Save(int locationNode)
    {
        this.locationNode = locationNode;
        jsonParser.SaveGame(this);
    }

    public SaveData Load()
    {
        return jsonParser.LoadGame();
    }




    public int getLocationNode() {
        return locationNode;
    }
}


