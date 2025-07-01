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

public class MageAbilityTest extends GeneralUnitTest {
    private Enemy e1, e2;

    @Before
    public void setUp(){
        testLevel = new File("./DnD_OOP/tests/testLevels/level4.txt");
        player = new Mage("test mage", 10000, 3, 500, 2000,500,30000, 2,3);
        board = new LevelInitializer(player).initializeLevel(testLevel).getBoard();//Assuming levelInitializer works
        pX = player.getX();//2
        pY = player.getY();//2
        e1 = ((Enemy)board.getTile(0,0)); //We know this tiles holds a Lannister Soldier
        e2 = ((Enemy)board.getTile(12,0)); //We know this tiles holds a Lannister Soldier
        //s.....@.....s
    }

    @Test
    public void outsideRangeTest(){
        player.castAbility();

        Assert.assertFalse(e1.isDead());
        Assert.assertFalse(e2.isDead());
    }

    @Test
    public void noManaTest(){
        player.castAbility();
        e1.moveRight();
        e1.moveRight();
        e1.moveRight();
        e1.moveRight();
        e2.moveLeft();
        e2.moveLeft();
        e2.moveLeft();
        e2.moveLeft();
        player.castAbility();

        Assert.assertFalse(e1.isDead());
        Assert.assertFalse(e2.isDead());
    }

    @Test
    public void abilityKillTest(){
        e1.moveRight();
        e1.moveRight();
        e1.moveRight();
        e1.moveRight();
        e2.moveLeft();
        e2.moveLeft();
        e2.moveLeft();
        e2.moveLeft();
        player.castAbility();

        Assert.assertTrue(e1.isDead());
        Assert.assertTrue(e2.isDead());
    }
}