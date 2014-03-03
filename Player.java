import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Mob
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("right"))
        {
            move(1);
        }
        if(Greenfoot.isKeyDown("left"))
        {
            move(-1);
        }
        sight();
    }    

    public boolean solid = true;

    private void sight()
    {
        List<Tiles> allObjects = getWorld().getObjects(Tiles.class);
        for(Tiles object_:allObjects)
        {
            object_.setInSight(false);
        }

        List<Tiles> inRange = getObjectsInRange(100, Tiles.class);
        for(Tiles object_:inRange)
        {
            object_.setInSight(true);
        }
    }
}
