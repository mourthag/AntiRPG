import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door extends Tile
{
    public boolean doorInRange = false;   //to acces from the Dungeon class
    public int blockedRange = 5;
    
    public Door()
    {
         tileImageVisible = new GreenfootImage("lift-closed.png");
         //tileHeightmap[0]
    }
    
    @Override
    void subSpecific()
    {
        List<Door> doorsInRange = getObjectsInRange(blockedRange, Door.class);
        
        if(doorsInRange.isEmpty())
        {
            doorInRange = true;
        }
        else
        {
            doorInRange = false;
        }
    }
}
