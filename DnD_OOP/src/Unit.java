abstract public class Unit extends Tile {
    protected String name;
    protected Board board;
    protected int healthPool;
    protected int healthAmount;
    protected int attack;
    protected int defence;
    protected MessageCallBack displayCallBack;
    protected DamageCallBack abilityCallback;
    protected DamageCallBack combatCallback;

    public Unit(char tile, String name, int healthPool, int attack, int defence, Color color){
        super(tile,color);
        this.name = name;
        this.healthPool = healthPool;
        this.healthAmount = healthPool;
        this.attack = attack;
        this.defence = defence;
    }
    public Unit init (int x,int y,Board board,MessageCallBack display, DamageCallBack combat, DamageCallBack ability){
        init(x,y);
        this.board = board;
        this.abilityCallback = ability;
        this.displayCallBack = display;
        this.combatCallback = combat;
        return this;
    }

    public void moveUp() {
        if(y != 0){
            Tile target = board.getTile(x, y-1);
            if(canMoveTo(target)){
                board.swapPlaces(x,y, x,y-1);
            }
        }
    }

    public void moveDown() {
        if(y != board.height()){
            Tile target = board.getTile(x, y+1);
            if(canMoveTo(target)){
                board.swapPlaces(x,y, x,y+1);
            }
        }
    }

    public void moveLeft() {
        if(x != 0){
            Tile target = board.getTile(x-1, y);
            if(canMoveTo(target)){
                board.swapPlaces(x,y, x-1,y);
            }
        }
    }

    public void moveRight() {
        if(x != board.width()){
            Tile target = board.getTile(x+1, y);
            if(canMoveTo(target)){
                board.swapPlaces(x,y,x+1,y);
            }
        }
    }

    public String getName() {
        return color.wrap(name);
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

    public void takeDamage(int damageTaken, Unit dealer) {
        this.healthAmount = Math.max(0, healthAmount - damageTaken);
        if(isDead())
            onDeathMsg(dealer);
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

    public abstract void defend(Player p, int damage, DamageCallBack dcb);
    public abstract void defend(Enemy m, int damage, DamageCallBack dcb);
    public abstract void attack(Player p);
    public abstract void attack(Enemy m);

    public abstract boolean canMoveTo(Tile target);

    protected abstract void onDeathMsg(Unit killer);
    protected void descMsg(){
        displayCallBack.send(getName() + "\t" + description());
    }
    protected void onCombatMsg(Unit target){
        displayCallBack.send(String.format("%s engaged in combat with %s.", getName(), target.getName()));
        descMsg();
        target.descMsg();
    }
    protected void attackRollMsg(int attack){
        displayCallBack.send(String.format("%s rolled %d attack points.", getName(), attack));
    }
    protected void defenceRollMsg(int defence){
        displayCallBack.send(String.format("%s rolled %d defence points.", getName(), defence));
    }
}
