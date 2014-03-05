import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Monster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Monster extends NPC
{
    /**
     * Act - do whatever the Monster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        stImage = new GreenfootImage("baby1.png");
        sightRange(inSight, stImage);
    }

    private GreenfootImage stImage = new GreenfootImage("baby1.png");

    public boolean solid = true;

    @Override
    protected void addedToWorld(World world)
    {
        Dungeon curWorld = (Dungeon)getWorld();
        stImage.scale(curWorld.getTileWidth(), curWorld.getTileHeight());	
        setImage(stImage);
    }
}
