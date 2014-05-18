import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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

    }

    /**
     * Add all of the metaTile's Tiles to the world
     */
    @Override
    protected void addedToWorld(World world){
        for(Tile tileToPlace : tiles){
            getDungeon().addObject(tileToPlace, 1337, 1337); //location param is ignored by our Tiles
        }
    }

    /**
     * Add a Tile to this metaTile.
     * Can be used with completely new Tiles which don't yet have X/Y/R. If they do, this method doesn't care and overwrites the values.
     */
    void add(Tile tileToAdd, float x, float y, float r){
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
    void add(Tile tileToAdd){
        if(tileToAdd.accXYRInit){
            add(tileToAdd, tileToAdd.accX, tileToAdd.accY, tileToAdd.accR);
        }
    }

    /**
     * Separate a Tile from this metaTile.
     * Pay attention, it fails silently when used on Tiles which aren't actually in metaTile.
     */
    void separate(Tile tileToRemove){
        if(tiles.contains(tileToRemove)){
            tiles.remove(tileToRemove);
        }
    }

    /**
     * Remove a Tile from this metaTile and the World.
     * Pay attention, it fails silently when used on Tiles which aren't actually in metaTile.
     */
    void remove(Tile tileToRemove){
        if(tiles.contains(tileToRemove)){
            tiles.remove(tileToRemove);
            tileToRemove.getDungeon().removeObject(tileToRemove); //Greenfoot at it's best.
        }
    }

    void move(float x, float y){
        for(Tile tile : tiles){
            tile.move(x, y);
        }
    }

    void subSpecific()
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
