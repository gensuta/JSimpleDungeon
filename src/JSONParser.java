import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParser {

    ObjectMapper objectMapper = new ObjectMapper();
    EncryptionHandler encryptionHandler = new EncryptionHandler("JS1mpL3D<^g30N!");

    public SaveData LoadGameFromJSON() {
        try {

            Path path = Paths.get("save.json");

            Stream<String> lines = Files.lines(path);
            String encryptedString = lines.collect(Collectors.joining("\n"));
            lines.close();


            String jsonString = encryptionHandler.decrypt(encryptedString);
            return objectMapper.readValue(jsonString, SaveData.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public void SaveGameAsJSON(SaveData s) {
        try {
            String saveString = objectMapper.writeValueAsString(s);
            String encryptedString = encryptionHandler.encrypt(saveString);
            SaveGameToFile(encryptedString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SaveGameToFile(String saveDataString) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("save.json"));
            writer.write(saveDataString);
            writer.close();
        } catch (IOException e) {
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
                locationNodes[i].setId(i);
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

    public Map<String,Enemy> ParseEnemies()
    {
        try {
            return objectMapper.readValue(Paths.get("enemies.json").toFile(), new TypeReference<Map<String, Enemy>>() {
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  null;

    }

    public Map<Integer,Player> ParsePlayers()
    {
        try {
            return objectMapper.readValue(Paths.get("player.json").toFile(), new TypeReference<Map<Integer, Player>>() {
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  null;

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

    public Map<String, DialogueNode> ParseDialogueMap() {

        try {
            return objectMapper.readValue(Paths.get("dialogue_nodes.json").toFile(), new TypeReference<Map<String, DialogueNode>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

