import java.util.*;

public class LocationNode {
    int idNum = 0;
    LocationNode northNode, eastNode, southNode, westNode;
    GameEvent gameEvent;

    Map<String,LocationNode> map = new HashMap<>();

    public LocationNode(){

    }

    public LocationNode(LocationNode north, LocationNode east, LocationNode south, LocationNode west, GameEvent event){
        this.northNode = north;
        this.eastNode = east;
        this.southNode = south;
        this.westNode = west;

        this.gameEvent = event;

        map.put("north",this.northNode);
        map.put("east",this.eastNode);
        map.put("south",this.southNode);
        map.put("west",this.westNode);
    }

    public boolean canMoveNorth()
    {
        return northNode != null;
    }
    public boolean canMoveEast()
    {
        return eastNode != null;
    }
    public boolean canMoveSouth()
    {
        return southNode != null;
    }
    public boolean canMoveWest()
    {
        return westNode != null;
    }
}
