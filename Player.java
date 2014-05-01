import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Tile
{
    int viewingRange;
    public Player(){
        viewingRange = 800;  //TODO: make the Range dependet to the size of the tiles
        speed=1;
        tileImageVisible = new GreenfootImage("player.png"); // choose image
        tileImageVisibleDefault = true; //players are visible by default
        tileHeightmap[1] = true; //the player is situated at height 1 and is solid
    }

    //make stuff visible which should be visible
    private void adjustVisibility()
    {
        List<Tile> tilesVisible = getObjectsInRange(viewingRange, Tile.class);
        for(Tile tile : tilesVisible)
        {
            tile.setVisibility(true);
        }
    }

    private void movement()
    {
        //move, and pay attention not to be faster diagonally
        int x=0;
        int y=0;
        float factor=1;
        if(Greenfoot.isKeyDown("right")) x++;
        if(Greenfoot.isKeyDown("left")) x--;
        if(Greenfoot.isKeyDown("down")) y++;
        if(Greenfoot.isKeyDown("up")) y--;
        if(x != 0 && y != 0) factor=(float)0.7071; // factor = squareRoot(0.5)
        move(x*speed*factor, y*speed*factor);
    }

    /**
     * Do stuff only players but not tiles do
     */
    @Override
    public void subSpecific() 
    {
        movement();
        adjustVisibility();
    }    
}
