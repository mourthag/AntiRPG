import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Monster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Monster extends Tile
{
    public Monster() 
    {
        tileImageVisible = new GreenfootImage("baby2.png"); // choose image
        tileHeightmap[1] = true; //monsters are situated at height 1 and are solid
    }    
}
