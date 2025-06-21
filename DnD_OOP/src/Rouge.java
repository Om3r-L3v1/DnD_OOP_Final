import java.util.LinkedList;
import java.util.List;

public class Rouge extends Player {

    final String rogueAbility = "Fan of Knives";
    final int rangeAbility = 2;
    final int energyMax = 100;
    private int cost;
    private int currentEnergy;

    public Rouge(int x, int y, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int cost) {
        super(x, y, name, currentLevel, healthPool, healthAmount, attack, defence);
        this.cost = cost;
        this.currentEnergy = energyMax;
    }

    public void levelUp() {
        experience -= 50 * level;
        level++;
        currentEnergy = energyMax;
        attack += 7 * level;
        defence += level;
        healthAmount = healthPool;
    }

    public boolean canCast() {
        if (currentEnergy < cost) {
            return false;
        }
        return true;
    }

    @Override
    public void castAbility() {
        if (canCast()) {
            currentEnergy -= cost;
            for (Enemy e : currentlevel.getEnemies()) {
                if (this.getRange(e) < rangeAbility) {
                    e.defend(this);
                }
            }
        }
    }
}

