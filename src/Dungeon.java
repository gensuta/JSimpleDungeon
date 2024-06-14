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

    public void ChangeLocation()
    {
        currentLocationNode = inputHandler.CheckAgainstDirections(currentLocationNode);
    }

    public LocationNode getLocation(int id)
    {
        return allLocationNodes[id];
    }

}
