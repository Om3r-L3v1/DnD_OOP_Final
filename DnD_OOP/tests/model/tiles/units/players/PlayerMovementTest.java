package model.tiles.units.players;

import control.initializers.LevelInitializer;
import model.level.*;
import model.tiles.Tile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


public class PlayerMovementTest {
    private Board board;
    private Player player;
    private File level2;

    @Before
    public void setUp(){
        level2 = new File("./DnD_OOP/tests/testLevels/level2.txt");
        player = new Mage("test player", 10, 0, 1000, 300, 40, 40000, 5, 10, Tile.Color.GOLD);
        board = new LevelInitializer(player).initializeLevel(level2).getBoard();//Assuming levelInitializer works
    }

    @Test
    public void moveUpTest(){
        int startX = player.getX(), startY = player.getY();

        player.moveUp();

        Assert.assertEquals(startX, player.getX());
        Assert.assertEquals(startY - 1, player.getY());
        Assert.assertEquals(board.getTile(startX, startY - 1), player);
    }
    @Test
    public void moveDownTest(){
        int startX = player.getX(), startY = player.getY();

        player.moveDown();

        Assert.assertEquals(startX, player.getX());
        Assert.assertEquals(startY + 1, player.getY());
        Assert.assertEquals(board.getTile(startX, startY + 1), player);
    }
    @Test
    public void moveLeftTest(){
        int startX = player.getX(), startY = player.getY();

        player.moveLeft();

        Assert.assertEquals(startX - 1, player.getX());
        Assert.assertEquals(startY, player.getY());
        Assert.assertEquals(board.getTile(startX - 1, startY), player);
    }
    @Test
    public void moveRightTest(){
        int startX = player.getX(), startY = player.getY();

        player.moveRight();

        Assert.assertEquals(startX + 1, player.getX());
        Assert.assertEquals(startY, player.getY());
        Assert.assertEquals(board.getTile(startX + 1, startY), player);
    }

    @Test
    public void wallBlockTest(){
        int startX = player.getX(), startY = player.getY();

        player.moveDown();
        player.moveDown();

        Assert.assertEquals(startX, player.getX());
        Assert.assertEquals(startY + 1, player.getY());
        Assert.assertEquals(board.getTile(startX, startY + 1), player);
    }

    @Test
    public void enemyBlockTest(){
        int startX = player.getX(), startY = player.getY();

        player.moveLeft();
        player.moveLeft();

        Assert.assertEquals(startX - 1, player.getX());
        Assert.assertEquals(startY, player.getY());
        Assert.assertEquals(board.getTile(startX - 1, startY), player);
    }

    @Test
    public void outOfBoundsHeightBlockTest(){
        int startX = player.getX(), startY = player.getY();

        player.moveUp();
        player.moveUp();

        Assert.assertEquals(startX, player.getX());
        Assert.assertEquals(startY - 1, player.getY());
        Assert.assertEquals(board.getTile(startX, startY - 1), player);
    }

    @Test
    public void outOfBoundsWidthBlockTest(){
        int startX = player.getX(), startY = player.getY();

        player.moveRight();
        player.moveRight();

        Assert.assertEquals(startX + 1, player.getX());
        Assert.assertEquals(startY, player.getY());
        Assert.assertEquals(board.getTile(startX + 1, startY), player);
    }
}