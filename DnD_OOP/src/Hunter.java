import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Hunter extends Player{
    private static final String HUNTER_ABILITY = "Shoot";

    private int range;
    private int arrowsCount;
    private int ticksCount;

    public Hunter(int x, int y, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence,
    int range) {
        super(x, y, name, currentLevel, healthPool, healthAmount, attack, defence);
        this.range = range;
        this.arrowsCount = 10 * level;
        this.ticksCount = 0;
    }


    @Override
    public String getAbilityName(){
        return HUNTER_ABILITY;
    }
    @Override
    public void castAbility(){
        if(canCast()){
            arrowsCount--;
            Random rnd = new Random();
            List<Enemy> closest = new LinkedList<>();
            int closestRange = -1;
            for(Enemy e : currentlevel.getEnemies()) {
                int enemyRange = getRange(e);
                if(enemyRange <= range) {
                    if(enemyRange == closestRange)
                        closest.add(e);
                    else if (enemyRange < range) {
                        closest = new LinkedList<>();
                        closest.add(e);
                    }
                }
            }
            Enemy target = closest.get(rnd.nextInt(closest.size()));
            target.defend(this, attack);
        }
    }
    @Override
    protected boolean canCast(){
        return arrowsCount > 0 && !getEnemiesInRange(range, true).isEmpty();
    }

    @Override
    public void gameTick() {
        if (ticksCount == 10) {
            ticksCount = 0;
            arrowsCount += level;
        }
        else ticksCount++;
    }

    @Override
    protected void levelUp(){
        super.levelUp();
        arrowsCount += 10 * level;
        attack += 2 * level;
        defence += level;
    }
}
