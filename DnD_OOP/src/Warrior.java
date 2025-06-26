import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    private static final String WARRIOR_ABILITY = "Avenger's Shield";
    private static final int ABILITY_RANGE = 3;
    private static final int HEALTH_POOL_EXTRA_GAIN = 5;
    private static final int ATTACK_EXTRA_GAIN = 2;
    private static final int DEFENCE_EXTRA_GAIN = 1;

    private int abilityCooldown;
    private int remainingCooldown;

    public Warrior(int x, int y, String name, Board board, int healthPool, int healthAmount, int attack, int defence, int abilityCooldown) {
        super(x, y, name, board, healthPool, healthAmount, attack, defence);
        this.remainingCooldown = 0;
        this.abilityCooldown = abilityCooldown;
    }

    @Override
    protected int getHealthPoolGain(){return HEALTH_POOL_GAIN + HEALTH_POOL_EXTRA_GAIN;}
    @Override
    protected int getAttackGain(){return ATTACK_GAIN + ATTACK_EXTRA_GAIN;}
    @Override
    protected int getDefenceGain(){return DEFENCE_GAIN + DEFENCE_EXTRA_GAIN;}

    @Override
    public String getAbilityName(){
        return WARRIOR_ABILITY;
    }

    @Override
    public void castAbility() {
        //temporary
        if(canCast()) {
            remainingCooldown = abilityCooldown;
            heal(10 * defence);
            List<Enemy> inRangeEnemies = getEnemiesInRange(ABILITY_RANGE, false);
            if(inRangeEnemies.size()>0){
                Random rnd = new Random();
                int index = rnd.nextInt(inRangeEnemies.size());
                Enemy enemy = inRangeEnemies.get(index);
                enemy.defend(this, (int)Math.ceil(healthPool/10.0), ABILITY_CALLBACK);
                enemy.takeDamage((int)Math.ceil(healthPool/10.0));
            }
        }
    }

    @Override
    public String description(){
        return super.description()+String.format("CoolDown: %d/%d",remainingCooldown,abilityCooldown );
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
    }

}
