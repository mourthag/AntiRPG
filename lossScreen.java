import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Game over screen
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
