public class Monster extends Enemy {
    protected int visionRange;
    public Monster(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int expValue, int visionRange) {
        super(x,y,tile,name,currentLevel,healthPool,healthAmount,attack,defence,expValue);
        this.visionRange = visionRange;
    }

    @Override
    public boolean canMoveTo(Tile target){
        return target.canMoveOn(this);
    }
}
