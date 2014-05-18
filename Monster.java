import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Monster here.
 */
public class Monster extends Tile
{
    public Monster() 
    {
        tileImageVisible = new GreenfootImage("baby2.png"); // choose image
        tileHeightmap[1] = true; //monsters are situated at height 1 and are solid
    }    
}
