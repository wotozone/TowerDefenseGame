/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageController;

import java.awt.image.BufferedImage;
import mainFrame.MainPanel;

/**
 *
 * @author Tae
 */
public class ImageManager{
    
    public ImageLoader imageLoader;
    
    public ImageManager() throws Exception{
        imageLoader=new ImageLoader();
    }
    
    public BufferedImage getPathImage(String path){
        if(path=="LU"){
            return imageLoader.PATH_LU;
        }else if(path=="LD"){
            return imageLoader.PATH_LD;
        }else if(path=="RU"){
            return imageLoader.PATH_RU;
        }else if(path=="RD"){
            return imageLoader.PATH_RD;
        }else if(path=="HZ1"){
            return imageLoader.PATH_HZ1;
        }else if(path=="HZ2"){
            return imageLoader.PATH_HZ2;
        }else if(path=="HZ3"){
            return imageLoader.PATH_HZ3;
        }else if(path=="VT1"){
            return imageLoader.PATH_VT1;
        }else if(path=="VT2"){
            return imageLoader.PATH_VT2;
        }else if(path=="VT3"){
            return imageLoader.PATH_VT3;
        }else if(path=="GR1"){
            return imageLoader.GRASS1;
        
        
        //DOODAD
        }else if(path=="DD1_P1"){
            return imageLoader.DOODAD_STONE1_PART1;
        }else if(path=="DD1_P2"){
            return imageLoader.DOODAD_STONE1_PART2;
        }
        System.out.println("No matched image");
        return null;
    }
    
    public BufferedImage getUnitImage(String unit){
        if(unit=="ENEMY_DUMMY"){
            return imageLoader.ENEMY_DUMMY;
        }
        System.out.println("No matched image");
        return null;
    }
    
    public BufferedImage getUnitImage(int level){
        if(level==0){
            return imageLoader.ENEMY_DUMMY;
        }
        if(MainPanel.TEST)return imageLoader.ENEMY_DUMMY;
        return null;
    }
    
    public BufferedImage getCastleImage(){
        return imageLoader.CASTLE;
    }
    
    public BufferedImage getGameoverImage(){
        return imageLoader.GAMEOVER;
    }
    
    public BufferedImage getTowerIconImage(int towerID){
        if(towerID==1){
            return imageLoader.TOWER1_ICON;
        }
        if(MainPanel.TEST)return imageLoader.TOWER1_ICON;
        System.out.println("No matched image");
        return null;
    }
    
    public BufferedImage getTowerImage(String tower){
        if(tower=="TOWER_DUMMY_PART1"){
            return imageLoader.TOWER_DUMMY_PART1;
        }else if(tower=="TOWER_DUMMY_PART2"){
            return imageLoader.TOWER_DUMMY_PART2;
        }
        System.out.println("No matched image");
        return null;
    }
    
    public BufferedImage getTowerImage(int towerID, boolean head){//part 1 is usually body part
        if(head){
            if(towerID==0){
                return imageLoader.TOWER_DUMMY_PART1;
            }
            if(MainPanel.TEST)return imageLoader.TOWER_DUMMY_PART1;
            System.out.println("No matched image");
        }else{
            if(towerID==0){
                return imageLoader.TOWER_DUMMY_PART2;
            }
            if(MainPanel.TEST)return imageLoader.TOWER_DUMMY_PART2;
            System.out.println("No matched image");
        }
        return null;
    }
    
    public BufferedImage getTowerImage(int towerID){//unified
        switch(towerID){
            case 1: return imageLoader.TOWER_ARCHER_HEAD;
            case 2: return imageLoader.TOWER_FIRE_HEAD;
            case 3: return imageLoader.TOWER_GEN1_HEAD;
            case 4: return imageLoader.TOWER_GEN2_HEAD;
            case 5: return imageLoader.TOWER_GEN3_HEAD;
            case 6: return imageLoader.TOWER_ICE_HEAD;
            case 7: return imageLoader.TOWER_MORTAR_HEAD;
            case 8: return imageLoader.TOWER_POISON_HEAD;
            default: 
                if(MainPanel.TEST){
                    return imageLoader.TOWER1_HEAD;
                }else{
                    System.out.println("No matched image");
                    return null;
                }
        }
    }
    
    public BufferedImage getMissileImage(String missile){
        if(missile=="MISSILE_DUMMY"){
            return imageLoader.MISSILE_DUMMY;
        }
        if(MainPanel.TEST)return imageLoader.MISSILE_DUMMY;
        System.out.println("No matched image");
        return null;
    }
    
    public BufferedImage getMissileImage(int type){
        if(type==0){
            return imageLoader.MISSILE_DUMMY;
        }
        if(MainPanel.TEST)return imageLoader.MISSILE_DUMMY;
        return null;
    }
    /*
    public BufferedImage getBlockColorImage(int value){
        switch(value){
            case(0):return imageLoader.SKY_BLOCK;
            case(1):return imageLoader.BLUE_BLOCK;
            case(2):return imageLoader.ORANGE_BLOCK;
            case(3):return imageLoader.GREEN_BLOCK;
            case(4):return imageLoader.RED_BLOCK;
            case(5):return imageLoader.YELLOW_BLOCK;
            case(6):return imageLoader.PURPLE_BLOCK;
            case(7):return imageLoader.EMPTY_BLOCK;
        }
        return imageLoader.EMPTY_BLOCK;
    }
    
    public BufferedImage getBackgroundImage(){
        return imageLoader.BACKGROUND;
    }
    
    public BufferedImage getOutsideFrame(){
        return imageLoader.OUTSIDE_FRAME;
    }
    
    public BufferedImage getGameOverImage(){
        return imageLoader.GAME_OVER;
    }
    */
    
}
