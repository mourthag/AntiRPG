import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class StarterRoom here.
 */
public class starterRoom extends Room
{
    public starterRoom(int x, int y, int width, int height)
    {
        super( x, y, width, height);
    }

    /**
     * Act - do whatever the StarterRoom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    } 

    /**
     * Add a player and some (not so)random Deco
     */
    @Override
    public void specificContent()
    {
        getDungeon().addObject(new Player(), curX + 25, curY + 30);

        add(new Deco1(), curX + tileWidth, curY + tileHeight, 0);
        add(new Deco1(), getOtherX() - tileWidth, curY + tileHeight, 0);
        add(new Deco1(), curX + tileWidth, getOtherY() - tileHeight, 0);
        add(new Deco1(), getOtherX() - tileWidth, getOtherY() - tileHeight, 0);
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
