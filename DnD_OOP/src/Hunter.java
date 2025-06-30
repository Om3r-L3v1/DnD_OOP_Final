import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Hunter extends Player{
    private static final String HUNTER_ABILITY = "Shoot";

    private int range;
    private int arrowsCount;
    private int ticksCount;

    public Hunter(String name, int healthPool, int healthAmount, int attack, int defence,
    int range) {
        super(name, healthPool, healthAmount, attack, defence);
        this.range = range;
        this.arrowsCount = 10 * level;
        this.ticksCount = 0;
    }


    @Override
    public String getAbilityName(){
        return HUNTER_ABILITY;
    }

    @Override
    protected void cast(){
        arrowsCount--;
        Random rnd = new Random();
        List<Enemy> closest = new LinkedList<>();
        int closestRange = -1;
        for(Enemy e : board.getEnemies()) {
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
        onCastMsg(target.getName());
        target.defend(this, attack, abilityCallback);
    }

    public String description(){
        return super.description()+String.format("Arrows: %d\tRange: %d\t",arrowsCount,range );
    }
    @Override
    protected boolean canCast(){
        if(arrowsCount == 0){
            cantCastMsg("there are no arrows in the quiver.");
            return false;
        }
        else if(getEnemiesInRange(range, true).isEmpty()){
            cantCastMsg("there were no enemies in range.");
            return false;
        }
        return true;
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

    @Override
    public void onCastMsg(String targetName) {
        displayCallBack.send(String.format("%s fired an arrow at %s.",getName(),targetName));
    }
    @Override
    public void cantCastMsg(String reason) {
        displayCallBack.send(String.format("%s tried to shoot an arrow, but %s",getName(),reason));
    }
}
