import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A kind of Fireball with a non-default image and damage value.
 */
public class FPurple extends Fireball
{
    public FPurple(float direction){
        super(direction);
        imageLoc = "fireball-purple.png";
        damage=49;
        maxAge=25;
        speed=5;
    }   
}
