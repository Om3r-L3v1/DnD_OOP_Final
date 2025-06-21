import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Player extends Unit implements HeroicUnit {
    public static final char PLAYER_CHAR = '@';
    public static final char PLAYER_DEAD_CHAR = 'X';
    protected int experience;
    protected int level;
    protected String abilityName;

    public Player(int x, int y, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence) {
        super(x, y, PLAYER_CHAR, name, currentLevel, healthPool, healthAmount, attack, defence);
        this.experience = 0;
        this.level = 1;
    }

    @Override
    public void castAbility() {

    }

    protected abstract boolean canCast();

    @Override
    public boolean canMoveOn(Player p){
        return false;
    }

    @Override
    public boolean canMoveOn(Monster m){
        m.attack(this);
        if(isDead()){
            tile = PLAYER_DEAD_CHAR;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Tile target){
        return target.canMoveOn(this);
    }
    @Override
    public void defend(Player p, int damage){}
    @Override
    public void defend(Enemy m, int damage){
        Random rnd = new Random();
        int defence = rnd.nextInt(this.defence+1);
        if(defence<damage)
            takeDamage(damage - defence);
        //damage of (damage - defence) caused
        //health remaining

    }
    @Override
    public void attack(Enemy m){
        //monster name is attacking player name
        //you role the attack you got x
        //the defender rolled y
        Random rnd = new Random();
        int damage = rnd.nextInt(m.attack+1);
        m.defend(this, damage);
    }
    @Override
    public void attack(Player p){

    }
    public abstract void gameTick();

    public void gainExperience(int expValue){
        experience += expValue;
        if(experience >= 50 * level)
            levelUp();
    }

    protected void levelUp(){
        experience -= 50 * level;
        level++;
        healthPool += 10 * level;
        healthAmount = healthPool;
        attack += 4 * level;
        defence += level;
    };

    @Override
    public String getAbilityName(){
        return abilityName;
    }

    protected List<Enemy> getEnemiesInRange(int range, boolean inclusive){
        List<Enemy> inRangeEnemies = new LinkedList<>();
        for(Enemy e : currentlevel.getEnemies()){
            int enemyRange = getRange(e);
            if(enemyRange < range || (inclusive && enemyRange == range)){
                inRangeEnemies.add(e);
            }
        }
        return inRangeEnemies;
    }
}
