/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathController;

import mainFrame.MainPanel;


/**
 *
 * @author Tae
 */
public class PathFinder {
    
    public static PathFinder pf = new PathFinder();
    
    public PathFinder(){
    }
    
    
    public PathController findPath(int x, int y){
        if(x<0  ||  x>1280  ||  y<10  || y>690)return null;
        int tileX = (x/MainPanel.TILE_WIDTH);
        int tileY = ((y-50)/(MainPanel.TILE_HEIGHT));
        System.out.println("Vertial Axis: "+tileY+"//"+y);
        return PathController.path[tileX][tileY];
    }
    
}
