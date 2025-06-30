import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class TileFactory {
    private static final Map<Character, Supplier<Enemy>> enemies =
            Map.ofEntries(
                    // ────── Monsters ──────
                    Map.entry('s', () -> new Monster('s', "Lannister Soldier", 80,  80,  8,  3,  25, 3)),
                    Map.entry('k', () -> new Monster('k', "Lannister Knight",  200, 200, 14,  8,  50, 4)),
                    Map.entry('q', () -> new Monster('q', "Queen's Guard",     400, 400, 20, 15, 100, 5)),
                    Map.entry('z', () -> new Monster('z', "Wright",            600, 600, 30, 15, 100, 3)),
                    Map.entry('b', () -> new Monster('b', "Bear‑Wright",      1000,1000, 75, 30, 250, 4)),
                    Map.entry('g', () -> new Monster('g', "Giant‑Wright",     1500,1500,100, 40, 500, 5)),
                    Map.entry('w', () -> new Monster('w', "White Walker",     2000,2000,150, 50,1000, 6)),

                    // ────── Bosses ──────
                    Map.entry('M', () -> new Boss('M', "The Mountain", 1000,1000, 60, 25,  500, 6, 5)),
                    Map.entry('C', () -> new Boss('C', "Queen Cersei",  100, 100, 10, 10, 1000, 1, 8)),
                    Map.entry('K', () -> new Boss('K', "Night's King", 5000,5000,300,150,5000, 8, 3)),

                    // ────── Traps ──────
                    Map.entry('B', () -> new Trap('B', "Bonus Trap",      1,   1,  1,  1, 250, 1, 5)),
                    Map.entry('Q', () -> new Trap('Q', "Queen's Trap",   250, 250, 50, 10, 100, 3, 7)),
                    Map.entry('D', () -> new Trap('D', "Death Trap",     500, 500,100, 20, 250, 1,10))
            );
    private static final List<Supplier<Player>> players = Arrays.asList(
            () -> new Mage("Melisandre",100,100,5,1,300,30,15,5,6),
            () -> new Mage("Thoros of Myr",250,250,25,4,150,20,20,3,4),
            () -> new Warrior("Jon Snow",300,300,30,4,3),
            () -> new Warrior("The Hound",400,400,20,6,5),
            () -> new Rouge("Arya Stark", 150,150,40,2,20),
            () -> new Rouge("Bronn",250,250,35,3,50),
            () -> new Hunter("Ygritte",220,220,30,2,6)
    );
    public TileFactory(){}
    public Player producePlayer(int playerID){
        Supplier<Player> supp = players.get(playerID-1);
        return supp.get();
    }
    public Enemy produceEnemy(char tile, int x,int y, DamageCallBack combat,DamageCallBack ability, MessageCallBack display, Board board){
        Enemy e = enemies.get(tile).get();
        e.init(x,y,board,display,combat,ability);
        return e;
    }
    public Tile produceEmpty(int x,int y){
        return new Empty().init(x,y);
    }

    public Tile produceWall(int x, int y){
        return new Wall().init(x,y);
    }

}
