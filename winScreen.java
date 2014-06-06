import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Screen which is displayed when you have won.
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
