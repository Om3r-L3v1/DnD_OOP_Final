public class Monster extends Enemy implements MovableUnit {
    private int visionRange;
    public Monster(int x, int y, char tile, String name, int healthPool, int healthAmount, int attack, int defence, int expValue, int visionRange) {
        super(x,y,tile,name,healthPool,healthAmount,attack,defence,expValue);
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
