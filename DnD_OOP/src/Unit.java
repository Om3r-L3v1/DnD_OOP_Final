abstract public class Unit extends Tile {
    protected String name;
    protected Level currentlevel;
    protected int healthPool;
    protected int healthAmount;
    protected int attack;
    protected int defence;

    public Unit(int x, int y, char tile,String name, Level currentlevel, int healthPool, int healthAmount,int attack, int defence){
        super(tile,x,y);
        this.name = name;
        this.healthAmount = healthAmount;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defence = defence;
        this.currentlevel = currentlevel;
    }

    public void moveUp() {
        if(y != 0){
            Tile[][] tiles = currentlevel.getTiles();
            Tile target = tiles[x][y - 1];
            if(canMoveTo(target)){
                tiles[x][y] = new Empty(x, y);
                tiles[x][y - 1] = this;
                y--;
            }
        }
    }

    public void moveDown() {
        if(y != currentlevel.getTiles()[0].length){
            Tile[][] tiles = currentlevel.getTiles();
            Tile target = tiles[x][y + 1];
            if(canMoveTo(target)){
                tiles[x][y] = new Empty(x, y);
                tiles[x][y + 1] = this;
                y++;
            }
        }
    }

    public void moveLeft() {
        if(x != 0){
            Tile[][] tiles = currentlevel.getTiles();
            Tile target = tiles[x - 1][y];
            if(canMoveTo(target)){
                tiles[x][y] = new Empty(x, y);
                tiles[x - 1][y] = this;
                x--;
            }
        }
    }

    public void moveRight() {
        if(x != currentlevel.getTiles().length){
            Tile[][] tiles = currentlevel.getTiles();
            Tile target = tiles[x + 1][y];
            if(canMoveTo(target)){
                tiles[x][y] = new Empty(x, y);
                tiles[x + 1][y] = this;
                x++;
            }
        }
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
        this.healthAmount = Math.max(0, Math.min(getHealthPool(), healthAmount));
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

    public abstract void defend(Player p);
    public abstract void defend(Enemy m);

    public abstract boolean canMoveTo(Tile target);
}
