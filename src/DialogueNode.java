import java.util.HashMap;
import java.util.Map;

public class DialogueNode {

    int nodeNum;
    boolean visited;
    String[] nodeLines;
    Map<String,Integer> dialogueChoices = new HashMap<>();
    int[] nextNodes;

    int _currentLine;

    public int getCurrentLine(){
        return _currentLine;
    }

    public DialogueNode(int nodeNum, String[] nodeLines,Map<String,Integer> dialogueChoices, int[] nextNodes) {
        this.nodeNum = nodeNum;
        this.visited = false;
        this.nodeLines = nodeLines;
        this.dialogueChoices = dialogueChoices;
        this.nextNodes = nextNodes;
        this._currentLine = 0;
    }

    public void DisplayChoices()
    {
        for (Map.Entry<String,Integer> choice: dialogueChoices.entrySet()) {
            System.out.println(choice.getKey());
        }
    }

    public void DisplayLines(){
        while(_currentLine < nodeLines.length)
        {
            System.out.println(nodeLines[_currentLine]);
            _currentLine++;
        }
        if(!dialogueChoices.isEmpty())
        {
            DisplayChoices();
        }
    }


}
