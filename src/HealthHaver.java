import java.util.*;

public class HealthHaver {
    private int maxHealth;
    private int currentHealth;

    public HealthHaver(){

    }

    public HealthHaver(int maxHealth){
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

   public void IncreaseHealth(int num){
        currentHealth += num;
        currentHealth = clamp(currentHealth,0,maxHealth);
   }
    public void DecreaseHealth(int num){
        currentHealth -= num;
        currentHealth = clamp(currentHealth,0,maxHealth);
    }

    private int clamp(int value, int minVal, int maxVal)
    {
        if(value < minVal)
        {
            return minVal;
        }

        if(value > maxVal)
        {
            return  maxVal;
        }

        return  value;

    }

    public int getMaxHealth()
    {
        return this.maxHealth;
    }

    public  int Health()
    {
        return currentHealth;
    }
}
