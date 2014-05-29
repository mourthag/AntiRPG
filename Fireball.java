import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Fireball extends Tile
{
    float direction;
    boolean disappear;

    public Fireball(float dir){
        heightMap[1] = true;
        speed = 3;
        direction = dir;
    }

    /**
     * Fireball hit something. Yay!
     * Now notify the thing we hit that it got hit. Otherwise it won't notice.
     * And then we need to disappear because we are used up.
     */
    public void Hit(Tile tile){
        tile.gotHit(this);
        disappear=true; //can't just delete it right now, would break stuff which expects it to stay there for another few lines
    }

    public void subSpecific(){
        rotate(10);
        move(direction);
        if(disappear){
            getDungeon().removeObject(this);
        }
    }
}
