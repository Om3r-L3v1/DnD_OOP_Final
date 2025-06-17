import java.util.Random;

public class Enemy extends Unit {
    int expValue;

    public Enemy(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int expValue) {
        super(x,y,tile,name,currentLevel,healthPool,healthAmount,attack,defence);
        this.expValue = expValue;
    }
    @Override
    public void defend(Player p){
        //player name is attacking monster name
        //you role the attack you got x
        //the defender roled y
        Random rnd = new Random();
        int damage = rnd.nextInt(p.attack+1);
        int defence = rnd.nextInt(this.defence+1);
        if(healthAmount-damage+defence<=0) {
            healthAmount = 0;
            currentlevel.getEnemies().remove(this);
        }
        else if(defence<damage)
            healthAmount -= damage-defence;
        //damage of damage - defence caused
        //health remaining
    }
    @Override
    public void defend(Enemy m){

    }
    public void takeTurn(){

    }
}