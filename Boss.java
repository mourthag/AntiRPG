import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Very advanced monster which manages to blindly scramble in the direction of the player AND shoot fireballs both at the same time (unbelievable, isn't it?)
 */
public class Boss extends Monster
{
    public Boss(){
        health=300;
        damage=5;
        speed=1;
    }

    public void subSpecific(){
        counter++;
        if(counter>100) counter=0;
        if(counter==10) shoot();
        follow();
    }
}
