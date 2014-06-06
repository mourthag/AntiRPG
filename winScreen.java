import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class wonScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class winScreen extends Screen
{
    /**
     * Display the congratulation message and the score
     */
    public winScreen(Integer score)
    {
       super("Congratulation","You won", "Your Score:",score.toString());
    }
}
