public class Enemy extends Battler{

    private int fleeRate;
    private int attackRate;
    private int defendRate;

    private String ID;

    public Enemy(){
    }

    public Enemy(int maxHealth, Stats STATS, String name, int hitRate, int fleeRate, int attackRate, int defendRate, String id) {
        super(maxHealth, STATS, name, hitRate);
        this.setFleeRate(fleeRate);
        this.setAttackRate(attackRate);
        this.setDefendRate(defendRate);
        setID(id);
    }

    public String ID(){
        return this.getID();
    }

    public void MakeDecision()
    {
        //TODO: Create proper weighted probabilities for decision making

        int num = (int) (Math.random() * 100);

        if(num <= getFleeRate())
        {
            Flee();
        }

        else if(num <= getDefendRate())
        {
            Defend();
        }

        else if(num <= getAttackRate())
        {
            Attack(opponents[0]); //TODO: Ensure the enemy chooses who to attack. This will be hardcoded until multi-player battles are implemented
        }
        else {
            Attack(opponents[0]); //attacks by default
        }
    }

    public  void DisplayEnemy()
    {
        System.out.printf("\n%s has %d health, %d attack, and %d defense.",this.Name(),this.getCurrentHealth(), this.STATS.ATK,this.STATS.DEF);
    }

    public int getFleeRate() {
        return fleeRate;
    }

    public void setFleeRate(int fleeRate) {
        this.fleeRate = fleeRate;
    }

    public int getAttackRate() {
        return attackRate;
    }

    public void setAttackRate(int attackRate) {
        this.attackRate = attackRate;
    }

    public int getDefendRate() {
        return defendRate;
    }

    public void setDefendRate(int defendRate) {
        this.defendRate = defendRate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
