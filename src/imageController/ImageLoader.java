/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageController;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Tae
 */
public class ImageLoader{
    
    //FRAMES&BACKGROUNDS
    //public final BufferedImage BACKGROUND;
    //public final BufferedImage OUTSIDE_FRAME;
    
    //INTERFACES
    //public final BufferedImage GAME_OVER;
    
    //UNITS
    public final BufferedImage ENEMY_DUMMY;
    
    //TOWERS
    public final BufferedImage TOWER_DUMMY_PART1;
    public final BufferedImage TOWER_DUMMY_PART2;
    public final BufferedImage TOWER1_HEAD;
    public final BufferedImage TOWER_ARCHER_HEAD;
    public final BufferedImage TOWER_FIRE_HEAD;
    public final BufferedImage TOWER_GEN1_HEAD;
    public final BufferedImage TOWER_GEN2_HEAD;
    public final BufferedImage TOWER_GEN3_HEAD;
    public final BufferedImage TOWER_ICE_HEAD;
    public final BufferedImage TOWER_MORTAR_HEAD;
    public final BufferedImage TOWER_POISON_HEAD;
    
    //MISSILE
    public final BufferedImage MISSILE_DUMMY;
    
    //PATH
    public final BufferedImage PATH_LU;
    public final BufferedImage PATH_LD;
    public final BufferedImage PATH_RU;
    public final BufferedImage PATH_RD;
    public final BufferedImage PATH_HZ1;
    public final BufferedImage PATH_HZ2;
    public final BufferedImage PATH_HZ3;
    public final BufferedImage PATH_VT1;
    public final BufferedImage PATH_VT2;
    public final BufferedImage PATH_VT3;
    
    //DOODAD&GRASS
    public final BufferedImage GRASS1;
    public final BufferedImage DOODAD_STONE1_PART1;
    public final BufferedImage DOODAD_STONE1_PART2;
    
    //OBJECT
    public final BufferedImage CASTLE;
    public final BufferedImage GAMEOVER;
    
    //BUTTON ICONS
    public final BufferedImage TOWER1_ICON;
    
    
    public ImageLoader() throws Exception{
        //FRAMES
        //BACKGROUND = ImageIO.read(new FileInputStream("Images/BACKGROUND.jpg"));
        //OUTSIDE_FRAME = ImageIO.read(new FileInputStream("Images/OUTSIDEFRAME.jpg"));
        
        //PATH
        PATH_LU= ImageIO.read(new FileInputStream("Resources/Path_Left_Up.png"));
        PATH_LD= ImageIO.read(new FileInputStream("Resources/Path_Left_Down.png"));
        PATH_RU= ImageIO.read(new FileInputStream("Resources/Path_Right_Up.png"));
        PATH_RD= ImageIO.read(new FileInputStream("Resources/Path_Right_Down.png"));
        PATH_HZ1= ImageIO.read(new FileInputStream("Resources/Path_Horizontal1.png"));
        PATH_HZ2= ImageIO.read(new FileInputStream("Resources/Path_Horizontal2.png"));
        PATH_HZ3= ImageIO.read(new FileInputStream("Resources/Path_Horizontal3.png"));
        PATH_VT1= ImageIO.read(new FileInputStream("Resources/Path_Vertical1.png"));
        PATH_VT2= ImageIO.read(new FileInputStream("Resources/Path_Vertical2.png"));
        PATH_VT3= ImageIO.read(new FileInputStream("Resources/Path_Vertical3.png"));
        
        //DOODAD & GRASS
        GRASS1= ImageIO.read(new FileInputStream("Resources/Grass1.png"));
        DOODAD_STONE1_PART1= ImageIO.read(new FileInputStream("Resources/Doodad_Stone1_Part1.png"));
        DOODAD_STONE1_PART2= ImageIO.read(new FileInputStream("Resources/Doodad_Stone1_Part2.png"));
        
        //UNITS
        ENEMY_DUMMY= ImageIO.read(new FileInputStream("Resources/DummyEnemy.png"));
        
        //INTERFACES
        
        //TOWERS
        TOWER_DUMMY_PART1= ImageIO.read(new FileInputStream("Resources/DummyTower1.png"));
        TOWER_DUMMY_PART2= ImageIO.read(new FileInputStream("Resources/DummyTower2.png"));
        
        
        
        TOWER1_HEAD= ImageIO.read(new FileInputStream("Resources/DummyTower3.png"));
        TOWER_ARCHER_HEAD= ImageIO.read(new FileInputStream("Resources/tower-archer.png"));
        TOWER_FIRE_HEAD= ImageIO.read(new FileInputStream("Resources/tower-fire.png"));
        TOWER_GEN1_HEAD= ImageIO.read(new FileInputStream("Resources/tower-gen1.png"));
        TOWER_GEN2_HEAD= ImageIO.read(new FileInputStream("Resources/tower-gen2.png"));
        TOWER_GEN3_HEAD= ImageIO.read(new FileInputStream("Resources/tower-gen3.png"));
        TOWER_ICE_HEAD= ImageIO.read(new FileInputStream("Resources/tower-ice.png"));
        TOWER_MORTAR_HEAD= ImageIO.read(new FileInputStream("Resources/tower-mortar.png"));
        TOWER_POISON_HEAD= ImageIO.read(new FileInputStream("Resources/tower-poison.png"));
        
        //MISSILES
        MISSILE_DUMMY= ImageIO.read(new FileInputStream("Resources/DummyMissile.png"));
        
        
        //OBJECTS
        CASTLE= ImageIO.read(new FileInputStream("Resources/castle.png"));
        GAMEOVER= ImageIO.read(new FileInputStream("Resources/gameover.png"));
        
        //BUTTON FRAMES
        TOWER1_ICON= ImageIO.read(new FileInputStream("Resources/tower1_icon2.png"));
        
        /*
        */
    }
    
}
