import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Dungeon {
    LocationNode currentLocationNode;
    InputHandler inputHandler = new InputHandler();

    public Dungeon(){

    }

    public void GenerateDungeon() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        currentLocationNode = jsonParser.ParseToLocationNodes();

        boolean isRunning = true;
        while(isRunning)
        {
            DisplayLocationOptions();
            MovePlayer();
        }
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

}
