public class Trap extends Enemy {
    private final int ATTACK_RANGE = 2;

    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char tile, String name, int healthPool, int attack, int defence, int expValue,
                   int visibilityTime, int invisibilityTime, Color color) {
        super(tile,name,healthPool,attack,defence,expValue,color);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.visible = true;
        this.ticksCount = 0;
    }

    @Override
    public boolean canMoveTo(Tile target){
        return false;
    }

    @Override
    public void takeTurn() {
        visible = ticksCount < visibilityTime;
        if(ticksCount == (visibilityTime + invisibilityTime))
            ticksCount = 0;
        else
            ticksCount++;
        Player p = board.getPlayer();
        if(getRange(p) < ATTACK_RANGE){
            attack(p);
        }
    }

    @Override
    public String toString(){
        return visible ? super.toString() : Empty.EMPTY_CHAR + "";
    }
}
