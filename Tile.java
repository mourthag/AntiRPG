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
    GreenfootImage imageVisible;
    GreenfootImage imageInvisible;
    boolean imageVisibleDefault; //choose whether tile is usually visible to player
    //Tile's location and orientation
    boolean accXYRInit;
    float accX;
    float accY;
    float accR;

    float speed; //speed in pixels/act, automatically initialized to 0

    //Tile's geometry
    static int height; //height
    static boolean[] heightMap; //height map, height two for now (needs to be consistent among tiles, but should stay easily changeable for a slightly different game
    static boolean[][] colMap;

    public Tile()
    {
        //set up Tile's images
        imageVisible = new GreenfootImage("none.png");
        imageInvisible = new GreenfootImage("obfuscated.png");
        imageVisibleDefault = false;

        //set up Tile's height map
        height = 2;
        heightMap = new boolean[height]; 
        for(int h=0; h<height; h++) //default tile isn't solid at all
        {
            heightMap[h]=false;
        }

        //set up Tile's collision map

        //set up Tile's location and orientation
        accXYRInit=false;
    }

    /**
     * Copy constructor
     */
    public Tile(Tile another){
        this.imageVisible = another.imageVisible;
        this.imageInvisible = another.imageInvisible;
        this.imageVisibleDefault = another.imageVisibleDefault;

        this.height = another.height;
        this.heightMap = another.heightMap;
    }

    //run when placing the object in the world
    @Override
    protected void addedToWorld(World world)
    {
        //scale Tile's images to tile size of the world
        imageInvisible.scale(getDungeon().tileWidth, getDungeon().tileHeight);
        imageVisible.scale(getDungeon().tileWidth, getDungeon().tileHeight);

        //set default image
        if(imageVisibleDefault){
            setImage(imageVisible);
        } else {
            setImage(imageInvisible);
        }

        //initialize accurate location and orientation storage
        if(!accXYRInit){
            accXYRInit();
        } else{
            accXYRUpdate();
        }
    }

    public void setUpImages(){
        //Working here right now
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
            setImage(imageVisible);
        } else {
            setImage(imageInvisible);
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
            for(int i=0; i < height; i++)
            {
                if(otherTile.heightMap[i] && heightMap[i]) //logically intersecting?
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
        setVisibility(imageVisibleDefault);
        // call to a function which is used by subclasses to do stuff they want to do (without overwriting this act() function)
        subSpecific();
    }
}