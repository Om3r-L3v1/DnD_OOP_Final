import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    private final String WarriorAbility = "Avenger's Shield";
    private final int AbilityRange = 3;
    private int abilityCooldown;
    private int remainingCooldown;
    public Warrior(int x, int y, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int abilityCooldown) {
        super(x, y, name, currentLevel, healthPool, healthAmount, attack, defence);
        super.abilityName = WarriorAbility;
        this.remainingCooldown = 0;
        this.abilityCooldown = abilityCooldown;
    }

    @Override
    public void castAbility() {
        //temporary
        if(canCast()) {
            remainingCooldown = abilityCooldown;
            healthAmount = Math.min(healthAmount+10*defence, healthPool);
            List<Enemy> inRangeEnemies = getEnemiesInRange(AbilityRange, false);
            if(inRangeEnemies.size()>0){
                Random rnd = new Random();
                int index = rnd.nextInt(inRangeEnemies.size());
                Enemy enemy = inRangeEnemies.get(index);
                enemy.takeDamage((int)Math.ceil(healthPool/10.0));
            }
        }
    }
    @Override
    public void gameTick(){
        remainingCooldown=Math.max(0,remainingCooldown-1);
    }
    @Override
    protected boolean canCast(){
        return remainingCooldown == 0;
    }


    @Override
    protected void levelUp(){
        super.levelUp();
        remainingCooldown = 0;
        healthPool += 5 * level;
        attack += 2 * level;
        defence += level;
    }

}
