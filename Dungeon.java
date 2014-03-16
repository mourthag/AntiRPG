import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.List;
;

/**
 * The World is called Dungeon.
 * The Dungeon includes several rooms and roomtypes which will be spawned randomly at the beginning of a Level. 
 * The number of rooms and monsters which are spawned is relative to the difficulty level which is raised after each boss.
 * 
 * @author mourthag,t4b
 */
public class Dungeon extends World
{
    public int difficulty = 26;
    public static final int frameWidth = Integer.valueOf(JOptionPane.showInputDialog("Width","1131"));      //tmp no control for invalid values
    public static final int frameHeight = Integer.valueOf(JOptionPane.showInputDialog("Height","725"));     //tmp no control for invalid values
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
        //addObject(new Tile(), 70,45);
        addObject(new Player(), 150, 150);

        spawnRoom( 100,100,10,10);
    }

    /*
     * spawns a rectangle room at postition x,y 
     * its size is declared in Tiles
     */
    public void spawnRoom(int x, int y, int width, int height)
    {
        //Pack the Boarder Coordinates
        int[] outerCoordinates = new int[8];
        outerCoordinates[0] = x;                                    //Top Left
        outerCoordinates[1] = y;
        outerCoordinates[2] = x + (width - 1) * tileWidth;          //Top Right
        outerCoordinates[3] = y;
        outerCoordinates[4] = x + (width - 1) * tileWidth;          //Bottom Right
        outerCoordinates[5] = y + (height - 1) * tileHeight;
        outerCoordinates[6] = x;                                    //Bottom Left
        outerCoordinates[7] = y + (height - 1) * tileHeight;

        //inner coordinates for the floor
        int innerWidth = width - 2;
        int innerHeight = height - 2;
        int innerX = x + tileWidth;
        int innerY = y + tileHeight;

        //outer Boarders
        //TODO: use outerCoordinates[]
        createWallLine( x, y, true, width);                                             //top
        createWallLine( x, y + tileHeight, false, height - 1);                          //left
        createWallLine( innerX, outerCoordinates[7], true, width - 1);                  //bottom
        createWallLine( outerCoordinates[4], innerY, false, height - 2);                //right

        //Fill with Floor
        for(int i = 0; i<innerHeight; i++)
        {
            createFloorLine(innerX , innerY + i*tileHeight, true, innerWidth);
        }

        int doorCount = Greenfoot.getRandomNumber(4);   //number of Doors
        setDoors(doorCount, outerCoordinates, width, height);                            //Delete Walls and replace them with Doors
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

    public void setDoors(int doorCount, int[] roomCoordinates, int width, int height)
    {
        int[] doorCoordinates = new int[doorCount * 2]; //int array for the Coordinates of the Doors
        int[] doorDirections = new int[doorCount];     //int array for the direction of each door             
        //0 = top   1 = right   2 = bottom  3 = left
        
        int innerX = roomCoordinates[0] + tileWidth;
        int innerY = roomCoordinates[1] + tileHeight;

        for(int i = 0; i<doorCount; i++)
        {
            doorDirections[i] = Greenfoot.getRandomNumber(4);
            //TODO: Make these doors not intersecting with others

            if(doorDirections[i] == 0)
            {
                doorCoordinates[i] = innerX + Greenfoot.getRandomNumber(width - 2) * tileWidth;
                doorCoordinates[i + 1] = roomCoordinates[1];
            }
            else if(doorDirections[i] == 1)
            {
                doorCoordinates[i] = roomCoordinates[4];
                doorCoordinates[i + 1] = innerY + Greenfoot.getRandomNumber(height - 2) * tileHeight;

            }
            else if(doorDirections[i] == 2)
            {
                doorCoordinates[i] = innerX + Greenfoot.getRandomNumber(width - 2) * tileWidth;
                doorCoordinates[i + 1] = roomCoordinates[7];
            }
            else if(doorDirections[i] == 3)
            {
                doorCoordinates[i] = roomCoordinates[0];
                doorCoordinates[i + 1] = innerY + Greenfoot.getRandomNumber(height - 2) * tileHeight;
            }

            List<Wall> wallsToRemove = getObjectsAt(doorCoordinates[i], doorCoordinates[i + 1], Wall.class);
            removeObjects(wallsToRemove);
            addObject(new Door(), doorCoordinates[i], doorCoordinates[i + 1]);

        }
    }
}
