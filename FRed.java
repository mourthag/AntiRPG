import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A kind of Fireball with a non-default image and damage value.
 */
public class FRed extends Fireball
{
    public FRed(float direction){
        super(direction);
        imageLoc = "fireball.png";
        damage = 70;
        maxAge = 90;
    }    
}
