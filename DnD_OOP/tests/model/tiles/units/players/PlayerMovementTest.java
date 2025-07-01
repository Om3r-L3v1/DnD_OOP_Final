package model.tiles.units.players;

import control.initializers.LevelInitializer;
import model.level.*;
import model.tiles.Tile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


public class PlayerMovementTest{
    protected Board board;
    protected Player player;
    protected File level2;
    private int startX,startY;

    @Before
    public void setUp(){
        level2 = new File("./DnD_OOP/tests/testLevels/level2.txt");
        player = new Mage("test player", 10, 0, 1000, 300, 40, 40000, 5, 10, Tile.Color.GOLD);
        board = new LevelInitializer(player).initializeLevel(level2).getBoard();//Assuming levelInitializer works
        startX = player.getX();
        startY = player.getY();

        //....
        //z.@.
        //....
        //..#.
    }
    @Test
    public void moveUpTest(){

        player.moveUp();

        Assert.assertEquals(startX, player.getX());
        Assert.assertEquals(startY - 1, player.getY());
        Assert.assertEquals(board.getTile(startX, startY - 1), player);
    }
    @Test
    public void moveDownTest(){

        player.moveDown();

        Assert.assertEquals(startX, player.getX());
        Assert.assertEquals(startY + 1, player.getY());
        Assert.assertEquals(board.getTile(startX, startY + 1), player);
    }
    @Test
    public void moveLeftTest(){

        player.moveLeft();

        Assert.assertEquals(startX - 1, player.getX());
        Assert.assertEquals(startY, player.getY());
        Assert.assertEquals(board.getTile(startX - 1, startY), player);
    }
    @Test
    public void moveRightTest(){

        player.moveRight();

        Assert.assertEquals(startX + 1, player.getX());
        Assert.assertEquals(startY, player.getY());
        Assert.assertEquals(board.getTile(startX + 1, startY), player);
    }

    @Test
    public void wallBlockTest(){

        player.moveDown();
        player.moveDown();

        Assert.assertEquals(startX, player.getX());
        Assert.assertEquals(startY + 1, player.getY());
        Assert.assertEquals(board.getTile(startX, startY + 1), player);
    }

    @Test
    public void enemyBlockTest(){

        player.moveLeft();
        player.moveLeft();

        Assert.assertEquals(startX - 1, player.getX());
        Assert.assertEquals(startY, player.getY());
        Assert.assertEquals(board.getTile(startX - 1, startY), player);
    }

    @Test
    public void outOfBoundsHeightBlockTest(){

        player.moveUp();
        player.moveUp();

        Assert.assertEquals(startX, player.getX());
        Assert.assertEquals(startY - 1, player.getY());
        Assert.assertEquals(board.getTile(startX, startY - 1), player);
    }

    @Test
    public void outOfBoundsWidthBlockTest(){

        player.moveRight();
        player.moveRight();

        Assert.assertEquals(startX + 1, player.getX());
        Assert.assertEquals(startY, player.getY());
        Assert.assertEquals(board.getTile(startX + 1, startY), player);
    }
}