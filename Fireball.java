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
    int age;

    public Fireball(float dir){
        heightMap[1] = true;
        speed = 3;
        direction = dir;
        damage=10;
    }

    /**
     * Fireball hit something. Yay!
     * We need to disappear because we are used up.
     */
    public void Hit(Tile tile){
        disappear=true; //can't just delete it right now, would break stuff which expects it to stay there for another few lines
    }

    public void subSpecific(){
        age++;
        if(age > 30) disappear=true;
        rotate(10); //pretty expensive, rotate() needs some optimizing if we keep that
        move(direction);
        if(disappear){
            getDungeon().removeObject(this);
        }
    }
}
