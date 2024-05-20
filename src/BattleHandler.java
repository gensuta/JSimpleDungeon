import java.util.*;

public class BattleHandler {

    public List<Battler> currentBattlers = new ArrayList<>();
    private  int currentBattlerNum = 0;

    private boolean inBattle;

    private Battler currentBattler;

    public void StartBattle(Battler ...battlers){
        List<Battler> list = List.of(battlers); // to ensure currentbattlers is imutable. TODO: plx fix this it's goofy
        currentBattlers = new ArrayList<>(list);

        ConnectOpponents();
        System.out.println("Oh No! It looks like you got into a battle!");
        DisplayEnemies();
        RunBattle();
    }

    public boolean InBattle(){
        return inBattle;
    }

    public void RunBattle(){
        inBattle = true;

        currentBattler = FindPlayer();
        //TODO: Clean up the above line in case the enemy ambushes and gets to go first

        StartTurn();
        while(inBattle)
        {

            while(!currentBattler.TurnFinished())
            {
                currentBattler.MakeDecision();
            }
            CheckForDeaths();

            if(currentBattlers.size() ==1) return; // HACKFIX: it keeps showing the next person's turn cut it out
            ChangeTurn();
        }
    }

    //TODO: Clean up this function because there needs to be a better way to notify battlers who their opponents are
    public void ConnectOpponents()
    {
        for(Battler b : currentBattlers)
        {
            b.opponents = currentBattlers.toArray(new Battler[0]);
        }
    }

    public void ChangeTurn()
    {
        currentBattlerNum = (currentBattlerNum +1 == currentBattlers.size() ? 0 : currentBattlerNum+1);
        currentBattler = currentBattlers.get(currentBattlerNum);
        StartTurn();
    }

    public void StartTurn()
    {
        System.out.println("\n-----------------");
        System.out.printf("\nIt's now %s's turn!",currentBattler.Name());
        currentBattler.StartTurn();
    }

    public void DisplayEnemies()
    {
        for(Battler b : currentBattlers)
        {
            if(b instanceof Enemy)
            {
                Enemy e = (Enemy) b;
                e.DisplayEnemy();
            }
        }
    }

    public Battler FindPlayer()
    {
        for(Battler b : currentBattlers)
        {
            if(b instanceof Player)
            {
               return b;
            }
        }
        System.out.println("Couldn't find a player! Returning null...");
        return null;
    }



    public  void CheckForDeaths(){

        List<Battler> deadBattlers = new ArrayList<>();
        for(Battler b : currentBattlers)
        {
            if(b.Health() <= 0)
            {
                deadBattlers.add(b);
            }
        }

        if(!deadBattlers.isEmpty())
        {
            RemoveDeadBattlers(deadBattlers);
        }


        if(currentBattlers.size() == 1)
        {
           EndBattle(); // TODO: Expand upon this to check if there's only players or only enemies
        }
    }

    public void EndBattle()
    {
        if(currentBattlers.get(0) instanceof Player)
        {
            System.out.println("\nYou were victorious!");
        }
        else
        {
            System.out.println("\nYou ran out of health and passed out....\nGAME OVER."); //TODO: Make it so the player retries or quits!!
        }
        inBattle = false;
    }

    public void RemoveDeadBattlers(List<Battler> deadBattlers)
    {
        for(Battler b : deadBattlers)
        {
            currentBattlers.remove(b);
        }
    }
}
