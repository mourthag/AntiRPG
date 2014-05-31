import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Monster which always tries to run in the direction of the player
 */
public class Monster2 extends Monster
{
    public Monster2(){
        health=100;
        damage=5;
        speed=(float)0.5;
        angularSpeed=6;
    }   

    public void subSubSpecific(){
        counter++;
        if(counter>100) counter=0;
        followPlayer();
    }
}
