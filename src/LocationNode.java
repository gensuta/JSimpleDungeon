import java.util.*;

public class LocationNode {
    private Integer id;
    private LocationNode northNode;
    private LocationNode eastNode;
    private LocationNode southNode;
    private LocationNode westNode;
    private GameEvent gameEvent;

    private Map<String,LocationNode> map = new HashMap<>();

    public LocationNode(){

    }

    public LocationNode(LocationNode north, LocationNode east, LocationNode south, LocationNode west, GameEvent event){
        this.setNorthNode(north);
        this.setEastNode(east);
        this.setSouthNode(south);
        this.setWestNode(west);

        this.setGameEvent(event);

        getMap().put("north", this.getNorthNode());
        getMap().put("east", this.getEastNode());
        getMap().put("south", this.getSouthNode());
        getMap().put("west", this.getWestNode());
    }

    public boolean canMoveNorth()
    {
        return getNorthNode() != null;
    }
    public boolean canMoveEast()
    {
        return getEastNode() != null;
    }
    public boolean canMoveSouth()
    {
        return getSouthNode() != null;
    }
    public boolean canMoveWest()
    {
        return getWestNode() != null;
    }

    //getters and setters below
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocationNode getNorthNode() {
        return northNode;
    }

    public void setNorthNode(LocationNode northNode) {
        this.northNode = northNode;
    }

    public LocationNode getEastNode() {
        return eastNode;
    }

    public void setEastNode(LocationNode eastNode) {
        this.eastNode = eastNode;
    }

    public LocationNode getSouthNode() {
        return southNode;
    }

    public void setSouthNode(LocationNode southNode) {
        this.southNode = southNode;
    }

    public LocationNode getWestNode() {
        return westNode;
    }

    public void setWestNode(LocationNode westNode) {
        this.westNode = westNode;
    }

    public GameEvent getGameEvent() {
        return gameEvent;
    }

    public void setGameEvent(GameEvent gameEvent) {
        this.gameEvent = gameEvent;
    }

    public Map<String, LocationNode> getMap() {
        return map;
    }

    public void setMap(Map<String, LocationNode> map) {
        this.map = map;
    }
}
