import java.util.HashMap;
import java.util.Map;

public class EnemyManager {
    //Enemies all have id numbers tied to them to be grabbed
    public Map<String,Enemy> enemyMap = new HashMap<>();

    public EnemyManager()
    {
        JSONParser jsonParser = new JSONParser();
        enemyMap = jsonParser.ParseEnemies();
    }



    public Enemy getEnemy(String ID)
    {
        return enemyMap.get(ID);
    }
}
