import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Very advanced monster which manages to blindly scramble in the direction of the player AND shoot fireballs both at the same time (unbelievable, isn't it?)
 */
public class Boss extends Monster
{
    public Boss(){
        imageLoc = "monster-red.png";
        health=1000;
        dropTime = 50;
        damage=15;
        speed=(float)0.5;
        angularSpeed = 5;
        range = 9;
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
