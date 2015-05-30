/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapInfo;

import GameController.PlayerData;
import imageController.ImageManager;
import java.awt.image.BufferedImage;
import mainFrame.MainPanel;
import pathController.PathController;

/**
 *
 * @author Tae
 */
public class BasicMap extends MapInfo{
    
    protected int startX = 1;
    protected int startY = 0;
    protected int endX = 25;
    protected int endY = 3;
    
    public BasicMap() throws Exception{
        super.imageManager = new ImageManager();
        super.startX=this.startX;
        super.startY=this.startY;
        super.endX=this.endX;
        super.endY=this.endY;
        super.initInfo();
        PlayerData.player.castle=super.getEndPoint();
    }
    
    @Override
    protected void pathInfo(){
        //Path
        setPath(1,0,getPathImg("VT1"),3);
        setPath(1,1,getPathImg("VT2"),3);
        setPath(1,2,getPathImg("VT1"),3);
        setPath(1,3,getPathImg("VT3"),3);
        setPath(1,4,getPathImg("VT2"),3);
        setPath(1,5,getPathImg("VT1"),3);
        setPath(1,6,getPathImg("VT3"),3);
        
        setPath(1,7,getPathImg("LD"),2);
        setPath(2,7,getPathImg("HZ2"),2);
        setPath(3,7,getPathImg("HZ2"),2);
        setPath(4,7,getPathImg("HZ1"),2);
        setPath(5,7,getPathImg("HZ3"),2);
        setPath(6,7,getPathImg("HZ1"),2);
        setPath(7,7,getPathImg("HZ1"),2);
        setPath(8,7,getPathImg("HZ1"),2);
        setPath(9,7,getPathImg("HZ2"),2);
        
        setPath(10,7,getPathImg("RD"),1);
        setPath(10,6,getPathImg("VT2"),1);
        
        setPath(10,5,getPathImg("RU"),4);
        setPath(9,5,getPathImg("HZ1"),4);
        setPath(8,5,getPathImg("HZ2"),4);
        setPath(7,5,getPathImg("HZ3"),4);
        setPath(6,5,getPathImg("HZ2"),4);
        setPath(5,5,getPathImg("HZ1"),4);
        
        setPath(4,5,getPathImg("LD"),1);
        setPath(4,4,getPathImg("VT1"),1);
        setPath(4,3,getPathImg("VT2"),1);
        
        setPath(4,2,getPathImg("LU"),2);
        setPath(5,2,getPathImg("HZ1"),2);
        setPath(6,2,getPathImg("HZ2"),2);
        setPath(7,2,getPathImg("HZ2"),2);
        setPath(8,2,getPathImg("HZ3"),2);
        setPath(9,2,getPathImg("HZ3"),2);
        setPath(10,2,getPathImg("HZ1"),2);
        setPath(11,2,getPathImg("HZ3"),2);
        setPath(12,2,getPathImg("HZ3"),2);
        setPath(13,2,getPathImg("HZ2"),2);
        setPath(14,2,getPathImg("HZ1"),2);
        setPath(15,2,getPathImg("HZ2"),2);
        setPath(16,2,getPathImg("HZ1"),2);
        setPath(17,2,getPathImg("HZ3"),2);
        
        setPath(18,2,getPathImg("RU"),3);
        setPath(18,3,getPathImg("VT1"),3);
        setPath(18,4,getPathImg("VT1"),3);
        setPath(18,5,getPathImg("VT2"),3);
        setPath(18,6,getPathImg("VT2"),3);
        setPath(18,7,getPathImg("VT1"),3);
        
        setPath(18,8,getPathImg("LD"),2);
        setPath(19,8,getPathImg("HZ3"),2);
        setPath(20,8,getPathImg("HZ3"),2);
        setPath(21,8,getPathImg("HZ1"),2);
        setPath(22,8,getPathImg("HZ3"),2);
        
        setPath(23,8,getPathImg("RD"),1);
        setPath(23,7,getPathImg("VT1"),1);
        setPath(23,6,getPathImg("VT1"),1);
        
        setPath(23,5,getPathImg("RU"),4);
        setPath(22,5,getPathImg("HZ1"),4);
        
        setPath(21,5,getPathImg("LD"),1);
        setPath(21,4,getPathImg("VT1"),1);
        
        setPath(21,3,getPathImg("LU"),2);
        setPath(22,3,getPathImg("HZ1"),2);
        setPath(23,3,getPathImg("HZ2"),2);
        setPath(24,3,getPathImg("HZ3"),2);
        setPath(25,3,getPathImg("HZ2"),2);
        
        
        //Doodad
        setTileTexture(25, 0, 2, getPathImg("DD1_P1"));
        setTileTexture(25, 1, 2, getPathImg("DD1_P2"));
        setTileTexture(14, 5, 2, getPathImg("DD1_P1"));
        setTileTexture(14, 6, 2, getPathImg("DD1_P2"));
    }
    
}
