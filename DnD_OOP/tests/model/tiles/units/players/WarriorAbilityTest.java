package model.tiles.units.players;

import control.initializers.LevelInitializer;
import model.tiles.Tile;
import model.tiles.units.GeneralUnitTest;
import model.tiles.units.enemies.Enemy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
public class WarriorAbilityTest extends GeneralUnitTest {
    private Enemy e;

    @Before
    public void setUp(){
        testLevel = new File("./DnD_OOP/tests/testLevels/level4.txt");
        player = new Warrior("test warrior", 10000, 3, 500, 5);
        board = new LevelInitializer(player).initializeLevel(testLevel).getBoard();//Assuming levelInitializer works
        pX = player.getX();//2
        pY = player.getY();//2
        e = ((Enemy)board.getTile(12,0)); //We know this tiles holds a Lannister Soldier
        //s.....@.....s
    }

    @Test
    public void outsideRangeTest(){
        player.takeDamage(6000, e);
        player.castAbility();

        Assert.assertFalse(e.isDead());
        Assert.assertEquals(9000,player.getCurrentHealth());
    }

    @Test
    public void onCooldownTest(){
        player.castAbility();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.takeDamage(6000, e);
        player.castAbility();

        Assert.assertFalse(e.isDead());
        Assert.assertEquals(4000,player.getCurrentHealth());
    }

    @Test
    public void abilityKillTest(){
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.takeDamage(6000, e);
        player.castAbility();

        Assert.assertTrue(e.isDead());
        Assert.assertEquals(9000,player.getCurrentHealth());
    }
}