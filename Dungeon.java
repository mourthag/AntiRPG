import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * The World is called Dungeon.
 * The Dungeon includes several rooms and roomtypes which will be spawned randomly at the beginning of a Level. 
 * The number of rooms and monsters which are spawned is relative to the difficulty level which is raised after each boss.
 * 
 * @author mourthag
 */
public class Dungeon extends World
{

    /**
     * Constructor for objects of class Dungeon.
     * 
     */
    public int difficulty = 26;

    public static final Class<?>[] PAINT_ORDER = {
            Wall.class, 
            Ground.class, 
            Monster.class, 
            Boss.class,
            Player.class};

    public Dungeon()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1131, 725, 1);

        //color the background black
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
    }
}
