import java.util.Map;

public class DialogueNode {

    private int nodeNum;
    private boolean visited;
    private String[] dialogueLines;
    private String[] dialogueChoices;
    private String[] nextNodes;

    private int _currentLine;

    public int getCurrentLine(){
        return get_currentLine();
    }

    public DialogueNode(){

    }

    public DialogueNode(int nodeNum, String[] dialogueLines, String[] dialogueChoices, String[] nextNodes) {
        this.setNodeNum(nodeNum);
        this.setVisited(false);
        this.setDialogueLines(dialogueLines);
        this.setDialogueChoices(dialogueChoices);
        this.setNextNodes(nextNodes);
        this.set_currentLine(0);
    }



    public void DisplayChoices()
    {
        System.out.println("Make a choice:");
       for(String s : getDialogueChoices())
       {
           System.out.println(s);
       }
    }

    public void DisplayLines(){
        while(get_currentLine() < getDialogueLines().length)
        {
            System.out.println(getDialogueLines()[get_currentLine()]);
            set_currentLine(get_currentLine() + 1);
        }
        if(getDialogueChoices().length > 0)
        {
            DisplayChoices();
        }
    }


    public int getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String[] getDialogueLines() {
        return dialogueLines;
    }

    public void setDialogueLines(String[] dialogueLines) {
        this.dialogueLines = dialogueLines;
    }

    public String[] getDialogueChoices() {
        return dialogueChoices;
    }

    public void setDialogueChoices(String[] dialogueChoices) {
        this.dialogueChoices = dialogueChoices;
    }

    public String[] getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(String[] nextNodes) {
        this.nextNodes = nextNodes;
    }

    public int get_currentLine() {
        return _currentLine;
    }

    public void set_currentLine(int _currentLine) {
        this._currentLine = _currentLine;
    }
}
