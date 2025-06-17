import java.util.Random;
public class Monster extends Enemy implements MovableUnit {
    protected int visionRange;
    public Monster(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int expValue, int visionRange) {
        super(x,y,tile,name,currentLevel,healthPool,healthAmount,attack,defence,expValue);
        this.visionRange = visionRange;
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {
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
    public void defend(Monster m){

    }
    @Override
    public void defend(Trap t){

    }
}
