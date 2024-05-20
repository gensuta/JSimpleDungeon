import java.util.*;

public class Player extends Battler{

    //Player can battle and has a position in the dungeon!
    //Also has an inventory ^-^ the int in the map is the amount of the item

    public Map<Item,Integer> inventory = new HashMap<>();

    public LocationNode currentLocation;

    private InputHandler inputHandler = new InputHandler();

    public Player(int maxHealth, Stats STATS, String name, int hitRate) {
        super(maxHealth, STATS, name, hitRate);
    }

    public void MakeDecision()
    {
        while (!TurnFinished()) {
            String decision = inputHandler.GetUserInput().toLowerCase();
            switch (decision) {
                case "attack":
                    Attack(opponents[1]); // TODO: Ensure the player chooses who to attack. This is hardcoded for testing purposes
                    break;
                case "defend":
                    Defend();
                    break;
                case "flee":
                    Flee();
                    break;
                default:
                    System.out.println("Huh? Can you try that again?");
                    break;
            }
        }

    }
}
