public class Monster extends Enemy {
    private Integer visionRange;
    public Monster(Integer x, Integer y, char tile, String name, Integer healthPool, Integer healthAmount, Integer attack, Integer defence, Integer expValue, Integer visionRange) {
        super(x,y,tile,name,healthPool,healthAmount,attack,defence,expValue);
        this.visionRange = visionRange;
    }


}
