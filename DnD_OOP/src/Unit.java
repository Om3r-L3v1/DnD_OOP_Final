abstract public class Unit extends Tile {
    private String name;
    private Level level;
    private int healthPool;
    private int healthAmount;
    private int attack;
    private int defence;

    public Unit(int x, int y, char tile,String name, int healthPool, int healthAmount,int attack, int defence){
        super(tile,x,y);
        this.name = name;
        this.healthAmount = healthAmount;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defence = defence;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPool() {
        return healthPool;
    }

    public void setHealthPool(int healthPool) {
        this.healthPool = healthPool;
    }

    public int getHealthAmount() {
        return healthAmount;
    }

    public void setHealthAmount(int healthAmount) {
        this.healthAmount = healthAmount;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
