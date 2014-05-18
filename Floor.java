import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Floor here.
 */
public class Floor extends Tile
{
    public Floor() 
    {
        tileImageVisible = new GreenfootImage("rivets.jpg"); // choose image
        tileHeightmap[0] = true; //floors are at height 0
    }    
}
