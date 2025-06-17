public class Player extends Unit implements MovableUnit, HeroicUnit {
    private int experience;
    private int level;

    public Player(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int experience, int level) {
        super(x, y, tile, name, currentLevel, healthPool, healthAmount, attack, defence);
        this.experience = experience;
        this.level = level;
    }

    @Override
    public void castAbility() {
        
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
