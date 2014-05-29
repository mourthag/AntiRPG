import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;

/**
 *A Class that contains the subclasses for the specific Rooms
 *a rectangle room is the standart shape
 */
public class Room extends metaTile
{
    int tileWidth = getDungeon().tileWidth;
    int tileHeight = getDungeon().tileHeight;
    int doorCount;                                   //number of Exits this room will have
    int doorMinimum;                             // Minimum number of Doors
    boolean[] blockedWalls;                         //the blocked Walls of this room

    int[] doorCoordinates; //int array for the Coordinates of the Doors
    int[] doorDirections;     //int array for the direction of each door             
    //0 = top   1 = right   2 = bottom  3 = left

    int[] nextDoor;          //Coords for the Entrance of these rooms

    boolean colliding = false; //used for collisions of generated rooms

    //For handling them over to addedToWorld()
    int curX;           
    int curY;
    int curWidth;
    int curHeight;
    int[] curEntrance;

    /**
     * Initialize. Handle the parameters over for use in addedToWorld()
     */
    public Room(int x, int y, int width, int height, int...entrance)
    {
        curX = x;
        curY = y;
        curWidth = width;
        curHeight = height;
        curEntrance = entrance;
    }

    /**
     * Generate the room itself and goes on with more Rooms
     */
    @Override
    protected void addedToWorld(World world)
    {
        //Create the Walls and the Floor
        addSquare(new Wall(), curX, curY, curWidth, curHeight, false);
        addSquare(new Floor(), curX + tileWidth, curY + tileHeight, curWidth - 2, curHeight - 2, true);

        //if a entrance is given create the door
        if(curEntrance.length == 2)
        {
            remove(getDungeon().getObjectsAt(curEntrance[0], curEntrance[1], Tile.class));
            add(new Door(), curEntrance[0], curEntrance[1], 0);
        }

        specificContent();

        blockedWalls =  blockedWalls();

        setDoorCount();

        if(doorCount == 0)     //if the doorCount hasnt been set already
        {
            int limit = 0;
            for(boolean curBlocked: blockedWalls)
            {
                if(!curBlocked)limit++;
            }
            doorCount = Greenfoot.getRandomNumber(2*limit);
        }
        if(doorCount < doorMinimum)doorCount = doorMinimum;

        doorCoordinates = new int[doorCount * 2]; //int array for the Coordinates of the Doors
        doorDirections = new int[doorCount];     //int array for the direction of each door             
        //0 = top   1 = right   2 = bottom  3 = left

        nextDoor = new int[doorCount * 2];          //Coords for the Entrance of these rooms

        setDoors();                            //Delete Walls and replace them with Doors      Spawn another Room if possible
    }

    /**
     * Subclasses can override this to place Deco, Mobs, etc.
     */
    public void specificContent()
    {
        //For subclasses to Override
    }

    /**
     * Act - do whatever the Rooms wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

    }   

    /**
     * This Function will set Doors randomly over the outer Walls
     * Blocked Walls are excluded and for each Door it will spawn a following Room
     */
    public void setDoors()
    {
        for(int i = 0; i<doorCount; i++)
        {
            setDoor(i);

        }

    }

