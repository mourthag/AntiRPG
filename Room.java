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
    int tileWidth = getDungeon().tileWidth;
    int tileHeight = getDungeon().tileHeight;
    int doorCount = Greenfoot.getRandomNumber(4);   //Number of Doors
    boolean[] blockedWalls;                         //the blocked Walls of this room
    int[] outerCoordinates = new int[8];            //for easier handling

    int[] doorCoordinates = new int[doorCount * 2]; //int array for the Coordinates of the Doors
    int[] doorDirections = new int[doorCount];     //int array for the direction of each door             
    //0 = top   1 = right   2 = bottom  3 = left

    int[] nextDoor = new int[doorCount * 2];          //Coords for the Entrance of these rooms

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
            getDungeon().removeObjects(getDungeon().getObjectsAt(curEntrance[0], curEntrance[1], Wall.class));
            getDungeon().addObject(new Door(), curEntrance[0], curEntrance[1]);
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
                getDungeon().addObject(new Wall(), startX + i*tileWidth, startY);
            }
        }
        else
        {
            for(int j=0; j<length; j++)
            {
                getDungeon().addObject(new Wall(), startX, startY + j*tileHeight);
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
                getDungeon().addObject(new Floor(), startX + i*tileWidth, startY);
            }
        }
        else
        {
            for(int j=0; j<length; j++)
            {
                getDungeon().addObject(new Floor(), startX, startY + j*tileHeight);
            }
        }
    }

    /*
     * sets the Doors into the room
     */
    public void setDoors()
    {
        for(int i = 0; i<doorCount; i++)
        {
            boolean tooCloseDoor = false;

            int random;
            do
            {
                random = Greenfoot.getRandomNumber(4);
            }while(blockedWalls[random]);

            if(i < doorDirections.length){
                doorDirections[i] = random;
            }

            do
            {
                if(doorDirections[i] == 0)
                {
                    doorCoordinates[2*i] = innerX + Greenfoot.getRandomNumber(curWidth - 2) * tileWidth;
                    doorCoordinates[2*i + 1] = outerCoordinates[1];

                    nextDoor[2*i] = doorCoordinates[2*i];
                    nextDoor[2*i + 1] = doorCoordinates[2*i + 1] - tileHeight;

                }
                else if(doorDirections[i] == 1)
                {
                    doorCoordinates[2*i] = outerCoordinates[4];
                    doorCoordinates[2*i + 1] = innerY + Greenfoot.getRandomNumber(curHeight - 2) * tileHeight;

                    nextDoor[2*i] = doorCoordinates[2*i] + tileWidth;
                    nextDoor[2*i + 1] = doorCoordinates[2*i + 1];

                }
                else if(doorDirections[i] == 2)
                {
                    doorCoordinates[2*i] = innerX + Greenfoot.getRandomNumber(curWidth - 2) * tileWidth;
                    doorCoordinates[2*i + 1] = outerCoordinates[7];

                    nextDoor[2*i] = doorCoordinates[2*i];
                    nextDoor[2*i + 1] = doorCoordinates[2*i + 1] + tileHeight;

                }
                else if(doorDirections[i] == 3)
                {
                    doorCoordinates[2*i] = outerCoordinates[0];
                    doorCoordinates[2*i + 1] = innerY + Greenfoot.getRandomNumber(curHeight - 2) * tileHeight;

                    nextDoor[2*i] = doorCoordinates[2*i] - tileWidth;
                    nextDoor[2*i + 1] = doorCoordinates[2*i + 1];

                }

                List<Wall> wallsToRemove = getDungeon().getObjectsAt(doorCoordinates[2*i], doorCoordinates[2*i + 1], Wall.class);

                tooCloseDoor = false;
                for(Wall currWall:wallsToRemove)
                {
                    if(currWall.doorsInRange())
                    {
                        tooCloseDoor = true;
                    }
                    else
                    {
                        getDungeon().removeObjects(wallsToRemove);
                        getDungeon().addObject(new Door(), doorCoordinates[2*i], doorCoordinates[2*i + 1]);
                    }
                }

            }while(checkTooCloseRoom() && tooCloseDoor);

            /*
             * Rufe die Funktion am besten von Hand auf... i ist die Numer der Tür, an der ein neuer Raum erzeugt werden soll
             */
            //randomRoom(i);
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
        if(outerCoordinates[6] >  getDungeon().frameWidth - 10 * tileWidth)
        {
            blocked[1] = true;         //right

        }
        if(outerCoordinates[7] > getDungeon().frameHeight - 10 * tileHeight)
        {
            blocked[2] = true;         //bottom

        }

        return blocked;
    }

    /*
     * checks if another room is too close to the door
     */
    public boolean checkTooCloseRoom()
    {

        List<Room> otherRoomsInRange = getObjectsInRange(getDungeon().roomDistance , Room.class);
        if(otherRoomsInRange.size() == 0)
        {
            return false;
        }

        return true;
    }

    /*
     * creates the new Room
     */
    public void randomRoom(int number)
    {
        int[] entrance = new int[2];

        entrance[0] = nextDoor[2*number];
        entrance[1] = nextDoor[2*number + 1];

        int nextWidth = Greenfoot.getRandomNumber(7) + 6;
        int nextHeight = Greenfoot.getRandomNumber(7) + 6;

        int nextX = entrance[0];
        int nextY = entrance[1];

        if(doorDirections[number] == 0 || doorDirections[number] == 2)
        {
            nextX = nextX - nextWidth/2 * tileWidth;// + getDungeon().getOffset();
        }
        else if(doorDirections[number] == 1 || doorDirections[number] == 3)
        {
            nextY = nextY - nextHeight/2 * tileHeight; //+ getDungeon().getOffset();
        }

        if(!getDungeon().bossRoomSpawned)
        {
            if(Greenfoot.getRandomNumber(100) <= getDungeon().bossRoomChance)
            {
                getDungeon().addObject(new bossRoom(nextX, nextY, nextWidth, nextHeight,entrance), nextX, nextY);
                getDungeon().bossRoomSpawned = true;
            }
            else
            {
                getDungeon().addObject(new bossRoom(nextX, nextY, nextWidth, nextHeight,entrance), nextX, nextY);
            }
        }
        else
        {
            getDungeon().addObject(new bossRoom(nextX, nextY, nextWidth, nextHeight,entrance), nextX, nextY);
        }

    }
}

