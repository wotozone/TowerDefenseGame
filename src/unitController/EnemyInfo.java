/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitController;

import imageController.ImageManager;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapInfo.MapManager;

/**
 *
 * @author minjikim
 */
public class EnemyInfo {
    
    public static EnemyInfo info = new EnemyInfo();
    private ImageManager im;
    
    public static final int initialAmount = 20;
    public static final int respondDelay = 500;
    public static final int levelDelay = 100;
    
    private EnemyData output;
    
    public EnemyInfo(){
        initImageManager();
    }
    
    private void initImageManager(){
        try {
            im = new ImageManager();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public EnemyData getEnemyData(int level){
        output=new EnemyData();
        resetOutput();
        getEnemyStat(level);
        getEnemyImage(level);
        getEnemyStartPosition();
        return output;
    }
    
    private void getEnemyStat(int level){
        switch(level){
            case 1:
                output.initEnemy(10, 0, 5, 30);//(HP,TYPE,GOLD,SPEED)
                break;
            case 2:
                output.initEnemy(30, 0, 10, 30);
                break;
            case 3:
                output.initEnemy(30, 0, 15, 50);
                break;
            case 4:
                output.initEnemy(70, 0, 20, 30);
                break;
            case 5:
                output.initEnemy(100, 0, 30, 30);
                break;
            case 6:
                output.initEnemy(200, 0, 40, 15);
                break;
            case 7:
                output.initEnemy(150, 0, 50, 40);
                break;
            case 8:
                output.initEnemy(200, 0, 60, 40);
                break;
            case 9:
                output.initEnemy(100, 0, 70, 80);
                break;
            case 10:
                output.initEnemy(300, 0, 80, 40);
                break;
            case 11:
                output.initEnemy(400, 0, 90, 40);
                break;
            case 12:
                output.initEnemy(600, 0, 100, 30);
                break;
            case 13:
                output.initEnemy(600, 0, 120, 40);
                break;
            case 14:
                output.initEnemy(1000, 0, 140, 20);
                break;
            case 15:
                output.initEnemy(1500, 0, 140, 40);
                break;
            case 16:
                output.initEnemy(1750, 0, 140, 50);
                break;
            case 17:
                output.initEnemy(2000, 0, 140, 60);
                break;
            case 18:
                output.initEnemy(2200, 0, 140, 90);
                break;
            case 19:
                output.initEnemy(2500, 0, 140, 70);
                break;
            case 20:
                output.initEnemy(3000, 0, 140, 60);
                break;
            case 21:
                output.initEnemy(5000, 0, 140, 70);
                break;
            case 22:
                output.initEnemy(7000, 0, 140, 70);
                break;
            case 23:
                output.initEnemy(10000, 0, 140, 60);
                break;
            case 24:
                output.initEnemy(15000, 0, 140, 70);
                break;
            case 25:
                output.initEnemy(20000, 0, 140, 70);
                break;
            case 26:
                output.initEnemy(22000, 0, 140, 100);
                break;
            case 27:
                output.initEnemy(30000, 0, 140, 80);
                break;
            case 28:
                output.initEnemy(40000, 0, 140, 70);
                break;
            case 29:
                output.initEnemy(50000, 0, 140, 70);
                break;
            case 30:
                output.initEnemy(100000, 0, 140, 60);
                break;
        }
    }
    
    public int getEnemyAmount(int level){
        int amount=initialAmount;
        switch(level){
            case 9:
                amount=10;
                break;
        }
        return amount;
    }
    
    private void getEnemyStartPosition(){
        output.setPosition(MapManager.map.startPath.getPositionX(),MapManager.map.startPath.getPositionY());
        output.setCurrentPath(MapManager.map.startPath);
    }
    
    private void getEnemyImage(int level){
        output.setUnitImage(im.getUnitImage(level));
    }
    
    private void resetOutput(){
        output.clearData();
    }

}
