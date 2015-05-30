/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathController;

import java.awt.image.BufferedImage;
import mainFrame.MainPanel;

/**
 *
 * @author minjikim
 */
public class PathController {
    
    //Path Image
    private BufferedImage pathImage = null;
    
    
    private int pathStatus=0;
    /*
        PathStatus:
        0 - empty
        1 - tower
        2 - doodad
        3
        ...
        10 - pathway
        11~14 - pathway where enemy moves
    */
    
    private int pathDir=0;
    /*
        Direction(clockwise)
        1 - Up
        2 - Right
        3 - Down
        4 - Left
    */
    private int positionX=0;
    private int positionY=0;
    
    public static PathController[][] path = new PathController[MainPanel.TILE_MAX_X_NUM][MainPanel.TILE_MAX_Y_NUM];
    
    public void setTileType(int type){
        switch(type){
            case 0:
                setTileEmpty();
                break;
            case 1:
                pathStatus=1;
                break;
            case 2:
                pathStatus=2;
                break;
            case 11:
                setTilePathway(1);
                break;
            case 12:
                setTilePathway(2);
                break;
            case 13:
                setTilePathway(3);
                break;
            case 14:
                setTilePathway(4);
                break;
        }
    }
    
    public void setTilePosition(int x, int y){
        positionX=x;
        positionY=y;
    }
    
    public void setTileEmpty(){
        pathStatus=0;
    }
    
    public void setTilePathway(int dir){
        pathStatus=10;
        pathDir=dir;
    }
    
    public void setTileImage(BufferedImage bi){
        pathImage=bi;
    }
    
    
    
    
    public BufferedImage getTileImage(){
        return pathImage;
    }
    
    public int getTileType(){
        return pathStatus;
    }
    
    public int getPositionX(){
        return positionX;
    }
    
    public int getPositionY(){
        return positionY;
    }
    
    public int getPathDir(){
        return pathDir;
    }
    
}
