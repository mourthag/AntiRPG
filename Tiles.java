import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * "Tiles" is the overall class which is included to manage 
 * global settings for every object in the world
 * 
 * @author mourthag
 */
public class Tiles extends Actor
{
    /**
     * sightRange must be called every act() of the subclass
     * It makes the image Black if its not in the range of the Player
    */
     public void sightRange(boolean visible, GreenfootImage stImage)
    {
        if(!visible)
        {
            GreenfootImage image = getImage();
            image.setColor(Color.BLACK);
            image.fill();
        }
        else{
            GreenfootImage image = getImage();
            image = stImage;
        }
    }

    public boolean inSight = false;
    
    public void setInSight(boolean status)
    {
        inSight = status;
    }
    
    public void act() 
    {
        
    }    
}
