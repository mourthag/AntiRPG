import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A kind of Fireball with a non-default image and damage value.
 */
public class FBlue extends Fireball
{
    public FBlue(float direction){
        super(direction);
        imageLoc = "fireball-blue.png";
        damage = 49;
        maxAge = 40;
        speed = 3;
    }    
}
