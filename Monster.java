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

    public void shoot(){
        List<Tile> players = getDungeon().getObjects(Player.class);
        for(Tile p : players){
            shootFireball(directionOf(p));
        }
    }

    public void follow(){
        List<Tile> players = getDungeon().getObjects(Player.class);
        for(Tile p : players){
            move(directionOf(p));
        }
    }
}
