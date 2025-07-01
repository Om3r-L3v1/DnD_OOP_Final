package control.initializers;

import model.level.Board;
import model.level.Level;
import model.tiles.*;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import utils.callbacks.*;
import view.CLI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class LevelInitializer {
    TileFactory tileFactory;
    Player player;
    private static final DamageCallBack COMBAT_CALLBACK = CLI::combatDamage;
    private static final DamageCallBack ABILITY_CALLBACK = CLI::abilityDamage;
    private static final MessageCallBack DISPLAY_CALLBACK = CLI::display;
    private static final InputCallBack INPUT_CALLBACK = CLI::actionInput;

    public LevelInitializer(Player p) {
        this.player = p;
    }
    public Level initializeLevel(File file) {
        char[][] characters = fileToChars(file);
        Tile[][] tiles = new Tile[characters.length][characters[0].length];
        Board board = new Board(player);
        List<Enemy> enemies = new LinkedList<>();
        for (int y = 0; y < characters.length; y++) {
            for (int x = 0; x < characters[y].length; x++) {
                tiles[y][x] = charToTile(characters[y][x], x, y, board, enemies);
            }
        }
        board.init(tiles, enemies);
        return new Level(player, board,DISPLAY_CALLBACK, INPUT_CALLBACK);
    }
    private Tile charToTile(char c, int x, int y, Board board, List<Enemy> enemies) {
        TileFactory tileFactory = new TileFactory();
        switch (c) {
            case Empty.EMPTY_CHAR:
                Tile empty = tileFactory.produceEmpty(x,y);
                return empty;
            case Wall.WALL_CHAR:
                Tile wall = tileFactory.produceWall(x,y);
                return wall;
            case Player.PLAYER_CHAR:
                player.init(x,y,board,DISPLAY_CALLBACK,COMBAT_CALLBACK,ABILITY_CALLBACK);
                return player;
            default:
                Enemy enemy = tileFactory.produceEnemy(c,x,y, COMBAT_CALLBACK,ABILITY_CALLBACK,DISPLAY_CALLBACK,board);
                enemies.add(enemy);
                return enemy;
        }
    }
    private char[][] fileToChars(File file) {
        if (file.isFile()) {
            try {
                List<String> lines = Files.readAllLines(file.toPath());

                char[][] matrix = new char[lines.size()][];
                for (int i = 0; i < lines.size(); i++) {
                    matrix[i] = lines.get(i).toCharArray();
                }
                return matrix;
            } catch (IOException e) {
                System.err.println("Error reading file: " + file.getName());
                e.printStackTrace();
            }
        }
        System.out.println("file is not file");
        return null;
    }
}
