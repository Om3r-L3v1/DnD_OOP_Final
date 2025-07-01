package model.tiles.units.players;

import control.initializers.LevelInitializer;
import model.tiles.Tile;
import model.tiles.units.GeneralUnitTest;
import model.tiles.units.enemies.Boss;
import model.tiles.units.enemies.Enemy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class HunterAbilityTest extends GeneralUnitTest {
    private Enemy e1,e2;

    @Before
    public void setUp(){
        testLevel = new File("./DnD_OOP/tests/testLevels/level5.txt");
        player = new Hunter("test hunter", 10000, 90, 500, 2, Tile.Color.GOLD);
        board = new LevelInitializer(player).initializeLevel(testLevel).getBoard();//Assuming levelInitializer works
        pX = player.getX();//2
        pY = player.getY();//2
        e1 = ((Enemy)board.getTile(0,0)); //We know this tiles holds a Night's King
        e2 = ((Enemy)board.getTile(12,0)); //We know this tiles holds a Lannister Soldier
        //M.....@.....s
    }

    @Test
    public void outsideRangeTest(){
        player.castAbility();

        Assert.assertFalse(e2.isDead());
    }

    @Test
    public void noArrowsTest(){
        e1.moveRight();
        e1.moveRight();
        e1.moveRight();
        e1.moveRight();
        e1.moveRight();
        for (int i = 0; i < 10; i++) {
            player.castAbility();
        }
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.castAbility();

        Assert.assertFalse(e2.isDead());
    }

    @Test
    public void abilityKillTest(){
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.castAbility();

        Assert.assertTrue(e2.isDead());
    }
}