import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * A metaTile refers to multiple Tiles. It can be used to perform operations on those Tiles and to store additional information on a group of Tiles.
 * Operations:
 *  * relative move
 *  * ...
 */
public class metaTile extends hackedActor
{
    List<Tile> tiles; //tiles belonging to the metaTile
    //subclasses define the variable

    /**
     * Initialize object
     * There is nothing to do, really. Everything happens when it is added to the world.
     */
    public metaTile(){
        tiles = new ArrayList<Tile>();
    }

    /**
     * Add all of the metaTile's Tiles to the world
     */
    @Override
    protected void addedToWorld(World world){
        GreenfootImage image = new GreenfootImage("none.png");
        image.scale(getDungeon().tileWidth, getDungeon().tileHeight);
        setImage(image);
        for(Tile tileToPlace : tiles){
            getDungeon().addObject(tileToPlace, 1337, 1337); //location param is ignored by our Tiles
        }
    }

    /**
     * Add a Tile to this metaTile.
     * Can be used with completely new Tiles which don't yet have X/Y/R. If they do, this method doesn't care and overwrites the values.
     */
    public void add(Tile tileToAdd, float x, float y, float r){
        if(!tiles.contains(tileToAdd)){ //If tileToAdd isn't already part of this metaTile
            tiles.add(tileToAdd);
            tileToAdd.accXYRInit(x, y, r);
            if(getDungeon() != null){ //metaTile is already in a world
                getDungeon().addObject(tileToAdd, 1337, 1337); //location param is ignored by our Tiles
            }
        }
    }

    /**
     * Add a Tile to this metaTile.
     * This method assumes the Tile already has X/Y/R
     * Pay attention, it FAILS SILENTLY if the tileToAdd.accXYRInit == false.
     */
    public void add(Tile tileToAdd){
        if(tileToAdd.accXYRInit){
            add(tileToAdd, tileToAdd.accX, tileToAdd.accY, tileToAdd.accR);
        }
    }

    public void addLine(Tile tile, int startX, int startY, int length, boolean horizontal, boolean vertical){
        int x = horizontal ? 1 : 0;
        int y = vertical ? 1 : 0;
        float r = tile.accXYRInit ? tile.accR : 0;
        for(int i=0; i<length; i++){
            add(new Tile(tile), startX + x*i*getDungeon().tileWidth, startY + y*i*getDungeon().tileHeight, r);
        }
    }
    
    //public void addSquare(Tile tile,

    /**
     * Separate a Tile from this metaTile.
     * Pay attention, it fails silently when used on Tiles which aren't actually in metaTile.
     */
    public void separate(Tile tileToRemove){
        if(tiles.contains(tileToRemove)){
            tiles.remove(tileToRemove);
        }
    }

    /**
     * Remove a Tile from this metaTile and the World.
     * Pay attention, it fails silently when used on Tiles which aren't actually in metaTile.
     */
    public void remove(Tile tileToRemove){
        if(tiles.contains(tileToRemove)){
            tiles.remove(tileToRemove);
            tileToRemove.getDungeon().removeObject(tileToRemove); //Greenfoot at it's best.
        }
    }

    public void move(float x, float y){
        for(Tile tile : tiles){
            tile.move(x, y);
        }
    }

    public void subSpecific()
    {
        //for subclasses to overwrite
    }

    /**
     * Act - do whatever the metaTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // call to a function which is used by subclasses to do stuff they want to do (without overwriting this act() function)
        subSpecific();
    }    
}
