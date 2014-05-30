import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Monster here.
 */
public class Monster extends Tile
{
    int counter;

    public Monster() 
    {
        imageLoc = "baby2.png"; // choose image
        heightMap[1] = true; //monsters are situated at height 1 and are solid
    }

    public void subSpecific(){
        counter++;
        if(counter>100) counter=0;
        if(counter==10){
            List<Tile> players = getDungeon().getObjects(Player.class);
            for(Tile p : players){
                shootFireball(directionOf(p));
            }
        }
    }
}
