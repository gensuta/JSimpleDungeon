import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player extends Battler{

    private InputHandler inputHandler = new InputHandler();

    public Player()
    {

    }

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
