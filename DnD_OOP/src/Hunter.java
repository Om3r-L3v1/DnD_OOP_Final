import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Hunter extends Player{
    private int range;
    private int arrowsCount;
    private int ticksCount;
    private final String HunterAbility = "Shoot";


    public Hunter(int x, int y, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence,
    int range) {
        super(x, y, name, currentLevel, healthPool, healthAmount, attack, defence);
        this.range = range;
        this.arrowsCount = 10 * level;
        this.ticksCount = 0;
    }

    @Override
    public void castAbility(){
        if(arrowsCount == 0){ //or no one in range
            //print
            return;
        }
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
