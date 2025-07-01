public class Boss extends Monster implements HeroicUnit {
    private static final String BOSS_ABILITY = "Shoebodybop";
    private int abilityFreq;
    private int combatTicks;

    public Boss(char tile, String name, int healthPool, int attack, int defence,
                int expValue, int visionRange, int abilityFreq, Color color) {
        super(tile, name, healthPool, attack, defence, expValue, visionRange,color);
        this.abilityFreq = abilityFreq;
        this.combatTicks = 0;
    }

    @Override
    public void castAbility() {
        //Assuming the player is in vision range
        Player p = board.getPlayer();
        onCastMsg(p.getName());
        p.defend(this, attack, abilityCallback);
    }

    @Override
    public String getAbilityName() {
        return BOSS_ABILITY;
    }

    @Override
    public void takeTurn() {
        Player p = board.getPlayer();
        if(getRange(p) < visionRange){
            if(combatTicks == abilityFreq){
                combatTicks = 0;
                castAbility();
            }
            else{
                combatTicks++;
                moveChase();
            }
        }
        else{
            combatTicks = 0;
            moveRandom();
        }
    }

    @Override
    public void onCastMsg(String targetName) {
        displayCallBack.send(String.format("%s cast %s on %s.",getName(), getAbilityName(), targetName));
    }
}
