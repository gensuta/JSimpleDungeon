import java.util.*;

public class InputHandler {

    public String GetUserInput()
    {
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    // Input for Movement
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
                     newNode = locationNode.getNorthNode();
                     break;
                 case "east":
                     newNode = locationNode.getEastNode();
                     break;
                 case "south":
                     newNode = locationNode.getSouthNode();
                     break;
                 case "west":
                     newNode = locationNode.getWestNode();
                     break;
             }
            if(newNode == null){ System.out.println("Huh? Can you try that again?");}
        }


        return newNode;

    }

    //Input for dialogue
    public int CheckAgainstChoices(String[] choices)
    {
        String input = GetUserInput().toLowerCase();

        for(int i = 0 ; i < choices.length; i++)
        {
            if(choices[i].toLowerCase().equals(input))
            {
                return i;
            }
        }


        System.out.println("Huh? Can you try that again?");
        return -1;
    }

    //Input for Battles


}
