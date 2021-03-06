import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * A metaTile refers to multiple Tiles. It can be used to perform operations on those Tiles and to store additional information on a group of Tiles.
 */
public class metaTile extends hackedActor
{
    List<Tile> tiles; //tiles belonging to the metaTile
    //subclasses can define the variable

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
            tileToAdd.parent = this;
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

    /**
     * Adds a lines of Tiles to the metaTile.
     * tile: Each Tile this method adds is a copy of this Tile. Couldn't find a better way, doesn't work with all Tiles (yet, I guess I can restructure some stuff so it does).
     * Works with static Tiles like Walls and Floor right now, which don't differ too much from the Tile class.
     */
    public void addLine(Tile tile, int startX, int startY, int length, boolean horizontal, boolean vertical){
        int x = horizontal ? 1 : 0;
        int y = vertical ? 1 : 0;
        float r = tile.accXYRInit ? tile.accR : 0;
        for(int i=0; i<length; i++){
            add(new Tile(tile), startX + x*i*getDungeon().tileWidth, startY + y*i*getDungeon().tileHeight, r);
        }
    }

    /**
     * Adds a square of tiles to the metaTile.
     * See addLine()
     * fill: true is a filled square, false an outline
     */
    public void addSquare(Tile tile, int startX, int startY, int width, int height, boolean fill){
        if(fill){
            for(int i=0; i<height; i++){

                addLine(tile, startX, startY + i*getDungeon().tileHeight, width, true, false);

            }
        } else {
            //top
            addLine(tile, startX, startY, width-1, true, false);
            //right
            addLine(tile, startX+(width-1)*getDungeon().tileWidth, startY, height-1, false, true);
            //bottom
            addLine(tile, startX+getDungeon().tileWidth, startY+(height-1)*getDungeon().tileHeight, width-1, true, false);
            //left
            addLine(tile, startX, startY+getDungeon().tileWidth-1, height-1, false, true);
        }
    }

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

    /**
     * Call remove(tile) for a whole list of Tiles
     */
    public void remove(List<Tile> tilesToRemove){
        for(Tile tileToRemove :  tilesToRemove){
            remove(tileToRemove);
        }
    }

    /**
     * Remove the metaTile and all of it's Tiles from the world
     */
    public void remove(){
        for(Tile tile : tiles){
            tile.getDungeon().removeObject(tile);
        }
        getDungeon().removeObject(this);
    }

    /**
     * Delete (remove from world and destroy) all Tile's belonging to that metaTile
     */
    public void empty(){
        while(tiles.size() > 0){
            Tile toRemove = tiles.remove(0);
            toRemove.getDungeon().removeObject(toRemove); //Greenfoot at it's best.
        }
    }

    /**
     * Return true if any of this metaTile's tiles are colliding with something at the moment, false otherwise.
     */
    public boolean colliding(){
        for(Tile tile : tiles){
            if(tile.colliding()) return(true);
        }
        return(false);
    }

    /**
     * Moves all Tiles of the metaTile.
     * If you set colCheck to false, individual moves will still fail if they would lead to collisions, so you would probably not get the result you expected
     */
    public void move(float x, float y, boolean colCheck){
        for(Tile tile : tiles){
            tile.move(x, y);
        }
        if(colCheck && colliding()) move(-x, -y, false);
    }

    /**
     * move(float x, float y, boolean colCheck) method with colCheck set to true.
     */
    public void move(float x, float y){
        move(x, y, true);
    }

    /**
     * For subclasses to overwrite, called by act()
     */
    public void subSpecific()
    {

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
