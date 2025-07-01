package model.tiles.units.enemies;

import control.initializers.LevelInitializer;
import model.tiles.Tile;
import model.tiles.units.GeneralUnitTest;
import model.tiles.units.players.Mage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterMovementTest extends GeneralUnitTest {
    private List<Enemy> enemies;
    private Enemy e;

    @Before
    public void setUp(){
        testLevel = new File("./DnD_OOP/tests/testLevels/level3.txt");
        player = new Mage("test player", 1000, 300, 5, 300, 40, 40000, 5, 10);
        board = new LevelInitializer(player).initializeLevel(testLevel).getBoard();//Assuming levelInitializer works
        pX = player.getX();//2
        pY = player.getY();//2
        e = ((Enemy)board.getTile(1,1)); //We know this tiles holds a Lannister Soldier
        //w.K.w
        //.s...
        //K.@.K
        //.#...
        //w.K.w

    }

    @Test
    public void enemyMovementChoiceTest(){
        enemies = board.getEnemies();
        enemies.sort((e1, e2) -> {
            if (e1.getX() != e2.getX()) return Integer.compare(e1.getX(), e2.getX());
            return Integer.compare(e1.getY(), e2.getY());
        }); //sorts by x and then y
        enemies.remove(e);

        for(Enemy e : enemies){
            e.takeTurn();
        }

        Assert.assertEquals(board.getTile(0,1), enemies.get(0));
        Assert.assertEquals(board.getTile(1,2), enemies.get(1));
        Assert.assertEquals(board.getTile(0,3), enemies.get(2));
        Assert.assertEquals(board.getTile(2,1), enemies.get(3));
        Assert.assertEquals(board.getTile(2,3), enemies.get(4));
        Assert.assertEquals(board.getTile(4,1), enemies.get(5));
        Assert.assertEquals(board.getTile(3,2), enemies.get(6));
        Assert.assertEquals(board.getTile(4,3), enemies.get(7));

        //.....
        //wsK.w
        //.K@K.
        //w#K.w
        //.....
    }

    @Test
    public void wallBlockTest(){

        e.moveDown();
        e.moveDown();

        Assert.assertEquals(1, e.getX());
        Assert.assertEquals(2, e.getY());
        Assert.assertEquals(board.getTile(1,2), e);
    }

    @Test
    public void enemyBlockTest(){

        e.moveLeft();
        e.moveUp();

        Assert.assertEquals(0, e.getX());
        Assert.assertEquals(1, e.getY());
        Assert.assertEquals(board.getTile(0, 1), e);
    }
    @Test
    public void playerBlockTest(){

        e.moveRight();
        e.moveDown();

        Assert.assertEquals(2, e.getX());
        Assert.assertEquals(1, e.getY());
        Assert.assertEquals(board.getTile(2, 1), e);
        Assert.assertEquals(board.getTile(2, 2), player);
    }

    @Test
    public void outOfBoundsHeightBlockTest(){

        e.moveUp();
        e.moveUp();

        Assert.assertEquals(1, e.getX());
        Assert.assertEquals(0, e.getY());
        Assert.assertEquals(board.getTile(1, 0), e);
    }

    @Test
    public void outOfBoundsWidthBlockTest(){

        e.moveRight();
        e.moveRight();
        e.moveRight();
        e.moveRight();

        Assert.assertEquals(4, e.getX());
        Assert.assertEquals(1, e.getY());
        Assert.assertEquals(board.getTile(4, 1), e);
    }
}