import java.util.List;

public class Dungeon {
    LocationNode[] allLocationNodes;
    LocationNode currentLocationNode;
    InputHandler inputHandler = new InputHandler();

    public Dungeon(){

    }

    public void GenerateDungeon() {
        JSONParser jsonParser = new JSONParser();
        allLocationNodes = jsonParser.ParseToLocationNodes();
        currentLocationNode = allLocationNodes[4];
    }

    public void DisplayLocationOptions()
    {
        String displayLine = "You can go";
        if(currentLocationNode.canMoveNorth()){
            displayLine += "...north";
        }
        if(currentLocationNode.canMoveEast()){
            displayLine += "...east";
        }
        if(currentLocationNode.canMoveSouth()){
            displayLine += "...south";
        }
        if(currentLocationNode.canMoveWest()){
            displayLine += "...west";
        }
        System.out.println(displayLine + ".");
    }

    public void MovePlayer()
    {
        currentLocationNode = inputHandler.CheckAgainstDirections(currentLocationNode);
    }

    public LocationNode getLocation(int id)
    {
        return allLocationNodes[id];
    }

    //TODO: THE DUNGEON SHOULDN'T CHECK FOR GAME EVENTS!!!!!!!!
    public boolean CheckForGameEvents(List<Integer> visited_events){

        GameEvent e = currentLocationNode.getGameEvent();
        if(e == null){
            return false;
        }
        if(e.getEvent() != Event.NONE)
        {
            if(visited_events.contains(e.getId()) || e.isComplete()){
                return e.isRepeatable();
            }
            else {
                return true;
            }
        }

        return false;
    }
}
