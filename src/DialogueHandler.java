import java.util.*;

public class DialogueHandler {
    DialogueNode currentNode;
    Map<Integer,DialogueNode> dialogueNodeMap = new HashMap<Integer,DialogueNode>();
    InputHandler inputHandler = new InputHandler();
    boolean runningDialogue;

    public void StartCurrentNode()
    {
        runningDialogue = true;
        while(runningDialogue)
        {
            System.out.println("-----------------");
            currentNode.DisplayLines();
            if(currentNode.getCurrentLine() >= currentNode.getDialogueLines().length)
            {
               HandleResponse();
            }

            if(atEndOfDialogue())
            {
                runningDialogue=false;
            }
        }

    }

    public void SetCurrentNode(int node_id){
        currentNode = dialogueNodeMap.get(node_id);
    }

    private void HandleResponse()
    {
        if(currentNode.getDialogueChoices().length > 0)
        {
            int choice = -1;
            while (choice == -1)
            {
               choice = inputHandler.CheckAgainstChoices(currentNode.getDialogueChoices());
            }
            MakeChoice(choice);
        }
        else {
            GoToNextNode();
        }
    }

    public void CreateDialogueNodes(){
        JSONParser jsonParser = new JSONParser();
        dialogueNodeMap = jsonParser.ParseToDialogueMap();
        currentNode = dialogueNodeMap.get(1);
    }

    public void GoToNextNode(){
        if(atEndOfDialogue())
        {
            System.out.println("-----------------");
            runningDialogue=false;
        }
        else{
            currentNode = dialogueNodeMap.get(currentNode.getNextNodes()[0]);
            StartCurrentNode();
        }
    }

    public void MakeChoice(int choice)
    {
        currentNode = dialogueNodeMap.get(currentNode.getNextNodes()[choice]);
        StartCurrentNode();
    }

    public boolean atEndOfDialogue(){
        return currentNode.getNextNodes().length == 0;
    }
}
