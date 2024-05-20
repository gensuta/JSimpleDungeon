public class Battler extends  HealthHaver
{

    Battler[] opponents;

    private String name;
    public Stats STATS;

    private boolean isDefending;

    private int hitRate;

    private boolean turnFinished;


    public boolean IsDefending(){
        return isDefending;
    }

    public String Name()
    {
        return name;
    }
    public  boolean TurnFinished()
    {
        return  turnFinished;
    }

    public Battler(int maxHealth, Stats STATS, String name, int hitRate ) {
        super(maxHealth);
        this.STATS = STATS;
        this.name = name;
        this.hitRate = hitRate;
    }

    public void MakeDecision()
    {

    }

    //TODO: Figure out how to properly implement fleeing for enemies
    public void Flee(){
        System.out.printf("\n%s attempted to flee!",name);
        turnFinished = true;
    }

    public void Attack(Battler opponent){

        boolean didMiss = (Math.random()*100) >= hitRate;

        if(didMiss) {
            System.out.printf("%s missed!",name);
            turnFinished = true;
            return;
        }

        int dmg = (opponent.IsDefending()) ? STATS.ATK - (opponent.STATS.DEF/2) : STATS.ATK;
        opponent.DecreaseHealth(dmg);

        System.out.printf("\n%s has attacked %s for %d damage.%n\n%s now has %d health left!",name, opponent.Name(),dmg,opponent.Name(),opponent.Health());
        turnFinished = true;
    }

    public void Defend(){
        isDefending = true;
        System.out.printf("\n%s started defending!",name);
        turnFinished = true;
    }

    // initializing before getting started!
    public void StartTurn(){
        isDefending = false;
        turnFinished = false;
        System.out.printf("\n%s is making a decision...",name);
    }
}
