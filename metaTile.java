import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class metaTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class metaTile extends hackedActor
{
    Class[] tiles; //tiles belonging to the metaTile
    //subclasses define the variable

    public metaTile(){
        //add all objects in tiles to the world
    }

    void add(Class myclass, int x, int y){
        //TODO: Adding objects
        //pars probably fixed
    }

    void remove(){
        //TODO: removing objects
        //pars will change
    }

    void move(){
        //call each object's move method
        //param will change
    }

    void subSpecific()
    {
        //for subclasses to overwrite
    }

    /**
     * Act - do whatever the metaTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // call to a function which is used by subclasses to do stuff they want to do (without overwriting this act() function)
        subSpecific();
    }    
}
