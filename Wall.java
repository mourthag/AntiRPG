import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Tile
{
    public Wall() 
    {
        tileImageVisible = new GreenfootImage("brick.jpg"); // choose image
        tileHeightmap[0] = true; //floors are at height 0
        tileHeightmap[1] = true; //... and 1.
    }     
}
