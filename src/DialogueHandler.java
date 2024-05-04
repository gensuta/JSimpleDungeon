import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.*;

public class DialogueHandler {
    DialogueNode currentNode;
    Map<Integer,DialogueNode> dialogueNodeMap = new HashMap<Integer,DialogueNode>();
    InputHandler inputHandler = new InputHandler();

    public void StartCurrentNode()
    {
        boolean isRunning = true;
        while(isRunning)
        {
            System.out.println("-----------------");
            currentNode.DisplayLines();
            if(currentNode.getCurrentLine() >= currentNode.nodeLines.length)
            {
               HandleResponse();
            }

            if(currentNode.nextNodes.length == 0)
            {
                isRunning=false;
            }
        }

    }

    private void HandleResponse()
    {
        if(!currentNode.dialogueChoices.isEmpty())
        {
            int choice = -1;
            while (choice == -1)
            {
               choice = inputHandler.CheckAgainstChoices(currentNode.dialogueChoices);
            }
            MakeChoice(choice);
        }
        else {
            GoToNext();
        }
    }

    //TODO: Change how this works too. this looks silly x-x
    public void CreateDialogueNodes() throws ParseException, IOException {
        JSONParser jsonParser = new JSONParser();
        dialogueNodeMap = jsonParser.ParseJsonFile();
        currentNode = dialogueNodeMap.get(1);
    }

    //TODO: Maybe rename this?
    public void GoToNext(){
        if(currentNode.nextNodes.length == 0)
        {
            System.out.println("-----The End------");
        }
        else{
            currentNode = dialogueNodeMap.get(currentNode.nextNodes[0]);
            StartCurrentNode();
        }
    }

    public void MakeChoice(int choice)
    {

        // TODO: FIX THIS THIS LOOKS SILLY
        currentNode = dialogueNodeMap.get(currentNode.nextNodes[choice]);
        StartCurrentNode();
    }

    public void Start() throws ParseException, IOException
    {
        CreateDialogueNodes(); //TODO: Please fix the fact it needs to throw exceptions haha;;;;
        StartCurrentNode();
    }
}
