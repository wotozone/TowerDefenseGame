/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputController;

import GameController.PlayerData;
import pathController.PathController;
import pathController.PathFinder;
import TowerController.*;

/**
 *
 * @author Tae
 */
public class MouseController {
    
    private boolean isBuilding = false;
    
    private int towerID;
    
    private int x;
    private int y;
    
    public static MouseController mc = new MouseController();
    
    public void MouseClicked(int x, int y){
        this.x=x;
        this.y=y;
        switch(getClickedArea()){
            case 0:
                break;
            case 1:
                if(isBuilding)buildTower();
                break;
            case 2:
                selectTower();
                break;
        }
    }
    
    private void selectTower(){
        towerID = getTowerType();
        if(towerID!=0)isBuilding=true;
    }
    
    private void buildTower(){
        PathController path = PathFinder.pf.findPath(x, y);
        if(path.getTileType()==0){
            if(PlayerData.player.useGold(TowerInfo.info.getTowerPrice(towerID))){
                TowerController.tc.createTower(path, towerID);
                System.out.println("TOWER ID: "+towerID);
            }
        }else{
            System.out.println("Unable to build in this Path: "+path.getTileType());
        }
        reset();
    }
    
    private void reset(){
        isBuilding=false;
        towerID=0;
    }
    
    private int getTowerType(){
        if(x<515){
            if(y<545){
                return 1;
            }else{
                return 2;
            }
        }else if(x<590){
            if(y<545){
                return 3;
            }else{
                //return 4;
            }
        }else if(x<665){
            if(y<545){
                //return 5;
            }else{
                //return 6;
            }
        }else if(x<740){
            if(y<545){
                //return 7;
            }else{
                //return 8;
            }
        }else if(x<815){
            if(y<545){
                //return 9;
            }else{
                //return 10;
            }
        }else if(x<890){
            if(y<545){
                //return 11;
            }else{
                //return 12;
            }
        }else if(x<965){
            if(y<545){
                //return 13;
            }else{
                //return 14;
            }
        }else if(x<1040){
            if(y<545){
                //return 15;
            }else{
                //return 16;
            }
        }
        return 0;
    }
    
    private int getClickedArea(){
        if(y<50){//noti-bar do nothing
            System.out.println("System: 0");
            return 0;
        }else if(y<450){//play-area
            System.out.println("System: 1");
            return 1;
        }else if(y>470){//info
            if(x<440){//info area do nothing
                System.out.println("System: 0");
                return 0;
            }else{//build tower
                System.out.println("System: 2");
                return 2;
            }
        }
        //gs.fillRect(0, 50, 1280, 680);//play area
        //gs.fillRect(0, 730, 480, 230);//info area
        return 0;
    }
    
}
