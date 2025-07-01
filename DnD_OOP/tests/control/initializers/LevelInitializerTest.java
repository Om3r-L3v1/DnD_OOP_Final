package control.initializers;

import model.level.Board;
import model.level.Level;
import model.tiles.Tile;
import model.tiles.units.players.Player;
import model.tiles.units.players.Warrior;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

public class LevelInitializerTest {
    private LevelInitializer levelInitializer;
    private Player testPlayer;
    private File level1;

    @Before
    public void setUp(){
        testPlayer = new Warrior("Test Player", 10, 10000, 1000, 3, Tile.Color.GREEN);
        levelInitializer = new LevelInitializer(testPlayer);
        level1 = new File("./DnD_OOP/tests/testLevels/level1.txt");
    }

    @Test
    public void fileParseTest(){
        String expectedBoard = "#################################################\n" +
                "#....s...###..........................#.........#\n" +
                "#........#B#....##..........##........#.........#\n" +
                "#........#......##..........##........#.........#\n" +
                "#........#.............g..............#....M....#\n" +
                "#........#............................#.........#\n" +
                "#....w...#......##..........##........#.........#\n" +
                "#........#......##s........k##........#.........#\n" +
                "#........#s.................##.......k#.........#\n" +
                "#@...............z...........Q.............C...q#\n" +
                "#....w...#s.................##.......k#.........#\n" +
                "#........#......##s........k##........#.........#\n" +
                "#........#......##..........##........#.........#\n" +
                "#........#............................#....K....#\n" +
                "#........#............................#.........#\n" +
                "#........#......##......b...##........#.........#\n" +
                "#........#B#....##..........##........#.........#\n" +
                "#....s...###.........................D#.........#\n" +
                "#################################################\n" ;

        Level level = levelInitializer.initializeLevel(level1);
        String actualBoard = "";
        Board b = level.getBoard();
        //colorless board
        for (int y = 0; y < b.height(); y++) {
            for (int x = 0; x < b.width(); x++) {
                actualBoard += b.getTile(x,y).getTile();
            }
            actualBoard += "\n";
        }

        Assert.assertEquals(expectedBoard, actualBoard);
    }

    @Test
    public void playerPositionTest(){
        int expectedX = 1, expectedY = 9;
        Level level = levelInitializer.initializeLevel(level1);

        Assert.assertEquals(expectedX, testPlayer.getX());
        Assert.assertEquals(expectedY, testPlayer.getY());
        Assert.assertEquals(testPlayer, level.getBoard().getTile(expectedX,expectedY));
    }
}