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

    public int difficulty = 26;
    public static final int frameWidth = 1131;
    public static final int frameHeight = 725;
    public static final int tileCountX = 51;
    public static final int tileCountY = 34;
    public static final int tileWidth = frameWidth/tileCountX;
    public static final int tileHeight = frameHeight/tileCountY;


    /**
     * Constructor for objects of class Dungeon.
     * 
     */
    public Dungeon()
    {   
        super(frameWidth, frameHeight, 1);

        //color the background black
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        
        //set act order
        //player needs to be last because of the way we process visbility
        setActOrder(Tile.class, Player.class);
        
        //tmp playground
        //addObject(new Wall(), 45,45);
        addObject(new Tile(), 70,45);
        addObject(new Player(), 70, 70);
    }


    
}
