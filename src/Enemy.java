import java.util.*;

public class Enemy extends Battler{

    private int fleeRate;
    private int attackRate;
    private int defendRate;

    private int ID;

    public Enemy(int maxHealth, Stats STATS, String name, int hitRate, int fleeRate, int attackRate, int defendRate, int id) {
        super(maxHealth, STATS, name, hitRate);
        this.fleeRate = fleeRate;
        this.attackRate = attackRate;
        this.defendRate = defendRate;
        ID = id;
    }

    public int ID(){
        return this.ID;
    }

    public void MakeDecision()
    {
        //TODO: Ask how to handle percents for rpgs. I haven't fully done the math and I think I'm wrong so far

        //Take our rates and organize them from lowest to highest
        //Then add the previous rate to the current rate to get the percent
        //ie flee rate is 10 attack rate is 20 so attackPercent is 30
        // I'm not doing that today though because I'm LAZY/tired

        int num = (int) (Math.random() * 100);

        if(num <= fleeRate)
        {
            Flee();
        }

        else if(num <= defendRate)
        {
            Defend();
        }

        else if(num <= attackRate)
        {
            Attack(opponents[0]); //TODO: Ensure the enemy chooses who to attack. This is hardcoded for testing purposes
        }
        else {
            Attack(opponents[0]); //TODO: Ensure the enemy chooses who to attack. This is hardcoded for testing purposes
        }
    }

    public  void DisplayEnemy()
    {
        System.out.printf("\n%s has %d health, %d attack, and %d defense.",this.Name(),this.Health(), this.STATS.ATK,this.STATS.DEF);
    }
}
