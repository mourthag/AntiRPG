import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Wall here.
 */
public class Wall extends Tile
{
    public Wall() 
    {
        imageVisible = new GreenfootImage("brick.jpg"); // choose image
        heightMap[0] = true; //floors are at height 0
        heightMap[1] = true; //... and 1.
    }

    public boolean doorsInRange()
    {
        if(getObjectsInRange(getDungeon().blockedDoorRange, Door.class).isEmpty())
        {
            return false;
        }
            return true;
    }
}
