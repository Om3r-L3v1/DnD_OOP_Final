import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Mage extends Player{
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;
    private final String MageAbility = "Blizzard";

    public Mage(int x, int y, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int experience, int level,
                int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(x, y, name, currentLevel, healthPool, healthAmount, attack, defence, experience, level);

        this.manaPool = manaPool;
        this.currentMana = (int)Math.ceil(manaPool / 4.0);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        this.abilityName = MageAbility;
    }

    @Override
    public void castAbility() {
        if(currentMana < manaCost){
            //print
            return;
        }
        Random rnd = new Random();
        currentMana = currentMana - manaCost;
        int hits = 0;
        List<Enemy> inRangeEnemies = new LinkedList<>();
        for(Enemy e : currentlevel.getEnemies()){
            if(getRange(e) < abilityRange){
                inRangeEnemies.add(e);
            }
        }
        int regAttack = attack;
        attack = spellPower;
        while(hits < hitsCount ^ !inRangeEnemies.isEmpty()){
            Enemy target = inRangeEnemies.get(rnd.nextInt(inRangeEnemies.size()));
            target.defend(this);
            hits++;
        }
        attack = regAttack;
    }
}
