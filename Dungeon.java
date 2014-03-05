import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List
;

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
    public static final int frameWidth = 1131;
    public static final int frameHeight = 725;

    public static final Class<?>[] PAINT_ORDER = {
            Player.class, 
            Boss.class, 
            Monster.class, 
            Ground.class,
            Wall.class};

    public Dungeon()
    {   
        super(frameWidth, frameHeight, 1);

        //color the background black
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();

        addObject(new Wall(), 45,45);
        addObject(new Ground(), 70,45);
        addObject(new Player(), 70, 70);
    }

    public int getTileHeight()
    {
        return frameHeight/34;
    }

    public int getTileWidth()
    {
        return frameWidth/51;
    }
}
