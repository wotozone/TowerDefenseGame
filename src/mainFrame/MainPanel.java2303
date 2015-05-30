/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFrame;

import pathController.PathController;
import InputController.KeyHandler;
import imageController.ImageManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import mapInfo.*;
import unitController.EnemyController;
import unitController.EnemyData;
import unitController.EnemyMove;

/**
 *
 * @author Tae
 */
public class MainPanel extends JFrame implements Runnable{
 
    public ImageManager imageManager;
    public EnemyController ec;
    public FPSChecker fps;
    public EnemyMove em;
    //public Object map;
    
    //size of window
    public static final int WINDOW_WIDTH=1280;
    public static final int WINDOW_HEIGHT=960;
    
    //size of tile
    public static final int TILE_WIDTH=40;
    public static final int TILE_HEIGHT=40;
    public static final int BLOCKSIZE=40;
    
    //# of tiles
    public static final int TILE_MAX_X_NUM=32;
    public static final int TILE_MAX_Y_NUM=17;
    
    private BufferedImage bi = null;
    private EnemyData ed = null;

    private boolean left = false, right = false, down = false;
    
    private int moveDelay;
    private int dropDelay;
    
    public static int mapType=0;//set as Basic Map initially
    
    private boolean start = false, end = false;
    
    private Timer timer;
    
    public static final boolean TEST = true;//for testing
    
    
    public boolean enemytest=true;


    public MainPanel() {
  
        bi = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        //this.addKeyListener(this);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
        this.setTitle("121321");
        this.setResizable(false);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);  
        this.setLocationRelativeTo(null);
        this.addKeyListener(new KeyHandler());

        start=true;
        end=false;



        ec = new EnemyController();
        fps = new FPSChecker();
        em = new EnemyMove();
        initTile();
        try {
            imageManager = new ImageManager();
            MapManager.map.setMapType(0);//0 is default
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initTimer();
    }
    private void initTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(enemytest){
                    System.out.println("ssdfsdfasdfasdfasdfasfd");
                    ec.getNextLevelEnemy();
                    enemytest=false;
                }
                if(!ec.spawnQueue.isEmpty()){
                    System.out.println(ec.spawnQueue.size());
                    EnemyData.enemy.add(ec.spawnQueue.get(0));
                    ec.spawnQueue.remove(0);
                }
            }
        }, 0, 500);
    }
    
    private void initTile(){
        for(int i=0;i<TILE_MAX_X_NUM;i++){
            for(int k=0;k<TILE_MAX_Y_NUM;k++){
                PathController.path[i][k] = new PathController();
                PathController.path[i][k].setTilePosition(BLOCKSIZE*i, 10+BLOCKSIZE*(k+1));
            }
        }
    }
    
 public void run() {

    try {

        
        moveDelay = 0;
        dropDelay = 0;

        while(true) {

            Thread.sleep(1);//optimizing fps

            if(start) {
                if(moveDelay>=100){//move per 0.1sec
                    //keyControl();
                }else{
                    moveDelay+=1;
                }
            }
            draw();
        }  
        }catch(Exception e){
            e.printStackTrace(); 
    } 
}


    public void draw() {

        Graphics gs = bi.getGraphics();
        //gs.drawImage(imageManager.getBackgroundImage(), 0, 0, this);

        gs.setColor(Color.BLACK);
        gs.fillRect(0, 0, 1280, 960);
        gs.setColor(Color.WHITE);
        gs.fillRect(0, 50, 1280, 680);
        gs.setColor(Color.LIGHT_GRAY);
        gs.fillRect(0, 730, 480, 230);
        gs.setColor(Color.RED);
        gs.fillRect(480, 760, 100, 100);
        gs.setColor(Color.BLUE);
        gs.fillRect(480, 860, 100, 100);
        gs.setColor(Color.GREEN);
        gs.fillRect(580, 760, 100, 100);
        gs.setColor(Color.YELLOW);
        gs.fillRect(580, 860, 100, 100);
        gs.setColor(Color.RED);
        gs.fillRect(680, 760, 100, 100);
        gs.setColor(Color.BLUE);
        gs.fillRect(680, 860, 100, 100);
        gs.setColor(Color.GREEN);
        gs.fillRect(780, 760, 100, 100);
        gs.setColor(Color.YELLOW);
        gs.fillRect(780, 860, 100, 100);
        gs.setColor(Color.RED);
        gs.fillRect(880, 760, 100, 100);
        gs.setColor(Color.BLUE);
        gs.fillRect(880, 860, 100, 100);
        gs.setColor(Color.GREEN);
        gs.fillRect(980, 760, 100, 100);
        gs.setColor(Color.YELLOW);
        gs.fillRect(980, 860, 100, 100);
        gs.setColor(Color.RED);
        gs.fillRect(1080, 760, 100, 100);
        gs.setColor(Color.BLUE);
        gs.fillRect(1080, 860, 100, 100);
        gs.setColor(Color.GREEN);
        gs.fillRect(1180, 760, 100, 100);
        gs.setColor(Color.YELLOW);
        gs.fillRect(1180, 860, 100, 100);
        
        //Main gamePanel
        for(int i=0;i<TILE_MAX_X_NUM;i++){
            for(int k=0;k<TILE_MAX_Y_NUM;k++){
                gs.drawImage(PathController.path[i][k].getTileImage(),
                             PathController.path[i][k].getPositionX(),
                             PathController.path[i][k].getPositionY(), this);
                if(TEST){
                    gs.setColor(Color.cyan);
                    gs.drawRect(BLOCKSIZE*i,10+BLOCKSIZE*(1+k),BLOCKSIZE-1,BLOCKSIZE-1);
                }
            }
        }
        if(!EnemyData.enemy.isEmpty()){
            for(int i=EnemyData.enemy.size()-1;i>=0;i--){
                System.out.println(i);
                ed=EnemyData.enemy.get(i);
                ed.setPosition(ed.getPositionX(), ed.getPositionY());
                gs.drawImage(ed.getUnitImage(), ed.getPositionX(), ed.getPositionY(), this);
            }
        }
        
        //Set Pathway

        
        //When Game has ended
        if(end){
            //gs.setColor(Color.WHITE);
            //gs.drawString("GAME OVER", 25+(35*4), 75+(35*10));//Show GAME OVER
            //gs.drawImage(imageManager.getGameOverImage(), 25, 425, this);
        }
        
        fps.checkFrame();
        fps.setFrame();
        gs.drawString("FPS: "+fps.getFramePerSecond(), 10, 40);//Show Frame Per Second
        
        Graphics ge = this.getGraphics();

        ge.drawImage(bi, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }
/*
    public void keyControl() {
        if(left){
        }else if(right){
        }else if(down){
        }
    }

    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case KeyEvent.VK_LEFT:  
                left = true;       
                break;
            case KeyEvent.VK_RIGHT: 
                right = true;      
                break;
            case KeyEvent.VK_UP:   
                break;
            case KeyEvent.VK_DOWN:   
                down = true;      
                break;
            case KeyEvent.VK_SPACE:
            } 
   }

    public void keyReleased(KeyEvent ke){
    switch(ke.getKeyCode()) 
        {
            case KeyEvent.VK_LEFT: 
                left = false;  
                break;
            case KeyEvent.VK_RIGHT: 
                right = false; 
                break;
            case KeyEvent.VK_DOWN:
                down = false;  
                break;
        } 
    } 

    public void keyTyped(KeyEvent ke) {

    }
    */
}
