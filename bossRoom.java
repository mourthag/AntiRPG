import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossRoom here.
 */
public class bossRoom extends Room
{
    public bossRoom(int x, int y, int width, int height, int[] entrance)
    {
        super( x, y, width, height, entrance);
    }

    /**
     * Act - do whatever the BossRoom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    

    /**
     * Add the predefined Deco and the Boss in the Center
     */
    @Override
    public void specificContent()
    {
        add(new Deco3(), curX + 2 * tileWidth, curY + 2 * tileHeight, 0);
        add(new Deco3(), getOtherX() - 2 * tileWidth, curY + 2 * tileHeight, 0);
        add(new Deco3(), curX + 2 * tileWidth, getOtherY() - 2 * tileHeight, 0);
        add(new Deco3(), getOtherX() - 2 * tileWidth, getOtherY() - 2 * tileHeight, 0);
        
        getDungeon().addObject(new Boss(), curX + 4 * tileWidth, curY + 4 * tileHeight);
    }
    
    /**
     * No following Rooms
     */
    @Override
    public void setDoorCount()
    {
        doorCount = -1;
        doorMinimum = 0;
    }
}
