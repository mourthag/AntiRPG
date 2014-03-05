import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.lang.*;

/**
 * "Tiles" is the overall class which is included to manage 
 * global settings for every object in the world
 * 
 * @author mourthag, t4b
 */
public class Tile extends Actor
{
    GreenfootImage tileImageVisible;
    GreenfootImage tileImageInvisible;
    boolean tileImageVisibleDefault; //choose whether tile is usually visible to player
    float Xaccurate;
    float Yaccurate;
    float speed; //speed in pixels/act, automatically initialized to 0

    public Tile()
    {
        tileImageVisible = new GreenfootImage("none.png");
        tileImageInvisible = new GreenfootImage("obfuscated.png");
        tileImageVisibleDefault = false; 
    }

    //run when placing the object in the world
    @Override
    protected void addedToWorld(World world)
    {
        //scale images
        Dungeon dungeon = (Dungeon) world;
        tileImageInvisible.scale(dungeon.tileWidth, dungeon.tileHeight);
        tileImageVisible.scale(dungeon.tileWidth, dungeon.tileHeight);
        //set default image
        if(tileImageVisibleDefault){
            setImage(tileImageVisible);
        } else {
            setImage(tileImageInvisible);
        }

        //initialize accurate position storage...
        Xaccurate = getX();
        Yaccurate = getY();
    }

    public void setVisibility(boolean visible)
    {
        //sets visibility to value visible
        if(visible){
            setImage(tileImageVisible);
        } else {
            setImage(tileImageInvisible);
        }
    }

    // kind of like smoothMover, but without the unnecessary features
    public void move(float x, float y)
    {
        Xaccurate = Xaccurate + x;
        Yaccurate = Yaccurate + y;
        setLocation(Math.round(Xaccurate), Math.round(Yaccurate));
    }

    void subSpecific()
    {
        //for subclasses to overwrite
    }

    public void act() 
    {
        // go to default state each act (before player act)
        // should only override this for the player
        setVisibility(tileImageVisibleDefault);
        // call to a function which is used by subclasses to do stuff they want to do (without overwriting this act() function)
        subSpecific();
    }
}