/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapInfo;

import pathController.PathController;

/**
 *
 * @author minjikim
 */
public class MapManager{
    
    public static MapManager map = new MapManager();
    
    public BasicMap basicMap;
    
    public PathController startPath=null;
    public PathController endPath=null;
    
    public MapManager(){
    }
    
    public void setMapType(int type){
        try{
            switch(type){
                default:
                    basicMap = new BasicMap();
                    startPath=basicMap.getStartPoint();
                    endPath=basicMap.getEndPoint();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
}
