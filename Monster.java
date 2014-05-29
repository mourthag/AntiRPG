import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Monster here.
 */
public class Monster extends Tile
{
    public Monster() 
    {
        imageVisible = new GreenfootImage("baby2.png"); // choose image
        heightMap[1] = true; //monsters are situated at height 1 and are solid
    }    
}
