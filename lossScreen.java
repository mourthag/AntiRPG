import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class endScreen here.
 */
public class lossScreen extends Screen
{  
    /**
     * Display the "GameOver" message and the score
     */
    public lossScreen(Integer score)
    {
       super("GameOver", "Your Score:",score.toString());
    }
}
