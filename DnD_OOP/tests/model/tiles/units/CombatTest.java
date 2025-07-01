package model.tiles.units;

import control.initializers.LevelInitializer;
import model.level.Board;
import model.tiles.Tile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Mage;
import model.tiles.units.players.Player;
import model.tiles.units.players.Rouge;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class CombatTest extends GeneralUnitTest{
    private int eX, eY;
    private Enemy enemy;

    @Before
    public void setUp(){
        testLevel = new File("./DnD_OOP/tests/testLevels/level2.txt");
        player = new Rouge("test player", 60, 300, 5, 10,Tile.Color.GOLD);
        board = new LevelInitializer(player).initializeLevel(testLevel).getBoard();//Assuming levelInitializer works
        pX = player.getX();
        pY = player.getY();
        enemy = ((Enemy)board.getTile(0,1));//We know that tile holds an enemy
        eX = enemy.getX();
        eY = enemy.getY();
    }

    @Test
    public void playerAttackSurviveTest(){
        player.moveLeft();
        player.moveLeft();

        Assert.assertFalse(enemy.isDead());
        Assert.assertEquals(pX - 1, player.getX());
        Assert.assertEquals(board.getTile(pX - 1, pY), player);
        Assert.assertEquals(board.getTile(eX, eY), enemy);
    }

    @Test
    public void playerAttackKillTest(){
        player.moveLeft();
        while (!enemy.isDead())
            player.moveLeft();

        Assert.assertTrue(enemy.isDead());
        Assert.assertEquals(pX - 2, player.getX());
        Assert.assertEquals(board.getTile(eX, eY), player);
        Assert.assertEquals(2, player.getLevel());
    }
    @Test
    public void enemyAttackSurviveTest(){
        enemy.moveRight();
        enemy.moveRight();

        Assert.assertFalse(player.isDead());
        Assert.assertEquals(eX + 1, enemy.getX());
        Assert.assertEquals(board.getTile(eX + 1, eY), enemy);
        Assert.assertEquals(board.getTile(pX, pY), player);
    }

    @Test
    public void enemyAttackKillTest(){
        enemy.moveRight();
        while (!player.isDead())
            enemy.moveRight();

        Assert.assertTrue(player.isDead());
        Assert.assertEquals(eX + 2, player.getX());
        Assert.assertEquals(board.getTile(pX, pY), player);
        Assert.assertEquals(player.getTile(), Player.PLAYER_DEAD_CHAR);
    }
}