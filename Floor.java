import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Floor here.
 */
public class Floor extends Tile
{
    public Floor() 
    {
        imageVisible = new GreenfootImage("rivets.jpg"); // choose image
        heightMap[0] = true; //floors are at height 0
    }    
}
