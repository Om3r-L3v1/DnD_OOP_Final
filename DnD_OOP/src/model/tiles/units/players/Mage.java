package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.List;
import java.util.Random;

public class Mage extends Player {
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


    public Mage(String name, int healthPool, int attack, int defence,
                int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange, Color color) {
        super(name, healthPool, attack, defence, color);

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
    protected void cast(){
        onCastMsg(null);
        Random rnd = new Random();
        currentMana = currentMana - manaCost;
        int hits = 0;
        List<Enemy> inRangeEnemies = getEnemiesInRange(abilityRange, false);
        while (hits < hitsCount && !inRangeEnemies.isEmpty()) {
            Enemy target = inRangeEnemies.get(rnd.nextInt(inRangeEnemies.size()));
            target.defend(this, spellPower, abilityCallback);
            if (target.isDead())
                inRangeEnemies.remove(target);
            hits++;
        }
    }

    @Override
    protected String levelUpString(){
        return super.levelUpString() + String.format(", +%d Maximum Mana, +%d Spell Power"
                ,getManaPoolGain()*level,getSpellPowerGain()*level);
    }
    @Override
    public String description(){
        return super.description()+String.format("Mana: %d/%d\tSpell Power: %d\tMana Cost: %d\tHit Count: %d\tRange: %d\t"
                ,currentMana,manaPool,spellPower, manaCost, hitsCount, abilityRange );
    }
    @Override
    protected boolean canCast(){
        if(currentMana < manaCost){
            cantCastMsg(String.format("there was not enough mana: %d/%d.", currentMana, manaCost));
            return false;
        }
        return true;
    }
    @Override
    public void gameTick(){
        if(currentMana < manaPool)
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
        chargeManaMsg(amount);
    }

    @Override
    public void onCastMsg(String targetName) {
        displayCallBack.send(String.format("%s cast %s for %d mana, hitting %d times.",getName(),this.getAbilityName(), manaCost, hitsCount));
    }
    @Override
    protected void cantCastMsg(String reason) {
        displayCallBack.send(String.format("%s tried to cast %s, but %s", getName(), getAbilityName(), reason));
    }
    private void chargeManaMsg(int amount){
        displayCallBack.send(String.format("%s restored %d mana.", getName(), amount));
        if(currentMana == manaPool)
            fullManaMsg();
    }
    private void fullManaMsg(){
        displayCallBack.send(String.format("%s has full mana!.", getName()));
    }
}
