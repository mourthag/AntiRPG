import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 */
public class Player extends Tile
{
    int viewingRange;
    public Player(){
        viewingRange = 800;  //TODO: make the Range dependent of the size of the tiles
        speed=1;
        imageLoc = "player.png"; // choose image
        visibleAlways = true; //players are visible by default
        heightMap[1] = true; //the player is situated at height 1 and is solid
    }

    private void movement()
    {
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
        move(x*speed*factor, y*speed*factor);
    }

    /**
     * Do stuff only players but not tiles do
     */
    @Override
    public void subSpecific() 
    {
        movement();
    }    
}
