import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Slow monster which throws fireballs at the player
 */
public class Monster1 extends Monster
{
    public Monster1(){
        imageLoc = "monster-brown.png"; // choose image
        health=300;
        damage=10;
        dropTime = 75;
        angularSpeed = 2;
        speed = (float)0.1;
        range = 8;
    }

    /**
     * Attacks the player
     */
    public void attack(){
        drop(new FBlue(accR-90));
    }

    /**
     * Handles all the actions of the monster, how often it performs them, ...
     */
    public void subSubSpecific(){
        shootPlayer();
        followPlayer();
    }
}
