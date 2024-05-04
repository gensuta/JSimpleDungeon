import java.util.*;

public class InputHandler {

    public String GetUserInput()
    {
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
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