    public void setDoor(int i)
    {
        boolean tooCloseDoor = false;
        do
        {
            tooCloseDoor = false;

            //randomly picks a Wall and redos if its blocked
            int random;
            do random = Greenfoot.getRandomNumber(2) + 1;while(blockedWalls[random]);

            doorDirections[i] = random;

            //set the coordinates for the Door and the following entrance
            //                 if(doorDirections[i] == 0)
            //                 {
            //                     doorCoordinates[2*i] = innerX + Greenfoot.getRandomNumber(curWidth - 2) * tileWidth;
            //                     doorCoordinates[2*i + 1] = outerCoordinates[1];
            // 
            //                     nextDoor[2*i] = doorCoordinates[2*i];
            //                     nextDoor[2*i + 1] = doorCoordinates[2*i + 1] - tileHeight;
            // 
            //                 }
            if(doorDirections[i] == 1)
            {
                doorCoordinates[2*i] = getOtherX();
                doorCoordinates[2*i + 1] = curY + tileHeight + Greenfoot.getRandomNumber(curHeight - 2) * tileHeight;

                nextDoor[2*i] = doorCoordinates[2*i] + tileWidth;
                nextDoor[2*i + 1] = doorCoordinates[2*i + 1];

            }
            else if(doorDirections[i] == 2)
            {
                doorCoordinates[2*i] = curY + tileWidth + Greenfoot.getRandomNumber(curWidth - 2) * tileWidth;
                doorCoordinates[2*i + 1] = getOtherY();

                nextDoor[2*i] = doorCoordinates[2*i];
                nextDoor[2*i + 1] = doorCoordinates[2*i + 1] + tileHeight;

            }
            //                 else if(doorDirections[i] == 3)
            //                 {
            //                     doorCoordinates[2*i] = outerCoordinates[0];
            //                     doorCoordinates[2*i + 1] = innerY + Greenfoot.getRandomNumber(curHeight - 2) * tileHeight;
            // 
            //                     nextDoor[2*i] = doorCoordinates[2*i] - tileWidth;
            //                     nextDoor[2*i + 1] = doorCoordinates[2*i + 1];
            // 
            //                 }

            List<Tile> wallsToRemove = getDungeon().getObjectsAt(doorCoordinates[2*i], doorCoordinates[2*i + 1], Tile.class);

            for(Tile currWall:wallsToRemove)
            {
                if(currWall.doorsInRange() || currWall.getClass() == Door.class) tooCloseDoor = true;
                else
                {
                    remove(currWall);
                    add(new Door(), doorCoordinates[2*i], doorCoordinates[2*i + 1],0);
                }
            }

        }while(tooCloseDoor);

        /*
         * Rufe die Funktion am besten von Hand auf... i ist die Numer der Tür, an der ein neuer Raum erzeugt werden soll
         * Momentan läuft das ganze noch per Hand. Wenn ich die Abstandserkennung implementiert habe,dann rufe ich sie so auf
         */
        //randomRoom(i);
    }

    /**
     * Checks which are blocked by the Worldedges
     * Returns a boolean Array
     */
    public boolean[] blockedWalls()
    {
        boolean[] blocked = new boolean[4]; //4 int        0 = top   1 = right   2 = bottom  3 = left; true = blocked false = available

        if(getX() < 10 * tileWidth) //TODO: make this rely on the difficulty
        {
            blocked[0] = true;         //top

        }
        if(getY() < 10 * tileHeight)
        {
            blocked[3] = true;         //left

        }
        if(getOtherX() >  getDungeon().frameWidth - 10 * tileWidth)
        {
            blocked[1] = true;         //right

        }
        if(getOtherY() > getDungeon().frameHeight - 10 * tileHeight)
        {
            blocked[2] = true;         //bottom

        }

        return blocked;
    }

    /**
     * Creates a new Room at the given Door.
     * It's possible that a bossRoom will spawn. In most cases it will be a standardRoom
     */
    public void randomRoom(int number)
    {
        //TODO: -Metatiles
        //      -Fix Bossroomchance
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

        bossRoom nextBossRoom = new bossRoom(nextX, nextY, 9, 9,entrance);
        standardRoom nextStandardRoom = new standardRoom(nextX, nextY, nextWidth, nextHeight,entrance);

        if(!getDungeon().bossRoomSpawned)
        {
            if(Greenfoot.getRandomNumber(100) <= getDungeon().bossRoomChance)
            {
                getDungeon().addObject(nextBossRoom, nextX, nextY);
                getDungeon().bossRoomSpawned = true;

                if(nextBossRoom.colliding())
                {
                    colliding = true;
                    getDungeon().removeObject(nextBossRoom);
                }
            }
            else
            {
                getDungeon().addObject(nextStandardRoom, nextX, nextY);
                getDungeon().bossRoomChance++;
                if(nextStandardRoom.colliding())
                {
                    colliding = true;
                    getDungeon().removeObject(nextStandardRoom);
                }
            }
        }
        else
        {
            getDungeon().addObject(nextStandardRoom, nextX, nextY);
            getDungeon().bossRoomChance++;
            if(nextStandardRoom.colliding())
            {
                colliding = true;
                getDungeon().removeObject(nextStandardRoom);
            }
        }

    }

    /**
     * Returns the X Value of the right side
     */
    public int getOtherX()
    {

        return curX + (curWidth - 1)*tileWidth;
    }

    /**
     * Returns the Y Value of the bottom side
     */
    public int getOtherY()
    {

        return curY + (curHeight - 1)*tileHeight;
    }

    /**
     * Sets the doorCount and minimum doorCount
     * Supposed to be overwridden by subclasses
     */
    public void setDoorCount()
    {
        doorCount = 0;
        doorMinimum = 0;
    }
}

