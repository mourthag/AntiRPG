import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.io.*;

/**
 * The World is called Dungeon.
 * The Dungeon includes several rooms and roomtypes which will be spawned randomly at the beginning of a Level. 
 * The number of rooms and monsters which are spawned is relative to the difficulty level which is raised after each boss.
 */
public class Dungeon extends World
{
    public int difficulty = 10;
    public int Score = 0;

    public static final int frameWidth = Integer.valueOf(JOptionPane.showInputDialog("Width","1131"));      //tmp no control for invalid values
    public static final int frameHeight = Integer.valueOf(JOptionPane.showInputDialog("Height","725"));     //tmp no control for invalid values
    public static final int tileCountX = 51; // frameWidth % tileCountX should be 0, and frameWidth/tileCountX=frameHeight/tileCountY
    public static final int tileCountY = 34; // frameHight % tileCountY should be 0
    public static final int tileWidth = frameWidth/tileCountX;
    public static final int tileHeight = frameHeight/tileCountY;

    public int roomDistance = 7 * tileHeight;       //the distance between two rooms
    public int blockedDoorRange = 5 * tileWidth;    //the distance  that doors have to have to each other

    public boolean bossRoomSpawned = false;
    public int bossRoomChance = 1;                 //Chance in percent of spawning a bossRoom

    /**
     * Constructor for objects of class Dungeon.
     * 
     */
    public Dungeon()
    {   
        super(frameWidth, frameHeight, 1);

        //color the background black
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();

        //player on top
        setPaintOrder(Player.class, Tile.class);

        //tmp playground
        //addObject(new Wall(), 45,45);
        //addObject(new Tile(), 70,45);
        //addObject(new metaTile(), 150, 150);

        //startUpRoom( 100,100,10,10);

        
        //errorHandling
        try{
            readTXT();  //Read The File
        }
        catch(IOException e)
        {
        }
        
        addObject(new startScreen(), Math.round(frameWidth / 2), Math.round(frameHeight / 2));
    }

    /*
     * spawns a rectangle room at postition x,y 
     * its size is declared in Tiles
     */
    public void startUpRoom(int x, int y, int width, int height)
    {

        addObject(new starterRoom( x, y, width, height), x, y);

    }

    /**
     * Reads the World.txt file and creates the Tiles mentioned in this txt
     */
    public void readTXT() throws IOException
    {
        FileReader fr = new FileReader("Welt.txt");     
        BufferedReader br = new BufferedReader(fr);    

        String zeile = br.readLine(); //Zeile auslesen

        int j = 0;
        while( zeile != null )
        {
            int i = 0;
            char[] cZeile = zeile.toCharArray();
            for(char curChar:cZeile)
            {
                //Zeichen verarbeiten
                if(curChar == '+')
                {
                    addObject(new Wall(), i*tileWidth,j*tileHeight); 
                }               
                else if(curChar == '-')
                {
                    addObject(new Floor(),i*tileWidth,j*tileHeight); 
                }
                else if(curChar == '#')
                {
                    addObject(new Door(),i*tileWidth,j*tileHeight); 
                }
                else if(curChar == 'x')
                {
                    addObject(new Floor(),i*tileWidth,j*tileHeight);
                    addObject(new Monster1(),i*tileWidth,j*tileHeight); 
                }
                else if(curChar == 'c')
                {
                    addObject(new Floor(),i*tileWidth,j*tileHeight);
                    addObject(new Monster2(),i*tileWidth,j*tileHeight); 
                }
                else if(curChar == 'p')
                {
                    addObject(new Floor(),i*tileWidth,j*tileHeight);
                    addObject(new Player(),i*tileWidth,j*tileHeight); 
                }
                else if(curChar == 'd')
                {
                    addObject(new Floor(),i*tileWidth,j*tileHeight);
                    addObject(new Deco1(),i*tileWidth,j*tileHeight); 
                }	
                else if(curChar == 'd')
                {
                    addObject(new Floor(),i*tileWidth,j*tileHeight);
                    addObject(new Deco2(),i*tileWidth,j*tileHeight); 
                }	
                else if(curChar == 'd')
                {
                    addObject(new Floor(),i*tileWidth,j*tileHeight);
                    addObject(new Deco3(),i*tileWidth,j*tileHeight); 
                }	
                else if(curChar == 'b')
                {
                    addObject(new Floor(),i*tileWidth,j*tileHeight);
                    addObject(new Boss(),i*tileWidth,j*tileHeight); 
                }	
                i++;
            }
            j++;
            zeile = br.readLine();
        }

        br.close();
    }
}
