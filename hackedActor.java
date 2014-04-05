import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
* Some hacks to the actor class, because the actor class is like the rest of Greenfoot (a piece of shit)
*
* @author (your name)
* @version (a version number or a date)
*/
public class hackedActor extends Actor
{
    /**
* getWorld() method which actually returns the world instead of the world casted to the World class.
*/
    public Dungeon getDungeon()
    {
        return (Dungeon)getWorld(); // There doesn't seem to be a less inelegant way. Can't easily overwrite getWorld() because then there is no way I actually get the world. There actually is this.world which is a reference to the world, but it is FUCKING PRIVATE
    }
}
