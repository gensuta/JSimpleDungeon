public class Dungeon {
    LocationNode currentLocationNode;
    InputHandler inputHandler = new InputHandler();

    public Dungeon(){

    }

    public void GenerateDungeon() {
        JSONParser jsonParser = new JSONParser();
        currentLocationNode = jsonParser.ParseToLocationNodes();
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

    public boolean CheckForGameEvents(){

        GameEvent e = currentLocationNode.getGameEvent();
        if(e == null){
            return false;
        }
        if(e.getEvent() != Event.NONE)
        {
            if(e.isComplete()){
                return e.isRepeatable();
            }
            else {
                return true;
            }
        }

        return false;
    }
}
