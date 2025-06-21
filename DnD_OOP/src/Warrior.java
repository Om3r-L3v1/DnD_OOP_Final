import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    final String warriorAbility = "Avenger's Shield";
    final int rangeAbility = 3;
    private int abillityCoolDown;
    private int remainingCoolDown;
    public Warrior(int x, int y, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int abillityCoolDown) {
        super(x, y, name, currentLevel, healthPool, healthAmount, attack, defence);
        super.abilityName = warriorAbility;
        this.remainingCoolDown = 0;
        this.abillityCoolDown = abillityCoolDown;
    }
    public void levelUp(){
        experience -=50*level;
        level++;
        remainingCoolDown = 0;
        healthPool += this.level*15;
        healthAmount = healthPool;
        attack += 6*level;
        defence += 2*level;
    }
    @Override
    public void castAbility() {
        //temporary
        if(canCast()) {
            remainingCoolDown = abillityCoolDown;
            healthAmount = Math.min(healthAmount+10*defence, healthPool);
            List<Enemy> inRangeEnemies = new LinkedList<>();
            for(Enemy e : currentlevel.getEnemies()){
                if(this.getRange(e)<rangeAbility){
                    inRangeEnemies.add(e);
                }
            }
            if(inRangeEnemies.size()>0){
                Random rnd = new Random();
                int index = rnd.nextInt(inRangeEnemies.size());
                Enemy enemy = inRangeEnemies.get(index);
                enemy.setHealthAmount(enemy.getHealthAmount()-(int)Math.ceil(healthPool/10.0));
            }
        }
    }
    public boolean canCast(){
        if(remainingCoolDown == 0) return true;
        return false;
    }



}
