import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *A Class that contains the subclasses for the specific Rooms
 *a rectangle room is the standart shape
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rooms extends Actor
{
    Dungeon currWorld = (Dungeon)getWorld();
    int tileWidth = currWorld.tileWidth;
    int tileHeight = currWorld.tileHeight;
    
    public Rooms(int x, int y, int width, int height, int...entrance)
    {
        currWorld = (Dungeon)getWorld();
        
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

        //currWorld.allRooms.add(outerCoordinates);

        //if a entrance is given create the door
        if(entrance.length == 2)
        {
            currWorld.addObject(new Door(), entrance[0], entrance[1]);
        }

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
