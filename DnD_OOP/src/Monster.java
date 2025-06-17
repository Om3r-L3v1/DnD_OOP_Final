public class Monster extends Enemy implements MovableUnit {
    protected int visionRange;
    public Monster(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int expValue, int visionRange) {
        super(x,y,tile,name,currentLevel,healthPool,healthAmount,attack,defence,expValue);
        this.visionRange = visionRange;
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }
}
