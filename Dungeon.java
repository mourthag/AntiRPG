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
    public static final int tileCountX = 51; // frameWidth % tileCountX should be 0, and frameWidth/tileCountX=frameHeight/tileCountY
    public static final int tileCountY = 34; // frameHight % tileCountY should be 0
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

        spawnRoom( 100,100,10,10);
    }

    /*
     * spawns a rectangle room at postition x,y 
     * its size is declared in Tiles
     */
    public void spawnRoom(int x, int y, int width, int height)
    {
        //coordinates for the opposite start
        int opX = x + (width - 1) * tileWidth;
        int opY = y + (height - 1) * tileHeight ;
        
        //inner coordinates for the floor
        int innerWidth = width - 2;
        int innerHeight = height - 2;
        int innerX = x + tileWidth;
        int innerY = y + tileHeight;
        
        //outer Boarders
        createWallLine( x, y, true, width);                             //top
        createWallLine( x, y + tileHeight, false, height - 1);          //left
        createWallLine( innerX, opY, true, width - 1);                  //bottom
        createWallLine( opX, innerY, false, height - 2);                 //right
        
        //Fill with Floor
        for(int k = 0; k<innerHeight; k++)
        {
            createFloorLine(innerX , innerY + k*tileWidth, true, innerWidth);
        }
    }

    /*
     * Creates a Line of Walls
     */
    public void createWallLine(int startX, int startY, boolean horizontal, int length)
    {
        if (horizontal)
        {
            for(int i=0; i<length; i++)
            {
                addObject(new Wall(), startX + i*tileWidth, startY);
            }
        }
        else
        {
            for(int j=0; j<length; j++)
            {
                addObject(new Wall(), startX, startY + j*tileHeight);
            }
        }
    }

    /*
     * Creates a Line of Floor
     */
    public void createFloorLine(int startX, int startY, boolean horizontal, int length)
    {
        if (horizontal)
        {
            for(int i=0; i<length; i++)
            {
                addObject(new Floor(), startX + i*tileWidth, startY);
            }
        }
        else
        {
            for(int j=0; j<length; j++)
            {
                addObject(new Floor(), startX, startY + j*tileHeight);
            }
        }
    }
}
