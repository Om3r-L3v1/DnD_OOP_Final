abstract public class Unit extends Tile {
    protected String name;
    protected Board board;
    protected int healthPool;
    protected int healthAmount;
    protected int attack;
    protected int defence;
    protected MessageCallBack callBack;

    public Unit(int x, int y, char tile, String name, Board board, int healthPool, int healthAmount, int attack, int defence){
        super(tile,x,y);
        this.name = name;
        this.healthAmount = healthAmount;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defence = defence;
        this.board = board;
        callBack = CLI::display;
    }

    public void moveUp() {
        if(y != 0){
            Tile target = board.getTile(x, y-1);
            if(canMoveTo(target)){
                board.swapPlaces(this, target);
            }
        }
    }

    public void moveDown() {
        if(y != board.height()){
            Tile target = board.getTile(x, y+1);
            if(canMoveTo(target)){
                board.swapPlaces(this, target);
            }
        }
    }

    public void moveLeft() {
        if(x != 0){
            Tile target = board.getTile(x-1, y);
            if(canMoveTo(target)){
                board.swapPlaces(this, target);
            }
        }
    }

    public void moveRight() {
        if(x != board.width()){
            Tile target = board.getTile(x+1, y);
            if(canMoveTo(target)){
                board.swapPlaces(this, target);
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

    public void takeDamage(int damageTaken) {
        this.healthAmount = Math.max(0, healthAmount - damageTaken);
        if(isDead())
            onDeathMsg();
    }

    public void heal(int healAmount){
        this.healthAmount = Math.min(healthPool, healthAmount + healAmount);
    }

    public boolean isDead(){return healthAmount == 0;}

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

    public String description(){
        String result = String.format("Health: %d/%d\tAttack: %d\tDefence: %d\t",
                healthAmount, healthPool, attack, defence);
        return result;
    }

    public abstract void defend(Player p, int damage);
    public abstract void defend(Enemy m, int damage);
    public abstract void attack(Player p);
    public abstract void attack(Enemy m);

    public abstract boolean canMoveTo(Tile target);

    protected abstract void onDeathMsg();
    protected void descMsg(){
        callBack.send(getName() + "\t" + description());
    }
}
