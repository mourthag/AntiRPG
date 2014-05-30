import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Stationary monster which throws fireballs at the player
 */
public class Monster1 extends Monster
{
    //Fireball throwing
    public Monster1(){
        health=100;
        damage=50;
    }

    public void subSpecific(){
        counter++;
        if(counter>100) counter=0;
        if(counter==10) shoot();
    }
}
