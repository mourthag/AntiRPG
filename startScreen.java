import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class startScreen here.
 */
public class startScreen extends Screen
{
    /**
     * Act - do whatever the startScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
      while(!Greenfoot.isKeyDown("enter"));
                getDungeon().removeObject(this);      //Suicide
    } 

    public startScreen()
    {
        super("AntiRPG","Press Enter to start");

    }
}
