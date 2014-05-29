import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Wall here.
 */
public class Wall extends Tile
{

    public Wall() 
    {
        imageLoc = "brick.jpg"; // choose image
        heightMap[0] = true; //floors are at height 0
        heightMap[1] = true; //... and 1.
    }

    /**
     * 
     */
    public int roomsInRange()
    {
        int roomsInRange = 0;
        int distance;
        for(distance = 0; roomsInRange == 0; distance++)
        {
            List<Room> allRooms = getObjectsInRange(distance, Room.class);
            for(Room curRoom: allRooms)
            {
                if(curRoom != parent) roomsInRange++;              //check all walls and add those who arent applied to the Parent of this Wall
            }
        }
        return distance;
    }
}
