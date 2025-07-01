package control.initializers;

import utils.callbacks.*;
import model.tiles.*;
import model.tiles.units.enemies.*;
import model.tiles.units.players.*;
import model.level.Board;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class TileFactory {
    private static final Map<Character, Supplier<Enemy>> ENEMIES =
            Map.ofEntries(
                    // ────── Monsters ──────
                    Map.entry('s', () -> new Monster('s', "Lannister Soldier", 80,  8,  3,  25, 3, Tile.Color.DARK_RED)),
                    Map.entry('k', () -> new Monster('k', "Lannister Knight",  200, 14,  8,  50, 4, Tile.Color.GOLD)),
                    Map.entry('q', () -> new Monster('q', "Queen's Guard",     400, 20, 15, 100, 5, Tile.Color.LILAC)),
                    Map.entry('z', () -> new Monster('z', "Wright",            600, 30, 15, 100, 3, Tile.Color.CYAN)),
                    Map.entry('b', () -> new Monster('b', "Bear‑Wright",      1000, 75, 30, 250, 4, Tile.Color.BROWN)),
                    Map.entry('g', () -> new Monster('g', "Giant‑Wright",     1500,100, 40, 500, 5, Tile.Color.BLUE)),
                    Map.entry('w', () -> new Monster('w', "White Walker",     2000,150, 50,1000, 6, Tile.Color.LIME)),

                    // ────── Bosses ──────
                    Map.entry('M', () -> new Boss('M', "The Mountain", 1000, 60, 25,  500, 6, 5, Tile.Color.PINK)),
                    Map.entry('C', () -> new Boss('C', "Queen Cersei",  100, 10, 10, 1000, 1, 8, Tile.Color.ROYAL_PURPLE)),
                    Map.entry('K', () -> new Boss('K', "Night's King", 5000,300,150,5000, 8, 3, Tile.Color.POISON_GREEN)),

                    // ────── Traps ──────
                    Map.entry('B', () -> new Trap('B', "Bonus model.tiles.units.enemies.Trap",      1,  1,  1, 250, 1, 5, Tile.Color.YELLOW)),
                    Map.entry('Q', () -> new Trap('Q', "Queen's model.tiles.units.enemies.Trap",   250, 50, 10, 100, 3, 7, Tile.Color.BLOOD_PURPLE)),
                    Map.entry('D', () -> new Trap('D', "Death model.tiles.units.enemies.Trap",     500,100, 20, 250, 1,10, Tile.Color.ASH_GREY))
            );
    private static final List<Supplier<Player>> PLAYERS = Arrays.asList(
            () -> new Warrior("Jon Snow",300,30,4,3, Tile.Color.TEAL),
            () -> new Warrior("The Hound",400,20,6,5, Tile.Color.VIOLET),
            () -> new Mage("Melisandre",100,5,1,300,30,15,5,6, Tile.Color.PURPLE),
            () -> new Mage("Thoros of Myr",250,25,4,150,20,20,3,4, Tile.Color.ORANGE),
            () -> new Rouge("Arya Stark", 150,40,2,20, Tile.Color.GREEN),
            () -> new Rouge("Bronn",250,35,3,50, Tile.Color.WHITE),
            () -> new Hunter("Ygritte",220,30,2,6, Tile.Color.GOLD)
    );
    public TileFactory(){}
    public Player producePlayer(int playerID){
        Supplier<Player> supp = PLAYERS.get(playerID-1);
        return supp.get();
    }
    public Enemy produceEnemy(char tile, int x, int y, DamageCallBack combat, DamageCallBack ability, MessageCallBack display, Board board){
        Enemy e = ENEMIES.get(tile).get();
        e.init(x,y,board,display,combat,ability);
        return e;
    }
    public Tile produceEmpty(int x, int y){
        return new Empty().init(x,y);
    }

    public Tile produceWall(int x, int y){
        return new Wall().init(x,y);
    }

    public List<Player> getDummyPlayers(){
        LinkedList<Player> players = new LinkedList<>();
        for(Supplier<Player> supplier : PLAYERS){
            players.addLast(supplier.get());
        }
        return players;
    }
}
