import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.lang.*;
import java.util.List;

/**
 * Tiles are our kind of Actors, with some additional features.
 */
public class Tile extends hackedActor
{
    //Tile's images
    GreenfootImage tileImageVisible;
    GreenfootImage tileImageInvisible;
    boolean tileImageVisibleDefault; //choose whether tile is usually visible to player
    //Tile's location and orientation
    boolean accXYRInit;
    float accX;
    float accY;
    float accR;

    //The parrent MetaTile whose member this Tile is
    metaTile parent;

    float speed; //speed in pixels/act, automatically initialized to 0

    boolean[] tileHeightmap; //height map, height two for now (needs to be consistent among tiles, but should stay easily changeable for a slightly different game
    int tileHeight; //height

    public Tile()
    {
        //set up Tile's images
        tileImageVisible = new GreenfootImage("none.png");
        tileImageInvisible = new GreenfootImage("obfuscated.png");
        tileImageVisibleDefault = false;

        //set up Tile's heightmap
        tileHeight = 2;
        tileHeightmap = new boolean[tileHeight]; 
        for(int h=0; h<tileHeight; h++) //default tile isn't solid at all
        {
            tileHeightmap[h]=false;
        }

        //set up Tile's location and orientation
        accXYRInit=false;
    }

    /**
     * Copy constructor
     */
    public Tile(Tile another){
        this.tileImageVisible = another.tileImageVisible;
        this.tileImageInvisible = another.tileImageInvisible;
        this.tileImageVisibleDefault = another.tileImageVisibleDefault;

        this.tileHeight = another.tileHeight;
        this.tileHeightmap = another.tileHeightmap;
    }

    //run when placing the object in the world
    @Override
    protected void addedToWorld(World world)
    {
        //scale Tile's images to tile size of the world
        tileImageInvisible.scale(getDungeon().tileWidth, getDungeon().tileHeight);
        tileImageVisible.scale(getDungeon().tileWidth, getDungeon().tileHeight);

        //set default image
        if(tileImageVisibleDefault){
            setImage(tileImageVisible);
        } else {
            setImage(tileImageInvisible);
        }

        //initialize accurate location and orientation storage
        if(!accXYRInit){
            accXYRInit();
        } else{
            accXYRUpdate();
        }
    }

    public void accXYRInit(float x, float y, float r){
        accX=x;
        accY=y;
        accR=r;
        accXYRInit=true;
    }

    public void accXYRInit(){
        accXYRInit(getX(), getY(), getRotation());
    }

    public void accXYRUpdate()
    {
        setLocation(Math.round(accX), Math.round(accY));
        setRotation(Math.round(accR));
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
    //todo: collisions
    public void move(float x, float y)
    {
        accX += x;
        accY += y;
        accXYRUpdate();
        if(colliding()) // just reverse the move before displaying it if it would lead to a collision. Might need to be changed to something more intelligent later on
        {
            accX -= x;
            accY -= y;
            accXYRUpdate();
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

    /**
     * Returns whether another Door is already in the predefined range
     */
    public boolean doorsInRange()
    {
        if(getObjectsInRange(getDungeon().blockedDoorRange, Door.class).isEmpty())
        {
            return false;
        }
        return true;
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