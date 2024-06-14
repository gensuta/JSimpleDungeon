import java.io.File;
import java.util.*;

public class SaveData {
    private List<String> visitedEventIDs;
    private Map<Integer,Player> players;
    private int locationNode;

    private JSONParser jsonParser = new JSONParser();

    public SaveData(){

    }

    public SaveData(Map<Integer,Player> players,List<String> visitedEventIDs, int locationNode) {
        this.players = players;
        this.visitedEventIDs = visitedEventIDs;
        this.locationNode = locationNode;
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }
    public Map<Integer,Player> getPlayers()
    {
        return players;
    }

    public List<String> getvisitedEventIDs() {
        return visitedEventIDs;
    }

    public void addNewEventID(String id){
        if(!didVistEvent(id))
        {
            visitedEventIDs.add(id);
        }
    }

    public boolean didVistEvent(String id) {
        return visitedEventIDs.contains(id);
    }

    public void Save(int locationNode)
    {
        this.locationNode = locationNode;
        jsonParser.SaveGameAsJSON(this);
    }

    public SaveData Load()
    {
        File saveFile = new File("save.json");

        if(saveFile.exists()) {
            return jsonParser.LoadGameFromJSON();
        }
        else{
            players = jsonParser.ParsePlayers();
        }
        return this;
    }





    public int getLocationNode() {
        return locationNode;
    }
}


