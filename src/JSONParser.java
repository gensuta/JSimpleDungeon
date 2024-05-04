import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.ParseException;

public class JSONParser {
    String jsonFilePath = "test_doc.json";
    public String ScanFile() throws IOException{

        String jsonString = "";
        BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
        String line;
        while ((line = reader.readLine()) != null) {
            jsonString += line + "\n";
           // System.out.println(line);
        }
        reader.close();


        return jsonString;
    }

    public Map<Integer,DialogueNode> ParseJsonFile() throws ParseException, IOException {
        String jsonString = ScanFile();
        Map<Integer, DialogueNode> dialogueNodeMap = new HashMap<Integer, DialogueNode>();

        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        Object obj = parser.parse(jsonString);
        JSONObject jsonObject = (JSONObject) obj; // array of nodes

        for(int i = 1; i < jsonObject.size()+1; i++)
        {
            JSONObject currentObj = (JSONObject) jsonObject.get(Integer.toString(i));

            if(currentObj == null) continue;

            String[] nodeLines = toStringArray ( (JSONArray) currentObj.get("dialogueLines"));
            int[] nextNodes = toIntArray ((JSONArray) currentObj.get("nextNode")); //TODO:rename nextNode to nextNodes in the JSONFile
            Map<String,Integer> dialogueChoices = toChoiceArray ((JSONArray) currentObj.get("dialogueChoices"));

            DialogueNode currentNode = new DialogueNode(i, nodeLines, dialogueChoices, nextNodes);
            dialogueNodeMap.put(i, currentNode);
        }


        return dialogueNodeMap;
    }

    public String[] toStringArray(JSONArray jsonArray) {
        if(jsonArray ==null)
            return new String[0];

        String[] arr=new String[jsonArray.size()];
        for(int i=0; i<arr.length; i++) {
            arr[i]= jsonArray.get(i).toString();
        }
        return arr;
    }

    public int[] toIntArray(JSONArray jsonArray) {
        if(jsonArray ==null)
            return new int[0];

        int[] arr=new int[jsonArray.size()];
        for(int i=0; i<arr.length; i++) {
        // TODO: Ask if there's a cleaner way to do this
            arr[i]= Integer.parseInt(jsonArray.get(i).toString());
        }
        return arr;
    }

    public Map<String,Integer> toChoiceArray(JSONArray jsonArray) {
        if(jsonArray ==null)
            return new HashMap<String,Integer>();

        Map<String,Integer> map = new HashMap<String,Integer>();
        for(int i = 0; i< jsonArray.size(); i++) {
            map.put(jsonArray.get(i).toString(),i);
        }
        return map;
    }
}