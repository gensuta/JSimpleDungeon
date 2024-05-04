import java.util.*;

public class InputHandler {

    public String GetUserInput()
    {
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    public LocationNode CheckAgainstDirections(LocationNode locationNode)
    {

        boolean canMove = false;
        LocationNode newNode = null;

        while(newNode == null)
        {
            String input = GetUserInput();
             switch (input.toLowerCase())
             {
                 case "north":
                     newNode = locationNode.northNode;
                     break;
                 case "east":
                     newNode = locationNode.eastNode;
                     break;
                 case "south":
                     newNode = locationNode.southNode;
                     break;
                 case "west":
                     newNode = locationNode.westNode;
                     break;
                     default:
                     System.out.println("Huh? Can you try that again?");
                     break;
             }
        }


        return newNode;

    }

    public int CheckAgainstChoices(Map<String,Integer> choices)
    {
        //TODO: See if you can check for the input being lowercase or all caps in a clean way
        String input = GetUserInput();
        if(choices.containsKey(input))
        {
            return choices.get(input);
        }

        System.out.println("Huh? Can you try that again?");
        return -1;
    }



}
