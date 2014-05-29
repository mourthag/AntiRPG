import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 */
public class Player extends Tile
{
    private boolean space;

    public Player(){
        space=false;
        speed=1;
        imageLoc = "player.png"; // choose image
        visibleAlways = true; //players are visible by default
        heightMap[1] = true; //the player is situated at height 1 and is solid
    }

    /**
     * Move if the right keys are pressed
     */
    public void movement(){
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
     * Shoot a fireball if space is pressed, and don't shoot another one until space is released and pressed again
     */
    public void attack(){
        if(Greenfoot.isKeyDown("space") && !space){
            space=true;
            //Fireball flies in direction accR-90 because the player internal direction isn't it's shooting direction
            getDungeon().addObject(new FRed(accR-90), (int)(accX+(float)Math.cos(((accR-90)/360) * 2 * Math.PI)*getDungeon().tileHeight*1.5), (int)(accY+(float)Math.sin(((accR-90)/360) * 2 * Math.PI)*getDungeon().tileHeight*1.5));
        } else if(!Greenfoot.isKeyDown("space") && space){
            space=false;
        }
    }

    /**
     * Turn if the right keys are pressed
     */
    public void turn(){
        if(Greenfoot.isKeyDown("v")) rotate(-2);
        if(Greenfoot.isKeyDown("n")) rotate(2);
    }

    /**
     * Do stuff only players but not tiles do
     */
    @Override
    public void subSpecific() {
        movement();
        attack();
        turn();
    }    
}
