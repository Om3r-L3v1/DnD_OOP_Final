package control.initializers;

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

    @Before
    public void setUp(){
        testPlayer = new Warrior("Test Player", 10, 10000, 1000, 3, Tile.Color.GREEN);
        levelInitializer = new LevelInitializer(testPlayer);
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
                "#################################################" ;
        File file = new File("/tests/testLevels/level1.txt");

        Level level = levelInitializer.initializeLevel(file);

        Assert.assertEquals(expectedBoard, level.getBoard().toString());
    }
}