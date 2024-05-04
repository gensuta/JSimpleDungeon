import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.ParseException;

public class JSONParser {

    public String ScanFile(String fileName) throws IOException{

        String jsonString = "";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            jsonString += line + "\n";
           // System.out.println(line);
        }
        reader.close();


        return jsonString;
    }

    public LocationNode ParseToLocationNodes() throws IOException, ParseException {

        String jsonString = ScanFile("location_nodes.json");


        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        Object obj = parser.parse(jsonString);
        JSONObject jsonObject = (JSONObject) obj; // array of nodes


        LocationNode[] locationNodes = new LocationNode[((JSONObject) obj).size()+1];

        //first add all the objects to a map
        //then organize them properly so that they refer to each other?
        //TODO: Ask how to do this better. Should I really be using a map? Am i being silly?

        for(int i = 0; i < jsonObject.size(); i++)
        {
            locationNodes[i+1] = new LocationNode();
            locationNodes[i+1].idNum = i+1;
        }

        for(int i = 1; i < locationNodes.length; i++)
        {
            JSONObject currentObj = (JSONObject) jsonObject.get(Integer.toString(i));

            if(currentObj.get("north") != null)
            {
                locationNodes[i].northNode = locationNodes[(Integer.parseInt(currentObj.get("north").toString()))];

            }
            if(currentObj.get("east") != null)
            {
                locationNodes[i].eastNode = locationNodes[(Integer.parseInt(currentObj.get("east").toString()))];

            }
            if(currentObj.get("south") != null)
            {
                locationNodes[i].southNode = locationNodes[(Integer.parseInt(currentObj.get("south").toString()))];

            }
            if(currentObj.get("west") != null)
            {
                locationNodes[i].westNode = locationNodes[((Integer.parseInt(currentObj.get("west").toString())))];
            }
        }


        return locationNodes[1];
    }

    public Map<Integer,DialogueNode> ParseToDialogueMap() throws ParseException, IOException {
        String jsonString = ScanFile("test_doc.json");
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