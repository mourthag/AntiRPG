import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends NPC
{
    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        stImage = new GreenfootImage("baby2.png");
        sightRange(inSight, stImage);
    }  
    private GreenfootImage stImage = new GreenfootImage("baby2.png");

    public boolean solid = true;

    @Override
    protected void addedToWorld(World world)
    {
        Dungeon curWorld = (Dungeon)getWorld();
        stImage.scale(curWorld.getTileWidth(), curWorld.getTileHeight());	
        setImage(stImage);
    }
}
