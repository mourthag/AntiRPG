import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.lang.*;
import java.util.List;

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
    boolean[] tileHeightmap; //height map, height two for now (needs to be consistent among tiles, but should stay easily changeable for a slightly different game
    int tileHeight; //height

    public Tile()
    {
        tileImageVisible = new GreenfootImage("none.png");
        tileImageInvisible = new GreenfootImage("obfuscated.png");
        tileImageVisibleDefault = false;
        tileHeight = 2;
        tileHeightmap = new boolean[tileHeight]; 
        for(int h=0; h<tileHeight; h++) //default tile isn't solid at all
        {
            tileHeightmap[h]=false;
        }
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

    public void updatePreciseLocation(float x, float y)
    {
        setLocation(Math.round(x), Math.round(y));
    }

    // kind of like smoothMover, but without the unnecessary features
    //todo: collisions
    public void move(float x, float y)
    {
        Xaccurate += x;
        Yaccurate += y;
        updatePreciseLocation(Xaccurate, Yaccurate);
        if(colliding()) // just reverse the move before displaying it if it would lead to a collision. Might need to be changed to something more intelligent later on
        {
            Xaccurate -= x;
            Yaccurate -= y;
            updatePreciseLocation(Xaccurate, Yaccurate);
        }
    }

    public boolean colliding()
    {
        List<Tile> tilesVisible = getIntersectingObjects(Tile.class); //visually intersecting tiles
        for(Tile otherTile : tilesVisible)
        {
            for(int i=0; i < tileHeight; i++)
            {
                if(otherTile.tileHeightmap[i] && tileHeightmap[i]) //logically intersecting?
                {
                    return(true);
                }
            }
        }
        return(false);
    }

    void subSpecific()
    {
        //for subclasses to overwrite
    }

    public void act() 
    {
        // go to default state each act (before player act)
        setVisibility(tileImageVisibleDefault);
        // call to a function which is used by subclasses to do stuff they want to do (without overwriting this act() function)
        subSpecific();
    }
}