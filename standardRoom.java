import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Room here.
 */
public class standardRoom extends Room
{
    public standardRoom(int x, int y, int width, int height, int[] entrance)
    {
        super( x, y, width, height, entrance);
    }

    /**
     * Act - do whatever the Room wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }  

    /**
     * Add some(none) random Deco and monsters
     */
    @Override
    public void specificContent()
    {
        int numberOfMonsters = Greenfoot.getRandomNumber(getDungeon().difficulty / 2);

        for(int i = 0; i < numberOfMonsters; i++)
        {
            int monX = curX + tileWidth + Greenfoot.getRandomNumber(curWidth - 2) * tileWidth;
            int monY = curY + tileHeight + Greenfoot.getRandomNumber(curHeight - 2) * tileHeight;
            int monType = Greenfoot.getRandomNumber(2);
            if(monType == 0)
                add(new Monster1(), monX, monY, 0);
            else
                add(new Monster2(), monX, monY, 0);
        }
    }

}
