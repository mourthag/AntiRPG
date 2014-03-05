import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ground extends Tiles
{
    private Dungeon curWorld = (Dungeon)getWorld();

    /**
     * Act - do whatever the Ground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        stImage = new GreenfootImage("granite-light.jpg");
        stImage.scale(curWorld.getTileWidth(), curWorld.getTileHeight());

        sightRange(inSight, stImage);
    } 

    private GreenfootImage stImage = new GreenfootImage("granite-light.jpg");

    public boolean solid = false;

    @Override
    protected void addedToWorld(World world)
    {
        Dungeon curWorld = (Dungeon)getWorld();
        stImage.scale(curWorld.getTileWidth(), curWorld.getTileHeight());	
        setImage(stImage);
    }
}
