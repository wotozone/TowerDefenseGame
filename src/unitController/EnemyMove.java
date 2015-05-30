/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitController;

import GameController.PlayerData;
import java.util.Timer;
import java.util.TimerTask;
import mainFrame.MainPanel;
import pathController.PathController;

/**
 *
 * @author Tae
 */
public class EnemyMove {
    
    private Timer timer;
    private EnemyData ed;
    
    private int x;
    private int y;
    
    private int speed;
    
    private int dir;
    
    private PathController path;
    
    public EnemyMove(){
        initTimer();
    }
    
    private void initTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(EnemyData.enemy.size()!=0){
                    for(int i=0;i<EnemyData.enemy.size();i++){
                        ed=EnemyData.enemy.get(i);
                        speed=ed.getUnitSpeed();
                        if(ed.getMoveDelay()>(1000/speed)){
                            ed.setMoveDelay(true);
                            path=ed.getCurrentPath();
                            dir=path.getPathDir();
                            setNewPosition();
                            if(
                                    ((dir==2||dir==3)&&(x>(40+path.getPositionX())||y>(40+path.getPositionY())))||
                                    ((dir==1||dir==4)&&(x<(path.getPositionX()-40)||y<(path.getPositionY()-40)))){
                                ed.setCurrentPath(getNextPathTile());
                                //When enemy reaches endpoint
                                if(ed.getCurrentPath()==PlayerData.player.castle){
                                    EnemyData.enemy.remove(i);
                                    PlayerData.player.loseLifePoint();
                                }
                            }
                        }else{
                            ed.setMoveDelay(false);
                        }
                    }
                }
            }
        }, 0, 1);
    }
    
    private PathController getNextPathTile(){
        int i=path.getPositionX()/MainPanel.BLOCKSIZE;
        int j=((path.getPositionY()-10)/MainPanel.BLOCKSIZE)-1;
        switch(dir){
            case 1://Up
                return PathController.path[i][j-1];
            case 2://Right
                return PathController.path[i+1][j];
            case 3://Down
                return PathController.path[i][j+1];
            case 4://LEFT
                return PathController.path[i-1][j];
            default://LEFT
                return PathController.path[i-1][j];
        }
    }
    
    private void setNewPosition(){
        x=0;
        y=0;
        x=ed.getPositionX();
        y=ed.getPositionY();
        switch(dir){
            case 1://Up
                y--;
                break;
            case 2://Right
                x++;
                break;
            case 3://Down
                y++;
                break;
            case 4://LEFT
                x--;
                break;
        }
        ed.setPosition(x, y);
    }
    
}
