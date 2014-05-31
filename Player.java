import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 */
public class Player extends Tile
{

    public Player(){
        health=100;
        damage=5;
        space=false;
        speed=1;
        angularSpeed=3;
        imageLoc = "player.png"; // choose image
        visibleAlways = true; //players are visible by default
        heightMap[1] = true; //the player is situated at height 1 and is solid
    }


    /**
     * Do stuff only players but not tiles do
     */
    @Override
    public void subSpecific() {
        PlayerMovement();
        PlayerAttack();
        PlayerTurn();
    }    
}
