import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Tiles
{       
    /**
     * Act - do whatever the Wall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        stImage = new GreenfootImage("brick.jpg");

        sightRange(inSight, stImage);
    }   

    private GreenfootImage stImage = new GreenfootImage("brick.jpg");

    @Override
    protected void addedToWorld(World world)
    {
        Dungeon curWorld = (Dungeon)getWorld();
        stImage.scale(curWorld.getTileWidth(), curWorld.getTileHeight());	
        setImage(stImage);
    }
}
