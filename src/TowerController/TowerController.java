/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowerController;

import java.util.ArrayList;
import pathController.PathController;

/**
 *
 * @author Tae
 */
public class TowerController {
    
    public static TowerController tc = new TowerController();
    
    private TowerData td;
    
    
    public TowerController(){
        
    }
    
    public void createTower(PathController path, int towerType){
        td=TowerInfo.info.getTowerData(towerType, path);
        path.setTileType(1);
        TowerData.tower.add(td);
    }
    
}
