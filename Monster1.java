import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Slow monster which throws fireballs at the player
 */
public class Monster1 extends Monster
{
    public Monster1(){
        health=100;
        damage=50;
        angularSpeed = 2;
        speed = (float)0.05;
    }

    public void attack(){
        drop(new FBlue(accR-90));
    }

    /**
     * Handles all the actions of the monster, how often it performs them, ...
     */
    public void subSubSpecific(){
        counter++;
        if(counter>100) counter=0;
        if(counter==10) shootPlayer();
        followPlayer();
    }
}
