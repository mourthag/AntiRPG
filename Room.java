import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;

/**
 *A Class that contains the subclasses for the specific Rooms
 *a rectangle room is the standart shape
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Room extends hackedActor
{
    Dungeon currWorld = (Dungeon)getWorld();
    int tileWidth = currWorld.tileWidth;
    int tileHeight = currWorld.tileHeight;

    int doorCount = Greenfoot.getRandomNumber(4);   //Number of Doors
    boolean[] blockedWalls;                         //the blocked Walls of this room
    int[] outerCoordinates = new int[8];            //for easier handling
    
    //For handling them over to addedToWorld()
    int curX;           
    int curY;
    int curWidth;
    int curHeight;
    int[] curEntrance;

    //inner coordinates for the floor
    int innerWidth;
    int innerHeight;
    int innerX;
    int innerY;
    
    public Room(int x, int y, int width, int height, int...entrance)
    {
        curX = x;
        curY = y;
        curWidth = width;
        curHeight = height;
        curEntrance = entrance;
    }

    @Override
    protected void addedToWorld(World world)
    {
        currWorld = (Dungeon)getWorld();

        //Pack the Boarder Coordinates
        outerCoordinates[0] = curX;                                    //Top Left
        outerCoordinates[1] = curY;
        outerCoordinates[2] = curX + (curWidth - 1) * tileWidth;          //Top Right
        outerCoordinates[3] = curY;
        outerCoordinates[4] = curX + (curWidth - 1) * tileWidth;          //Bottom Right
        outerCoordinates[5] = curY + (curHeight - 1) * tileHeight;
        outerCoordinates[6] = curX;                                    //Bottom Left
        outerCoordinates[7] = curY + (curHeight - 1) * tileHeight;

        //inner coordinates for the floor
        innerWidth = curWidth - 2;
        innerHeight = curHeight - 2;
        innerX = curX + tileWidth;
        innerY = curY + tileHeight;

        //outer Boarders
        //TODO: use outerCoordinates[]
        createWallLine( curX, curY, true, curWidth);                                             //top
        createWallLine( curX, curY + tileHeight, false, curHeight - 1);                          //left
        createWallLine( innerX, outerCoordinates[7], true, curWidth - 1);                  //bottom
        createWallLine( outerCoordinates[4], innerY, false, curHeight - 2);                //right

        
        //if a entrance is given create the door
        if(curEntrance.length == 2)
        {
            currWorld.addObject(new Door(), curEntrance[0], curEntrance[1]);
        }
        
        //Fill with Floor
        for(int i = 0; i<innerHeight; i++)
        {
            createFloorLine(innerX , innerY + i*tileHeight, true, innerWidth);
        }

        blockedWalls =  blockedWalls();

        setDoors();                            //Delete Walls and replace them with Doors

    }

    /**
     * Act - do whatever the Rooms wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        currWorld = (Dungeon)getWorld();
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
                currWorld.addObject(new Wall(), startX + i*tileWidth, startY);
            }
        }
        else
        {
            for(int j=0; j<length; j++)
            {
                currWorld.addObject(new Wall(), startX, startY + j*tileHeight);
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
                currWorld.addObject(new Floor(), startX + i*tileWidth, startY);
            }
        }
        else
        {
            for(int j=0; j<length; j++)
            {
                currWorld.addObject(new Floor(), startX, startY + j*tileHeight);
            }
        }
    }

    /*
     * sets the Doors into the room
     */
    public void setDoors()
    {
        int[] doorCoordinates = new int[doorCount * 2]; //int array for the Coordinates of the Doors
        int[] doorDirections = new int[doorCount];     //int array for the direction of each door             
        //0 = top   1 = right   2 = bottom  3 = left

        int[] nextRoom = new int[doorCount * 2];          //Coords for the rooms that will be spawned
        int[] nextDoor = new int[doorCount * 2];          //Coords for the Entrance of these rooms

        for(int i = 0; i<doorCount; i++)
        {
            boolean tooCloseDoor = false;

            int random;
            do
            {
                random = Greenfoot.getRandomNumber(4);
            }while(blockedWalls[random]);

            doorDirections[i] = random;

            do
            {
                if(doorDirections[i] == 0)
                {
                    doorCoordinates[i] = innerX + Greenfoot.getRandomNumber(curWidth - 2) * tileWidth;
                    doorCoordinates[i + 1] = outerCoordinates[1];

                    nextRoom[i + 1] =  doorCoordinates[i + 1] + 1;  //TODO:Make this Distance Random(maybe relying on the difficulty)
                    nextDoor[i + 1] = nextRoom[i + 1];

                }
                else if(doorDirections[i] == 1)
                {
                    doorCoordinates[i] = outerCoordinates[4];
                    doorCoordinates[i + 1] = innerY + Greenfoot.getRandomNumber(curHeight - 2) * tileHeight;

                    nextRoom[i] =  doorCoordinates[i] + 1;
                    nextDoor[i] = nextRoom[i];

                }
                else if(doorDirections[i] == 2)
                {
                    doorCoordinates[i] = innerX + Greenfoot.getRandomNumber(curWidth - 2) * tileWidth;
                    doorCoordinates[i + 1] = outerCoordinates[7];

                    nextRoom[i + 1] =  doorCoordinates[i + 1] - 1;
                    nextDoor[i + 1] = nextRoom[i + 1];

                }
                else if(doorDirections[i] == 3)
                {
                    doorCoordinates[i] = outerCoordinates[0];
                    doorCoordinates[i + 1] = innerY + Greenfoot.getRandomNumber(curHeight - 2) * tileHeight;

                    nextRoom[i] =  doorCoordinates[i] - 1;
                    nextDoor[i] = nextRoom[i];

                }

                nextDoor[i] = doorCoordinates[i];
                List<Wall> wallsToRemove = currWorld.getObjectsAt(doorCoordinates[i], doorCoordinates[i + 1], Wall.class);
                currWorld.removeObjects(wallsToRemove);
                currWorld.addObject(new Door(), doorCoordinates[i], doorCoordinates[i + 1]);

                List<Door> thisDoor =  currWorld.getObjectsAt(doorCoordinates[i], doorCoordinates[i + 1], Door.class);

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
    public boolean[] blockedWalls()
    {
        boolean[] blocked = new boolean[4]; //4 int        0 = top   1 = right   2 = bottom  3 = left; true = blocked false = available

        if(outerCoordinates[0] < 10 * tileWidth) //TODO: make this rely on the difficulty
        {
            blocked[0] = true;         //top

        }
        if(outerCoordinates[1] < 10 * tileHeight)
        {
            blocked[3] = true;         //left

        }
        if(outerCoordinates[6] >  currWorld.frameWidth - 10 * tileWidth)
        {
            blocked[1] = true;         //right

        }
        if(outerCoordinates[7] > currWorld.frameHeight - 10 * tileHeight)
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
  
        List<Room> otherRoomsInRange = getObjectsInRange(currWorld.roomDistance * tileHeight, Room.class);
        if(otherRoomsInRange.size() == 0)
        {
            return false;
        }
        
        return true;
    }
}

