package model.tiles.units.players;

import control.initializers.LevelInitializer;
import model.level.*;
import model.tiles.Tile;
import model.tiles.units.GeneralUnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


public class PlayerMovementTest extends GeneralUnitTest {

    @Before
    public void setUp(){
        testLevel = new File("./DnD_OOP/tests/testLevels/level2.txt");
        player = new Mage("test player", 10, 0, 1000, 300, 40, 40000, 5, 10);
        board = new LevelInitializer(player).initializeLevel(testLevel).getBoard();//Assuming levelInitializer works
        pX = player.getX();
        pY = player.getY();

        //....
        //z.@.
        //....
        //..#.
    }
    @Test
    public void moveUpTest(){

        player.moveUp();

        Assert.assertEquals(pX, player.getX());
        Assert.assertEquals(pY - 1, player.getY());
        Assert.assertEquals(board.getTile(pX, pY - 1), player);
    }
    @Test
    public void moveDownTest(){

        player.moveDown();

        Assert.assertEquals(pX, player.getX());
        Assert.assertEquals(pY + 1, player.getY());
        Assert.assertEquals(board.getTile(pX, pY + 1), player);
    }
    @Test
    public void moveLeftTest(){

        player.moveLeft();

        Assert.assertEquals(pX - 1, player.getX());
        Assert.assertEquals(pY, player.getY());
        Assert.assertEquals(board.getTile(pX - 1, pY), player);
    }
    @Test
    public void moveRightTest(){

        player.moveRight();

        Assert.assertEquals(pX + 1, player.getX());
        Assert.assertEquals(pY, player.getY());
        Assert.assertEquals(board.getTile(pX + 1, pY), player);
    }

    @Test
    public void wallBlockTest(){

        player.moveDown();
        player.moveDown();

        Assert.assertEquals(pX, player.getX());
        Assert.assertEquals(pY + 1, player.getY());
        Assert.assertEquals(board.getTile(pX, pY + 1), player);
    }

    @Test
    public void enemyBlockTest(){

        player.moveLeft();
        player.moveLeft();

        Assert.assertEquals(pX - 1, player.getX());
        Assert.assertEquals(pY, player.getY());
        Assert.assertEquals(board.getTile(pX - 1, pY), player);
    }

    @Test
    public void outOfBoundsHeightBlockTest(){

        player.moveUp();
        player.moveUp();

        Assert.assertEquals(pX, player.getX());
        Assert.assertEquals(pY - 1, player.getY());
        Assert.assertEquals(board.getTile(pX, pY - 1), player);
    }

    @Test
    public void outOfBoundsWidthBlockTest(){

        player.moveRight();
        player.moveRight();

        Assert.assertEquals(pX + 1, player.getX());
        Assert.assertEquals(pY, player.getY());
        Assert.assertEquals(board.getTile(pX + 1, pY), player);
    }
}