import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
    int roomDistance = 7;
    public static final int frameWidth = Integer.valueOf(JOptionPane.showInputDialog("Width","1131"));      //tmp no control for invalid values
    public static final int frameHeight = Integer.valueOf(JOptionPane.showInputDialog("Height","725"));     //tmp no control for invalid values
    public static final int tileCountX = 51; // frameWidth % tileCountX should be 0, and frameWidth/tileCountX=frameHeight/tileCountY
    public static final int tileCountY = 34; // frameHight % tileCountY should be 0
    public static final int tileWidth = frameWidth/tileCountX;
    public static final int tileHeight = frameHeight/tileCountY;

    public List<int[]> allRooms = new ArrayList<int[]>();    //List of all coordinates of every room

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

        startUpRoom( 100,100,10,10);
    }

    /*
     * spawns a rectangle room at postition x,y 
     * its size is declared in Tiles
     */
    public void startUpRoom(int x, int y, int width, int height)
    {
        
        addObject(new starterRoom( x, y, width, height), x, y);
//         //Pack the Boarder Coordinates
//         int[] outerCoordinates = new int[8];
//         outerCoordinates[0] = x;                                    //Top Left
//         outerCoordinates[1] = y;
//         outerCoordinates[2] = x + (width - 1) * tileWidth;          //Top Right
//         outerCoordinates[3] = y;
//         outerCoordinates[4] = x + (width - 1) * tileWidth;          //Bottom Right
//         outerCoordinates[5] = y + (height - 1) * tileHeight;
//         outerCoordinates[6] = x;                                    //Bottom Left
//         outerCoordinates[7] = y + (height - 1) * tileHeight;
// 
//         allRooms.add(outerCoordinates);
// 
//         //if a entrance is given create the door
//         if(entrance.length == 2)
//         {
//             addObject(new Door(), entrance[0], entrance[1]);
//         }
// 
//         //inner coordinates for the floor
//         int innerWidth = width - 2;
//         int innerHeight = height - 2;
//         int innerX = x + tileWidth;
//         int innerY = y + tileHeight;
// 
//         //outer Boarders
//         //TODO: use outerCoordinates[]
//         createWallLine( x, y, true, width);                                             //top
//         createWallLine( x, y + tileHeight, false, height - 1);                          //left
//         createWallLine( innerX, outerCoordinates[7], true, width - 1);                  //bottom
//         createWallLine( outerCoordinates[4], innerY, false, height - 2);                //right
// 
//         //Fill with Floor
//         for(int i = 0; i<innerHeight; i++)
//         {
//             createFloorLine(innerX , innerY + i*tileHeight, true, innerWidth);
//         }
// 
//         boolean[] blockedWalls =  blockedWalls(outerCoordinates);
// 
//         int doorCount = Greenfoot.getRandomNumber(4);   //number of Doors 
//         //TODO:different number of doors with different amount of blocked doors
// 
//         setDoors(doorCount, outerCoordinates, width, height, blockedWalls);                            //Delete Walls and replace them with Doors
    }


    /*
     * sets the Doors into the room
     */
    public void setDoors(int doorCount, int[] roomCoordinates, int width, int height,boolean[] blockedWalls)
    {
        int[] doorCoordinates = new int[doorCount * 2]; //int array for the Coordinates of the Doors
        int[] doorDirections = new int[doorCount];     //int array for the direction of each door             
        //0 = top   1 = right   2 = bottom  3 = left

        int innerX = roomCoordinates[0] + tileWidth;
        int innerY = roomCoordinates[1] + tileHeight;

        int[] nextRoom = new int[doorCount * 2];          //Coords for the rooms that will be spawned
        int[] nextDoor = new int[doorCount * 2]; 

        for(int i = 0; i<doorCount; i++)
        {
            boolean tooCloseDoor = false;
            
            int random;
            do
            {
                random = Greenfoot.getRandomNumber(4);
            }while(blockedWalls(roomCoordinates)[random]);

            doorDirections[i] = random;

            do
            {
                if(doorDirections[i] == 0)
                {
                    doorCoordinates[i] = innerX + Greenfoot.getRandomNumber(width - 2) * tileWidth;
                    doorCoordinates[i + 1] = roomCoordinates[1];

                    nextRoom[i + 1] =  doorCoordinates[i + 1] + 1;  //TODO:Make this Distance Random(maybe relying on the difficulty)
                    nextDoor[i + 1] = nextRoom[i + 1];

                }
                else if(doorDirections[i] == 1)
                {
                    doorCoordinates[i] = roomCoordinates[4];
                    doorCoordinates[i + 1] = innerY + Greenfoot.getRandomNumber(height - 2) * tileHeight;

                    nextRoom[i] =  doorCoordinates[i] + 1;
                    nextDoor[i] = nextRoom[i];

                }
                else if(doorDirections[i] == 2)
                {
                    doorCoordinates[i] = innerX + Greenfoot.getRandomNumber(width - 2) * tileWidth;
                    doorCoordinates[i + 1] = roomCoordinates[7];

                    nextRoom[i + 1] =  doorCoordinates[i + 1] - 1;
                    nextDoor[i + 1] = nextRoom[i + 1];

                }
                else if(doorDirections[i] == 3)
                {
                    doorCoordinates[i] = roomCoordinates[0];
                    doorCoordinates[i + 1] = innerY + Greenfoot.getRandomNumber(height - 2) * tileHeight;

                    nextRoom[i] =  doorCoordinates[i] - 1;
                    nextDoor[i] = nextRoom[i];

                }

                nextDoor[i] = doorCoordinates[i];
                List<Wall> wallsToRemove = getObjectsAt(doorCoordinates[i], doorCoordinates[i + 1], Wall.class);
                removeObjects(wallsToRemove);
                addObject(new Door(), doorCoordinates[i], doorCoordinates[i + 1]);
                
                List<Door> thisDoor =  getObjectsAt(doorCoordinates[i], doorCoordinates[i + 1], Door.class);
                
               for(Door currDoor : thisDoor)
               {
                   tooCloseDoor = currDoor.doorInRange;
                }
                
            }while(checkTooCloseRoom(Arrays.copyOfRange(doorCoordinates, i, i+2), doorDirections[i]) && tooCloseDoor);
        }

    }

    /*
     * checks which walls are blocked
     */
    public boolean[] blockedWalls(int[] roomCoordinates)
    {
        boolean[] blocked = new boolean[4]; //4 int        0 = top   1 = right   2 = bottom  3 = left; true = blocked false = available

        if(roomCoordinates[0] < 10 * tileWidth) //TODO: make this rely on the difficulty
        {
            blocked[0] = true;         //top

        }
        if(roomCoordinates[1] < 10 * tileHeight)
        {
            blocked[3] = true;         //left

        }
        if(roomCoordinates[6] >  frameWidth - 10 * tileWidth)
        {
            blocked[1] = true;         //right

        }
        if(roomCoordinates[7] > frameHeight - 10 * tileHeight)
        {
            blocked[2] = true;         //bottom

        }

        return blocked;
    }

    /*
     * checks if another room is too close to the door
     */
    public boolean checkTooCloseRoom(int[] doorCoordinates, int direction)
    {
        for(int[] p : allRooms)
        {

            if(direction == 0)
            {
                if( doorCoordinates[1] - p[5] < roomDistance && doorCoordinates[1] - p[5] != 0)
                {
                    return true;
                }
            }
            if(direction == 1)
            {
                if( doorCoordinates[0] - p[4] > roomDistance && doorCoordinates[0] - p[4] != 0)
                {
                    return true;
                }
            }
            if(direction == 0)
            {
                if( p[1] - doorCoordinates[1] > roomDistance && p[1] - doorCoordinates[1] != 0  )
                {
                    return true;
                }
            }
            if(direction == 0)
            {
                if( p[1] - doorCoordinates[0] > roomDistance && p[1] - doorCoordinates[0] != 0)
                {
                    return true;
                }
            }

        }
        return false;
    }
}
