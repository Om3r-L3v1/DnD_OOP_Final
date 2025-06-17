import java.util.Random;

public class Player extends Unit implements MovableUnit, HeroicUnit {
    protected int experience;
    protected int level;

    public Player(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int experience, int level) {
        super(x, y, tile, name, currentLevel, healthPool, healthAmount, attack, defence);
        this.experience = experience;
        this.level = level;
    }

    @Override
    public void castAbility() {
        
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
    public void defend(Player p){}
    @Override
    public void defend(Enemy m){

        //monster name is attacking player name
        //you role the attack you got x
        //the defender roled y
        Random rnd = new Random();
        int damage = rnd.nextInt(m.attack+1);
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

}
