abstract public class Unit extends Tile {
    private String name;
    private Integer healthPool;
    private Integer healthAmount;
    private Integer attack;
    private Integer defence;
    private Level level;
    public Unit(Integer x, Integer y, char tile,String name, Integer healthPool, Integer healthAmount,Integer attack, Integer defence, Level level){
        super(tile,x,y);
        this.name = name;
        this.healthAmount = healthAmount;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defence = defence;
        this.level = level;
    }
}
