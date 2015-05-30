/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapInfo;

import imageController.ImageManager;
import java.awt.image.BufferedImage;
import mainFrame.MainPanel;
import pathController.PathController;

/**
 *
 * @author minjikim
 */
abstract class MapInfo {
    
    protected ImageManager imageManager;
    
    public static PathController startPoint = null;
    public static PathController endPoint = null;
    
    protected int startX = 0;
    protected int startY = 0;
    protected int endX = 0;
    protected int endY = 0;
    
    public MapInfo() throws Exception{
        imageManager = new ImageManager();
        initInfo();
    }
    
    protected void initInfo(){
        startPoint=PathController.path[startX][startY];
        endPoint=PathController.path[endX][endY];
        setGrass();
        pathInfo();
    }
    
    public PathController getStartPoint(){
        return startPoint;
    }
    
    public PathController getEndPoint(){
        return endPoint;
    }
    
    protected void pathInfo(){//Edit this to set the path
    }
    
    protected void setGrass(){
        for(int i=0;i<MainPanel.TILE_MAX_X_NUM;i++){
            for(int k=0;k<MainPanel.TILE_MAX_Y_NUM;k++){
                setTileTexture(i, k, 0, getPathImg("GR1"));
            }
        }
    }
    
    protected BufferedImage getPathImg(String str){
        return imageManager.getPathImage(str);
    }
    
    protected void setPath(int x, int y, BufferedImage bi, int pathDir){
        PathController.path[x][y].setTilePathway(pathDir);
        PathController.path[x][y].setTileImage(bi);
    }
    
    protected void setTileTexture(int x,int y, int type, BufferedImage bi){
        PathController.path[x][y].setTileType(type);
        PathController.path[x][y].setTileImage(bi);
    }
    
}