package model.tiles.units.enemies;

import control.initializers.LevelInitializer;
import model.tiles.Tile;
import model.tiles.units.GeneralUnitTest;
import model.tiles.units.players.Warrior;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class BossAbilityTest extends GeneralUnitTest {
    private Boss b;

    @Before
    public void setUp(){
        testLevel = new File("./DnD_OOP/tests/testLevels/level5.txt");
        player = new Warrior("test player", 10, 3, 0, 5, Tile.Color.GOLD);
        board = new LevelInitializer(player).initializeLevel(testLevel).getBoard();//Assuming levelInitializer works
        pX = player.getX();//2
        pY = player.getY();//2
        b = ((Boss)board.getTile(0,0)); //We know this tiles holds a Night's King
        //M.....@.....s
    }

    @Test
    public void abilityKillTest(){
        b.moveRight();
        b.moveRight();
        b.moveRight();
        b.moveRight();
        b.castAbility();

        Assert.assertTrue(player.isDead());
    }
}