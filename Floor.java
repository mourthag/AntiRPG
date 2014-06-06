import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Floor Tile
 */
public class Floor extends Tile
{
    public Floor() 
    {
        imageLoc = "rivets.jpg"; // choose image
        heightMap[0] = true; //floors are at height 0
    }    
}
