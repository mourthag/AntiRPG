import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Small Healthbar to indicate the Player´s health
 */
public class healthBar extends hackedActor
{
    public int maxHealth;
    
    public healthBar(int maximum)
    {
        maxHealth = maximum;
    }
    
    /**
     * Creates a little bar on top of the Screen
     * Called by the player to handle his hp
     */
    public void makeImage(int HP)
    {
        int WIDTH = getDungeon().frameWidth;
        int HEIGHT = 10;
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(0,255,0));
        image.fillRect(0, 0, HP * WIDTH / maxHealth, HEIGHT);
        setImage(image);
    }
}
