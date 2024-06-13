import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.*;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParser {

    ObjectMapper objectMapper = new ObjectMapper();

    public SaveData LoadGame()
    {
        try {
            return objectMapper.readValue(Paths.get("save.json").toFile(), SaveData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void SaveGame(SaveData s) {
        try {
            objectMapper.writeValue(new File("save.json"), s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LocationNode[] ParseToLocationNodes() {
        try {
            JsonNode jsonNode = objectMapper.readTree(Paths.get("location_nodes.json").toFile());
            LocationNode[] locationNodes = new LocationNode[jsonNode.size()];

            //first add all the objects to a map
            //then organize them properly so that they refer to each other?
            for (int i = 0; i < jsonNode.size(); i++) {
                locationNodes[i] = new LocationNode();
                locationNodes[i].setIdNum(i);
            }

            for (int i = 0; i < locationNodes.length; i++) {

                JsonNode currentNode = jsonNode.get(Integer.toString(i));


                locationNodes[i].setGameEvent(objectMapper.treeToValue(currentNode.get("gameEvent"),GameEvent.class));

                if (JsonNodeToInt(currentNode.get("northNode")) != -1 ) {
                    locationNodes[i].setNorthNode(locationNodes[JsonNodeToInt(currentNode.get("northNode"))]);
                }
                if (JsonNodeToInt(currentNode.get("eastNode")) != -1 ){
                    locationNodes[i].setEastNode(locationNodes[JsonNodeToInt(currentNode.get("eastNode"))]);

                }
                if (JsonNodeToInt(currentNode.get("southNode")) != -1 ) {
                    locationNodes[i].setSouthNode(locationNodes[JsonNodeToInt(currentNode.get("southNode"))]);

                }
                if (JsonNodeToInt(currentNode.get("westNode")) != -1 ) {
                    locationNodes[i].setWestNode(locationNodes[JsonNodeToInt(currentNode.get("westNode"))]);
                }
            }

            return locationNodes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public int JsonNodeToInt(JsonNode node){

        try {
            return Integer.parseInt(node.toString());
        }
        catch(Exception e)
        {
            return -1;
        }
    }

    public Map<Integer, DialogueNode> ParseToDialogueMap() {

        try {
            return objectMapper.readValue(Paths.get("dialogue_nodes.json").toFile(), new TypeReference<Map<Integer, DialogueNode>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

