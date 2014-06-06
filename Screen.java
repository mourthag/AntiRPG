import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
import java.lang.Math;

/**
 * Class for text displaying objects.
 */
public class Screen extends hackedActor
{
    static final float FONT_SIZE = 48.0f;

    /**
     * Act - do whatever the Screen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }  

    public Screen(String... Text)
    {
        makeImage(Text);

    }

    /**
     * Erstellt ein Textfeld
     */
    private void makeImage(String... Text)
    {
        int WIDTH = getDungeon().frameWidth;
        int HEIGHT = getDungeon().frameHeight;
        int i = 0;
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(255,255,255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);

        for(String curText: Text)
        {
            image.drawString(curText, WIDTH / 2 - Math.round(curText.length() / 2) * 20, 100 +i * HEIGHT/Text.length);
            i++;
        }
        setImage(image);
    }
}
