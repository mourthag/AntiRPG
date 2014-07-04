import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Very advanced monster which manages to blindly scramble in the direction of the player AND shoot fireballs both at the same time (unbelievable, isn't it?)
 */
public class Boss extends Monster
{
    public Boss(){
        imageLoc = "monster-red.png";
        health=281; //four red fireballs and one 
        dropTime = 40;
        damage=15;
        speed=(float)0.005;
        angularSpeed = 3;
        range = 10;
    }

    /**
     * Attacks the player
     */
    public void attack(){
        drop(new FPurple(accR-90));
    }

    /**
     * Handles all the actions of the monster, how often it performs them, ...
     */
    public void subSubSpecific(){
        shootPlayer();
        followPlayer();
    }
}
