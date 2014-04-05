import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *A Class that contains the subclasses for the specific Rooms
 *a rectangle room is the standart shape
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Room extends Actor
{
    Dungeon currWorld = (Dungeon)getWorld();
    int tileWidth = currWorld.tileWidth;
    int tileHeight = currWorld.tileHeight;
    
    int curX;
    int curY;
    int curWidth;
    int curHeight;
    int[] curEntrance;
    
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
        int[] outerCoordinates = new int[8];
        outerCoordinates[0] = curX;                                    //Top Left
        outerCoordinates[1] = curY;
        outerCoordinates[2] = curX + (curWidth - 1) * tileWidth;          //Top Right
        outerCoordinates[3] = curY;
        outerCoordinates[4] = curX + (curWidth - 1) * tileWidth;          //Bottom Right
        outerCoordinates[5] = curY + (curHeight - 1) * tileHeight;
        outerCoordinates[6] = curX;                                    //Bottom Left
        outerCoordinates[7] = curY + (curHeight - 1) * tileHeight;

        //currWorld.allRooms.add(outerCoordinates);

        //if a entrance is given create the door
        if(curEntrance.length == 2)
        {
            currWorld.addObject(new Door(), curEntrance[0], curEntrance[1]);
        }

        //inner coordinates for the floor
        int innerWidth = curWidth - 2;
        int innerHeight = curHeight - 2;
        int innerX = curX + tileWidth;
        int innerY = curY + tileHeight;

        //outer Boarders
        //TODO: use outerCoordinates[]
        createWallLine( curX, curY, true, curWidth);                                             //top
        createWallLine( curX, curY + tileHeight, false, curHeight - 1);                          //left
        createWallLine( innerX, outerCoordinates[7], true, curWidth - 1);                  //bottom
        createWallLine( outerCoordinates[4], innerY, false, curHeight - 2);                //right

        //Fill with Floor
        for(int i = 0; i<innerHeight; i++)
        {
            createFloorLine(innerX , innerY + i*tileHeight, true, innerWidth);
        }
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
}
