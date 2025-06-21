import java.util.List;
import java.util.Random;

public class Mage extends Player{
    private static final String MAGE_ABILITY = "Blizzard";
    private static final int MANA_POOL_GAIN = 25;
    private static final int SPELL_POWER_GAIN = 10;
    private static final double MANA_INIT_FACTOR = 0.25;

    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;


    public Mage(int x, int y, String name, Board board, int healthPool, int healthAmount, int attack, int defence,
                int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(x, y, name, board, healthPool, healthAmount, attack, defence);

        this.manaPool = manaPool;
        this.currentMana = getManaCharge();
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    private int getManaPoolGain(){return MANA_POOL_GAIN;}
    private int getSpellPowerGain(){return SPELL_POWER_GAIN;}
    private int getManaCharge(){return (int)Math.ceil(manaPool * MANA_INIT_FACTOR);}

    @Override
    public String getAbilityName(){
        return MAGE_ABILITY;
    }

    @Override
    public void castAbility() {
        if(canCast()) {
            Random rnd = new Random();
            currentMana = currentMana - manaCost;
            int hits = 0;
            List<Enemy> inRangeEnemies = getEnemiesInRange(abilityRange, false);
            while (hits < hitsCount && !inRangeEnemies.isEmpty()) {
                Enemy target = inRangeEnemies.get(rnd.nextInt(inRangeEnemies.size()));
                target.defend(this, spellPower);
                if (target.getHealthAmount() == 0)
                    inRangeEnemies.remove(target);
                hits++;
            }
        }
    }
    @Override
    protected boolean canCast(){
        return currentMana >= manaCost;
    }
    @Override
    public void gameTick(){
        chargeMana(level);
    }

    @Override
    protected void levelUp(){
        super.levelUp();
        manaPool += getManaPoolGain() * level;
        chargeMana(getManaCharge());
        spellPower += getSpellPowerGain() * level;
    }

    private void chargeMana(int amount){
        currentMana = Math.min(manaPool, currentMana + amount);
    }
}
