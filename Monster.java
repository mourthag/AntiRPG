import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Monster here.
 */
public class Monster extends Tile
{
    boolean active;
    Tile target;
    float range; //multiplied by tile size

    public Monster() 
    {
        imageLoc = "monster.png"; // choose image
        heightMap[1] = true; //monsters are situated at height 1 and are solid
        damage = 100;
        health = 1000;
        active = false;
        range = 6;
    }

    /**
     * Does monster specific attack, to be overwritten
     */
    public void attack(){

    }

    /**
     * Shoots the target if this is facing it
     */
    public void shootPlayer(){
        if(active){
            if(Math.abs(normAngle(directionOf(target)-accR+90)) < 10){
                attack();
            }
        }

    }

    /**
     * Follows the target (moves and turns towards it) if it is in range
     */
    public void followPlayer(){
        if(active){
            move(directionOf(target), true, true);
            turnTowards(target);
        }
    }

    /**
     * Like subSpecific, but here we need another level
     */
    public void subSubSpecific(){
    }

    public void subSpecific(){
        subSubSpecific();
        //
        if(getObjectsInRange(Math.round(getDungeon().tileWidth*range), Player.class).size() > 0){
            active = true;
            target = (Tile) getObjectsInRange(Math.round(getDungeon().tileWidth*range), Player.class).get(0);
        } else {
            active = false;
        }
    }
}
