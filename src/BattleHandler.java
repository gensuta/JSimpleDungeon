import java.util.*;

public class BattleHandler {

    public List<Battler> currentBattlers = new ArrayList<>();
    private  int currentBattlerNum = 0;

    private boolean inBattle;

    private Battler currentBattler;

    public void StartBattle(Battler ...battlers){
        List<Battler> list = List.of(battlers);
        currentBattlers = new ArrayList<>(list);

//        //TODO: Look into how the JSON plugin runs the constructor since it's not currently doing that >:(
//        //We do NOT want to be giving the player health during every single battle, but this will be fixed before the summer's over
//        for(Battler b : currentBattlers)
//        {
//            b.IncreaseHealth(b.getMaxHealth());
//        }

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
            CheckForEscapees();
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

    public  void CheckForEscapees(){

        List<Battler> escapedBattlers = new ArrayList<>();
        for(Battler b : currentBattlers)
        {
            if(b.getFled())
            {
                escapedBattlers.add(b);
            }
        }

        if(!escapedBattlers.isEmpty())
        {
            RemoveBattlers(escapedBattlers);
        }


        if(currentBattlers.size() == 1)
        {
            EndBattle();
        }
    }


    public  void CheckForDeaths(){

        List<Battler> deadBattlers = new ArrayList<>();
        for(Battler b : currentBattlers)
        {
            if(b.getCurrentHealth() <= 0)
            {
                deadBattlers.add(b);
            }
        }

        if(!deadBattlers.isEmpty())
        {
            RemoveBattlers(deadBattlers);
        }


        if(currentBattlers.size() == 1)
        {
           EndBattle();
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

    public void RemoveBattlers(List<Battler> battlers)
    {
        for(Battler b : battlers)
        {
            currentBattlers.remove(b);
        }
    }
}
