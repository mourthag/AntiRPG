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
    float angularSpeed;

    public int health;

    public int damage;

    boolean space;

    //Tile's geometry
    static int height; //height
    boolean[] heightMap; //height map, height two for now (needs to be consistent among tiles, but should stay easily changeable for a slightly different game
    boolean[][] colMap; // collision map

    /**
     * Default constructor
     */
    public Tile(){
        //set up Tile's images
        imageLoc = "none.png";
        visibleAlways = false;

        health=100000000; //just some default value. Large, because default Tiles should stay

        damage=1; //just some default value. Bigger than one: Fuck players/monsters who scrape along walls

        //set up Tile's height map
        height = 2; // Don't change in subclasses or the system will go bananas (I guarantee for nothing)
        heightMap = new boolean[height]; //Set up a completely non-solid height map
        for(int h=0; h<height; h++) 
        {
            heightMap[h]=false;
        }

        //set up Tile's location and orientation
        accXYRInit=false;
        accR = 0;
    }

    /**
     * Copy constructor
     * Doesn't really work properly yet. (in most important cases it does)
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

    /**
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

    /**
     * Set up Tile's image with the image loaded from loc
     */
    public void setUpImage(String loc){
        imageLoc = loc;
        setUpImage();
    }

    /**
     * Sets up the Tile's collision map based on image
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

    /**
     * Sets up the collision map based on it's image
     */
    public void setUpColMap(){
        setUpColMap(image);
    }

    /**
     * Initialize our internal location/rotation tracking
     */
    public void accXYRInit(float x, float y, float r){
        accX=x;
        accY=y;
        accR=r;
        accXYRInit=true;
    }

    /**
     * Initialize our internal location/rotation tracking by taking Greenfoot's values
     */
    public void accXYRInit(){
        accXYRInit(getX(), getY(), getRotation());
    }

    /**
     * Set Greenfoot location/rotation values to our internal ones.
     * (not rotation right now, probably never, Greenfoot just messes up when we use it)
     */
    public void accXYRUpdate(){
        setLocation(Math.round(accX), Math.round(accY));
        //ignoring rotation, Greenfoot just messes up anyway
        //setRotation(Math.round(accR));
    }

    /**
     * Default values for move(float x, float y, boolean collisions, boolean asMuchAsPossible)
     */
    public void move(float x, float y){
        move(x, y, true, false);
    }

    /**
     * Moves the Tile.
     * This works around Greenfoot in several ways.
     * collisions: whether to check for collisions. Assumed to be true when asMuchAsPossible is
     * asMuchAsPossible: moves as much towards the desired position as (easily) possible without collisions. It's not very smart because that would be difficult/expensive, but it's a start
     */
    public void move(float x, float y, boolean collisions, boolean asMuchAsPossible){
        if(!asMuchAsPossible){
            accX += x;
            accY += y;
            accXYRUpdate();
            if(collisions && colliding()){ //assumes the if stops once the first is false, because otherwise that would be stupid
                move(-x,-y, false, false); //when moving back, don't check for collisions  or this could lead to endless loops
            }
        } else {
            //VERY cheap and easy way to solve it, but don't need more right now
            move(x, 0);
            move(0, y);
        }
    }

    /**
     * Move the Tile in the direction by this.speed,
     * collisions and asMuchAsPossible like in move(float x, float y, boolean collisions, boolean asMuchAsPossible)
     */
    public void move(float direction, boolean collisions, boolean asMuchAsPossible){
        move((float)Math.cos((direction/360) * 2 * Math.PI)*speed, (float)Math.sin((direction/360) * 2 * Math.PI)*speed, collisions, asMuchAsPossible);
    }

    /**
     * Default values for move(float direction, boolean collisions, boolean asMuchAsPossible)
     */
    public void move(float direction){
        move(direction, true, false);
    }

    /**
     * Takes any angle and outputs an equivalent angle in the range -180;180
     */
    public float normAngle(float ang){
        while(ang > 180) ang -= 360;
        while(ang < -180) ang += 360;
        return(ang);
    }

    /**
     * Rotates the Tile by r.
     * This works around Greenfoot in several ways, Greenfoot is never notified of the rotation.
     * This is pretty exensive, because the collision map needs to be regenerated
     * and we can't rotate the small displayed images (because that looks like crap)
     * but need to rotate the large originals and scale the result
     */
    public void rotate(float r){
        accR += r;
        accR = normAngle(accR);
        setUpImage();
        setUpColMap();
        if(colliding()){
            rotate(-r);
        }
    }

    /**
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

    /**
     * Returns true when there are any collision with this Tile.
     * It does three checks for collisions, the fastest first,
     * and skips the rest of the tests if the previous ones were sufficient to
     * determine there are no collisions
     */
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
                        otherTile.gotHit(this);
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

    /**
     * Move if the right keys are pressed
     */
    public void PlayerMovement(){
        //move, and pay attention not to be faster diagonally
        int x=0;
        int y=0;

        if(Greenfoot.isKeyDown("right")) x++;
        if(Greenfoot.isKeyDown("left")) x--;
        if(Greenfoot.isKeyDown("down")) y++;
        if(Greenfoot.isKeyDown("up")) y--;
        if((x == 0) && (y == 0)) return; // nothing to do, spare us the call to move()

        float factor=1;
        if(x != 0 && y != 0) factor=(float)0.7071; // factor = squareRoot(0.5)
        move(x*speed*factor, y*speed*factor, true, true);
    }

    /**
     * Shoot a fireball if space is pressed, and don't shoot another one until space is released and pressed again
     */
    public void PlayerAttack(){
        if(Greenfoot.isKeyDown("space") && !space){
            space=true;
            drop(new FRed(accR-90));
        } else if(!Greenfoot.isKeyDown("space") && space){
            space=false;
        }
    }

    /**
     * Drop tile in front of this.
     */
    public void drop(Tile tile){
        //in front of == accR - 90
        getDungeon().addObject(tile, (int)(accX+(float)Math.cos(((accR-90)/360) * 2 * Math.PI)*getDungeon().tileHeight*1.5), (int)(accY+(float)Math.sin(((accR-90)/360) * 2 * Math.PI)*getDungeon().tileHeight*1.5));
        if(tile.colliding()) getDungeon().removeObject(tile);
    }

    /**
     * Turn if the right keys are pressed
     */
    public void PlayerTurn(){
        if(Greenfoot.isKeyDown("v")) turn(false);
        if(Greenfoot.isKeyDown("n")) turn(true);
    }

    /**
     * Turn by angularSpeed
     */
    public void turn(boolean dir){
        rotate(dir ? angularSpeed : -angularSpeed);
    }

    /**
     * Turn towards Tile tile
     */
    public void turnTowards(Tile tile){
        if(normAngle(accR + 90 - directionOf(tile)) > 0){
            turn(true);
        }else{
            turn(false);
        }
    }

    /**
     * Get the direction of another Tile from the viewpoint of this Tile
     */
    public float directionOf(Tile tile){
        float dx = tile.accX-accX;
        float dy = tile.accY-accY;
        float dir = (float)(( - Math.atan2(dx, dy ) / (2*Math.PI)) * 360 + 90);
        return(normAngle(dir));
    }

    public void act(){
        if(getDungeon() != null){
            // call to a function which is used by subclasses to do stuff they want to do (without overwriting this act() function)
            subSpecific();

            //DIE!1!!
            if(health < 1){
                getDungeon().removeObject(this);
            }
        }

    }
}