public class Boss extends Monster implements HeroicUnit {
    private static final String BOSS_ABILITY = "Shoebodybop";
    private int abilityFreq;
    private int combatTicks;

    public Boss(int x, int y, char tile, String name, Board board, int healthPool, int healthAmount, int attack, int defence,
                int expValue, int visionRange, int abilityFreq) {
        super(x, y, tile, name, board, healthPool, healthAmount, attack, defence, expValue, visionRange);
        this.abilityFreq = abilityFreq;
        this.combatTicks = 0;
    }

    @Override
    public void castAbility() {
        //Assuming the player is in vision range
        Player p = board.getPlayer();
        p.defend(this, attack, ABILITY_CALLBACK);
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
}
