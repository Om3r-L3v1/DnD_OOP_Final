package model.tiles.units;

import model.level.Board;
import model.tiles.units.players.Player;
import org.junit.Before;

import java.io.File;

public abstract class GeneralUnitTest {
    protected Board board;
    protected Player player;
    protected File testLevel;
    protected int pX,pY;

    @Before
    public abstract void setUp();
}
