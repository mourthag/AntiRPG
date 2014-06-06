import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Monster which always tries to run in the direction of the player
 */
public class Monster2 extends Monster
{
    public Monster2(){
        health=300;
        damage=1;
        speed=(float)0.8;
        angularSpeed=6;
        range = 10;
    }   

    /**
     * Handles all the actions of the monster, how often it performs them, ...
     */
    public void subSubSpecific(){
        followPlayer();
    }
}
