/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowerController;

import imageController.ImageManager;
import pathController.PathController;

/**
 *
 * @author Tae
 */
public class TowerInfo {
    
    public static TowerInfo info = new TowerInfo();
    private ImageManager im;
    
    private TowerData output;
    private boolean seperated=false;
    
    private int x;
    private int y;
    
    public TowerData tempImg=new TowerData();
    
    public TowerInfo(){
        initImageManager();
    }
    
    private void initImageManager(){
        try {
            im = new ImageManager();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public TowerData getTempTowerImg(int towerID){//DO NOT TOUCH THIS PART. IT IS NOT WORKING
        resetOutput(tempImg);
        getTowerImage(towerID,true);
        return tempImg;
    }
    
    public TowerData getTowerData(int towerID,PathController path){
        output=new TowerData();
        resetOutput(output);
        getTowerStat(towerID);
        output.setTowerID(towerID);
        getTowerImage(towerID,false);
        getTowerPosition(path,towerID);
        return output;
    }
    
    private void getTowerImage(int towerID, boolean temp){
        TowerData td=output;
        if(temp)td=tempImg;
        if(seperated){
            td.setTowerImage(im.getTowerImage(towerID, false), im.getTowerImage(towerID, true), im.getMissileImage(towerID), false);
        }else{
            td.setTowerImage(im.getTowerImage(towerID), im.getMissileImage(towerID));
        }
    }
    
    private void getTowerStat(int towerID){
        switch(towerID){
            case 1:
                output.initTowerData(2, 125, 50, 4, getTowerPrice(towerID));//(damage,range.atkspeed,missilespeed,price)
                x=0;
                y=0;
                seperated=false;
                break;
            case 2:
                output.initTowerData(1, 75, 5, 3, getTowerPrice(towerID));
                x=0;
                y=0;
                seperated=false;
                break;
            case 3:
                output.initTowerData(30, 500, 500, 10, getTowerPrice(towerID));
                x=0;
                y=0;
                break;
            case 4:
                output.initTowerData(5, 200, 50, 1, getTowerPrice(towerID));
                x=0;
                y=0;
                break;
            case 5:
                output.initTowerData(10, 100, 30, 4, getTowerPrice(towerID));
                x=0;
                y=0;
                break;
            case 6:
                output.initTowerData(20, 300, 50, 5, getTowerPrice(towerID));
                x=0;
                y=0;
                break;
            case 7:
                output.initTowerData(100, 125, 40, 4, getTowerPrice(towerID));
                x=0;
                y=0;
                break;
            case 8:
                output.initTowerData(80, 300, 30, 6, getTowerPrice(towerID));
                x=0;
                y=0;
                break;
        }
    }
    
    public int getTowerPrice(int towerID){
        switch(towerID){
            case 1:
                return 100;
            case 2:
                return 200;
            case 3:
                return 400;
            case 4:
                return 450;
            case 5:
                return 650;
            case 6:
                return 1000;
            case 7:
                return 1400;
            case 8:
                return 2000;
            default:
                return 10;
        }
    }
    
    private void getTowerPosition(PathController path,int towerID){
        switch(towerID){
            case 3:
                //if()
                output.setTowerPosition(path, x, y, x,y-400);
                break;
            default:
                output.setTowerPosition(path,x,y);
                break;
        }
    }
    
    private void resetOutput(TowerData td){
        td.clearData();
    }
}
