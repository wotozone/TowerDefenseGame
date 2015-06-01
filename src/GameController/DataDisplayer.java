/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameController;

import TowerController.TowerData;
import TowerController.TowerInfo;

/**
 *
 * @author Tae
 */
public class DataDisplayer {
    
    public static DataDisplayer dd = new DataDisplayer();
    
    public boolean selected=false;
    
    public int damage=0;
    
    public int attackSpeed=0;
    
    public int range=0;
    
    public int gold=0;
    
    public boolean sellTower=false;
    
    public TowerData sellingTower=null;
    
    public void setData(int towerID, TowerData tower){
        if(!selected)selected=true;
        setData(towerID);
        sellTower=true;
        sellingTower=tower;
    }
    
    public void setData(int towerID){
        if(!selected)selected=true;
        switch(towerID){
            case 1:
                writeData(2, 125, 50, 3, TowerInfo.info.getTowerPrice(towerID));//(damage,range.atkspeed,missilespeed,price)
                break;
            case 2:
                writeData(1, 75, 5, 2, TowerInfo.info.getTowerPrice(towerID));
                break;
            case 3:
                writeData(30, 500, 500, 10, TowerInfo.info.getTowerPrice(towerID));
                break;
            case 4:
                writeData(5, 200, 50, 1, TowerInfo.info.getTowerPrice(towerID));
                break;
            case 5:
                writeData(10, 100, 30, 4, TowerInfo.info.getTowerPrice(towerID));
                break;
            case 6:
                writeData(20, 300, 50, 5, TowerInfo.info.getTowerPrice(towerID));
                break;
            case 7:
                writeData(100, 125, 40, 4, TowerInfo.info.getTowerPrice(towerID));
                break;
            case 8:
                writeData(80, 300, 30, 6, TowerInfo.info.getTowerPrice(towerID));
                break;
        }
        if(sellTower){
            sellTower=false;
            sellingTower=null;
        }
    }
    
    public void clearData(){
        selected=false;
        damage=0;
        attackSpeed=0;
        range=0;
        gold=0;
        sellTower=false;
        sellingTower=null;
    }
    
    private void writeData(int damage, int range, int attackSpeed, int missileSpeed, int price){
        this.damage=damage;
        this.range=range;
        this.attackSpeed=attackSpeed;
        this.gold=price;
    }
    
}
