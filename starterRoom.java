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
     * Add a player and some random Deco
     */
    @Override
    public void specificContent()
    {
        getDungeon().addObject(new Player(), curX + 25, curY + 30);
    }
}
