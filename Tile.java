import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.lang.*;
import java.util.List;

/**
 * Tiles are our kind of Actors, with some additional features.
 */
public class Tile extends hackedActor{
    //Tile's image
    String imageLoc; //where to get the image file
    private GreenfootImage image; //the GreenfootImage
    boolean visibleAlways; //choose whether tile is visible to player even when it shouldn't be

    //Tile's location and orientation
    boolean accXYRInit;
    float accX;
    float accY;
    float accR;

    //The parent MetaTile whose member this Tile is
    metaTile parent;

    //speed in pixels/act, automatically initialized to 0
    float speed;

    public int health;

    public int damage;

    //Tile's geometry
    static int height; //height
    boolean[] heightMap; //height map, height two for now (needs to be consistent among tiles, but should stay easily changeable for a slightly different game
    boolean[][] colMap; // collision map

    public Tile(){
        //set up Tile's images
        imageLoc = "none.png";
        visibleAlways = false;
        
        health=100; //just some default value
        
        damage=0; //just some default value

        //set up Tile's height map
        height = 2; // Don't change in subclasses or the system will go bananas (I guarantee for nothing)
        heightMap = new boolean[height]; //Set up a completely non-solid height map
        for(int h=0; h<height; h++) 
        {
            heightMap[h]=false;
        }

        //set up Tile's collision map

        //set up Tile's location and orientation
        accXYRInit=false;
        accR = 0;
    }

    /**
     * Copy constructor
     * Doesn't really work properly yet.
     */
    public Tile(Tile another){
        this.imageLoc = another.imageLoc;
        this.visibleAlways = another.visibleAlways;

        this.height = another.height;
        this.heightMap = another.heightMap;
    }

    /*
     * Run when placing the object in the world
     */
    @Override
    protected void addedToWorld(World world){
        setUpImage();

        setUpColMap();

        //initialize accurate location and orientation storage
        if(!accXYRInit){
            accXYRInit();
        } else{
            accXYRUpdate();
        }
    }

    /*
     * Set up Tile's image
     * Assumes accR contains a correct value and rotates the image accordingly, should be a safe assumption
     */
    public void setUpImage(){
        image = new GreenfootImage(imageLoc);
        int width = image.getWidth();
        int height = image.getHeight();
        image.rotate((int)accR);
        //calculate the size the image should be scaled to
        int rwidth = Math.round( (image.getWidth() / width) * getDungeon().tileWidth );
        int rheight = Math.round( (image.getHeight() / height) * getDungeon().tileHeight );
        image.scale(rwidth, rheight);
        setImage(image);
    }

    /*
     * Set up Tile's image with the image loaded from loc
     */
    public void setUpImage(String loc){
        imageLoc = loc;
        setUpImage();
    }

    /*
     * Sets up the Tile's collision map based on it's image
     */
    public void setUpColMap(GreenfootImage image){
        colMap = new boolean[image.getWidth()][image.getHeight()];
        if(!(image.getTransparency() == 0)){
            for(int i=0; i < image.getWidth(); i++){
                for(int j=0; j < image.getHeight(); j++){
                    colMap[i][j] = image.getColorAt(i, j).getAlpha() != 0;
                }
            }
        } else {
            for(int i=0; i < image.getWidth(); i++){
                for(int j=0; j < image.getHeight(); j++){
                    colMap[i][j] = false;
                }
            }
        }
    }

    public void setUpColMap(){
        GreenfootImage rotatedImage = new GreenfootImage(image);
        // !! don't leave like this
        //rotatedImage.rotate(getRotation());
        setUpColMap(rotatedImage);
    }

    /*
     * Initialize our internal location/rotation tracking
     */
    public void accXYRInit(float x, float y, float r){
        accX=x;
        accY=y;
        accR=r;
        accXYRInit=true;
    }

    /*
     * Initialize our internal location/rotation tracking by taking Greenfoot's values
     */
    public void accXYRInit(){
        accXYRInit(getX(), getY(), getRotation());
    }

    /*
     * Set Greenfoot location/rotation values to our internal ones.
     * (not rotation right now, probably never, Greenfoot just messes up when we use it)
     */
    public void accXYRUpdate(){
        setLocation(Math.round(accX), Math.round(accY));
        //ignoring rotation, Greenfoot just messes up anyway
        //setRotation(Math.round(accR));
    }

    // kind of like smoothMover, but without the unnecessary features
    public void move(float x, float y){
        accX += x;
        accY += y;
        accXYRUpdate();
        if(colliding()){ // just reverse the move before displaying it if it would lead to a collision. Might need to be changed to something more intelligent later on
            move(-x,-y);
        }
    }

    public void move(float direction){
        move((float)Math.cos((direction/360) * 2 * Math.PI)*speed, (float)Math.sin((direction/360) * 2 * Math.PI)*speed);
    }

    public void rotate(float r){
        accR += r;
        setUpImage();
        setUpColMap();
        if(colliding()){ // just reverse the rotation before displaying it if it would lead to a collision. Might need to be changed to something more intelligent later on
            rotate(-r);
        }
    }

    /*
     * Returns true when this Tile's collision map intersects with oColMap which is at oX|oY
     * oColMap is assumed to have the same size as this Tile's colMap
     */
    public boolean colMapIntersect(boolean[][] oColMap, int oX, int oY){

        boolean selfStartX = oX>=getX() ? true : false;
        boolean selfStartY = oY>=getY() ? true : false;

        int startX = Math.abs(oX-getX());
        int startY = Math.abs(oY-getY());

        int dsx = selfStartX ? startX : 0;
        int dsy = selfStartY ? startY : 0;
        int dox = selfStartX ? 0 : startX;
        int doy = selfStartY ? 0 : startY;

        for(int i=0; i < getDungeon().tileWidth-startX; i++){
            for(int j=0; j < getDungeon().tileHeight-startY; j++){
                if(oColMap[i+dox][j+doy] && colMap[i+dsx][j+dsy]) return(true);
            }
        }
        return(false);
    }

    public boolean colliding(){
        List<Tile> tilesVisible = getIntersectingObjects(Tile.class); //visually intersecting tiles
        for(Tile otherTile : tilesVisible)
        {
            for(int i=0; i < height; i++)
            {
                if(otherTile.heightMap[i] && heightMap[i]) //logically intersecting?
                {
                    if(colMapIntersect(otherTile.colMap, otherTile.getX(), otherTile.getY())){// collision map intersecting?
                        Hit(otherTile);
                        return(true);
                    }
                }
            }
        }
        return(false);
    }

    /**
     * Returns whether another Door is already in the predefined range
     */
    public boolean doorsInRange(){
        if(getObjectsInRange(getDungeon().blockedDoorRange, Door.class).isEmpty())
        {
            return false;
        }
        return true;
    }

    /**
     * Method which subclasses should overwrite instead of act
     */
    void subSpecific(){
    }

    /**
     * Called when that Tile was hit by another Tile
     */
    void gotHit(Tile tile){
        health -= tile.damage;
    }

    /**
     * Called when that Tile hits another Tile
     * If something should happen this is overwritten
     */
    void Hit(Tile tile){
    }

    public void act(){
        if(getDungeon() != null){
            // call to a function which is used by subclasses to do stuff they want to do (without overwriting this act() function)
            subSpecific();
        }
        
        //DIE!1!!
        if(health < 1){
            getDungeon().removeObject(this);
        }
    }
}