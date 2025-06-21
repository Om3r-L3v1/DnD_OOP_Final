import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Player extends Unit implements HeroicUnit {
    public static final char PLAYER_CHAR = '@';
    public static final char PLAYER_DEAD_CHAR = 'X';
    protected static final int LEVEL_UP_EXP = 50;
    protected static final int HEALTH_POOL_GAIN = 10;
    protected static final int ATTACK_GAIN = 4;
    protected static final int DEFENCE_GAIN = 1;

    protected int experience;
    protected int level;

    public Player(int x, int y, String name, Board board, int healthPool, int healthAmount, int attack, int defence) {
        super(x, y, PLAYER_CHAR, name, board, healthPool, healthAmount, attack, defence);
        this.experience = 0;
        this.level = 1;
    }

    protected int getLevelUpExp(){return LEVEL_UP_EXP;}
    protected int getHealthPoolGain(){return HEALTH_POOL_GAIN;}
    protected int getAttackGain(){return ATTACK_GAIN;}
    protected int getDefenceGain(){return DEFENCE_GAIN;}

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
        if(experience >= getLevelUpExp() * level)
            levelUp();
    }

    protected void levelUp(){
        experience -= getLevelUpExp() * level;
        level++;
        healthPool += getHealthPoolGain() * level;
        healthAmount = healthPool;
        attack += getAttackGain() * level;
        defence += getDefenceGain() * level;
    }

    protected List<Enemy> getEnemiesInRange(int range, boolean inclusive){
        List<Enemy> inRangeEnemies = new LinkedList<>();
        for(Enemy e : board.getEnemies()){
            int enemyRange = getRange(e);
            if(enemyRange < range || (inclusive && enemyRange == range)){
                inRangeEnemies.add(e);
            }
        }
        return inRangeEnemies;
    }
}
