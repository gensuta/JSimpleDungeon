import java.util.ArrayList;

public class Battle {
    private boolean canEscape;
    public ArrayList<Enemy> enemies;
    private EnemyManager enemyManager = new EnemyManager();
    private boolean isAmbush;

    public void Init(String ...enemyIDs)
    {
        for(String id : enemyIDs)
        {
            enemyManager.getEnemy(id);
        }
    }
}
