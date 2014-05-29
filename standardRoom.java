import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Room here.
 */
public class standardRoom extends Room
{
    public standardRoom(int x, int y, int width, int height, int[] entrance)
    {
        super( x, y, width, height, entrance);
    }

    /**
     * Act - do whatever the Room wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }  

    /**
     * Add some random Deco and monsters
     */
    @Override
    public void specificContent()
    {
        getDungeon().addObject(new Player(), curX + 25, curY + 30);
    }

    /**
     * set the minimum of following Rooms to 2 so it will not stop at the starterRoom
     */
    @Override
    public void setDoorCount()
    {
        doorCount = 0;
        doorMinimum = 2;
    }
}
