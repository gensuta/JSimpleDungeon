public class HealthHaver {
    private int maxHealth;
    private int currentHealth;

    public HealthHaver(){
    }

    public HealthHaver(int maxHealth){
        this.setMaxHealth(maxHealth);
        this.setCurrentHealth(maxHealth);
    }

   public void IncreaseHealth(int num){
        setCurrentHealth(getCurrentHealth() + num);
        setCurrentHealth(clamp(getCurrentHealth(),0, getMaxHealth()));
   }
    public void DecreaseHealth(int num){
        setCurrentHealth(getCurrentHealth() - num);
        setCurrentHealth(clamp(getCurrentHealth(),0, getMaxHealth()));
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

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
