import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Wall here.
 */
public class Wall extends Tile
{

    public Wall() 
    {
        imageLoc = "brick.jpg"; // choose image
        heightMap[0] = true; //floors are at height 0
        heightMap[1] = true; //... and 1.
    }
}
