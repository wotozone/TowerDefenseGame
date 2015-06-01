/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFrame;

import GameController.DataDisplayer;
import GameController.PlayerData;
import pathController.PathController;
import InputController.KeyHandler;
import InputController.MouseHandler;
import TowerController.MissileController;
import TowerController.MissileData;
import TowerController.TowerData;
import imageController.ImageManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import main.MainFrame;
import mapInfo.*;
import panels.MainMenuPage;
import unitController.EnemyController;
import unitController.EnemyData;
import unitController.EnemyInfo;
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
    public MissileController mc;
    //public Object map;
    
    //size of window
    public static final int WINDOW_WIDTH=1040;
    public static final int WINDOW_HEIGHT=620;
    
    //size of tile
    public static final int TILE_WIDTH=40;
    public static final int TILE_HEIGHT=40;
    public static final int BLOCKSIZE=40;
    
    //# of tiles
    public static final int TILE_MAX_X_NUM=26;
    public static final int TILE_MAX_Y_NUM=10;
    
    private BufferedImage bi = null;
    private EnemyData ed = null;
    private TowerData td = null;
    private MissileData md = null;
    
    private EnemyData dummyTarget;

    private boolean left = false, right = false, down = false;
    
    private int levelDelay=EnemyInfo.levelDelay+1;
    
    public static int mapType=0;//set as Basic Map initially
    
    private boolean start = false, end = false;
    
    private Timer timer;
    
    public static boolean TEST = true;//for testing
    
    public boolean enemytest=true;

    public static boolean keepGameAlive = true;

    public MainPanel() {
  
        bi = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        //this.addKeyListener(this);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
        this.setTitle("TOWER DEFENSE");
        this.setResizable(false);  
        this.setDefaultCloseOperation(resetMainMenu());
        this.setVisible(true);  
        this.setLocationRelativeTo(null);
        this.addKeyListener(new KeyHandler());
        this.addMouseListener(new MouseHandler());

        start=true;
        end=false;
        
        



        ec = new EnemyController();
        fps = new FPSChecker();
        em = new EnemyMove();
        mc= new MissileController();
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
                if(start){
                    if(levelDelay>EnemyInfo.levelDelay){
                        System.out.println("Next Level");
                        ec.getNextLevelEnemy();
                        levelDelay=0;
                        //enemytest=false;
                    }else{
                        levelDelay++;
                        System.out.println(levelDelay);
                    }
                    if(!ec.spawnQueue.isEmpty()){
                        System.out.println(ec.spawnQueue.size());
                        EnemyData.enemy.add(ec.spawnQueue.get(0));
                        ec.spawnQueue.remove(0);
                    }
                    if(EnemyData.enemy.isEmpty()){
                        if(10<(((EnemyInfo.levelDelay-levelDelay)*EnemyInfo.respondDelay)/1000)){
                            levelDelay=EnemyInfo.levelDelay-(10000/EnemyInfo.respondDelay);
                        }
                    }
                }else{
                    timer.cancel();
                }
            }
        }, 0, EnemyInfo.respondDelay);
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

        

        while(true) {

            Thread.sleep(1);//optimizing fps

            
            if(start) {
                for(int i=0;i<TowerData.tower.size();i++){
                    td = TowerData.tower.get(i);
                    td.attackTimer++;
                    if(td.attackTimer>td.attackSpeed){
                        mc.setTarget(td);
                        if(td.getPrimaryTarget()!=null){
                            mc.launchMissile(
                                                td.getTowerMissileImage(), 
                                                td.getAttackDamage(), 
                                                td.getMissileSpeed(), 
                                                td.getPrimaryTarget(), 
                                                td.getTowerMissileX(), 
                                                td.getTowerMissileY()
                                            );
                        }
                        td.attackTimer=0;
                    }
                }
                draw();
            }
        }  
        }catch(Exception e){
            e.printStackTrace(); 
    } 
}


    public void draw() {

        Graphics gs = bi.getGraphics();
        //gs.drawImage(imageManager.getBackgroundImage(), 0, 0, this);

        
        //When Game has ended
        if(PlayerData.player.gameover){
            if(!end){
                end=true;
                start=false;
                gs.drawImage(imageManager.getGameoverImage(), 140, 170, this);
                
            }
        }else{

            gs.setColor(Color.BLACK);
            gs.fillRect(0, 0, 1280, 960);
            //gs.setColor(Color.WHITE);
            gs.fillRect(0, 50, 1040, 570);
            gs.setColor(Color.LIGHT_GRAY);
            gs.fillRect(0, 450, 440, 170);
            
            
            //DisplayTowerData
            if(DataDisplayer.dd.selected){
                
                gs.setColor(Color.WHITE);
                gs.drawString("Damage: "+DataDisplayer.dd.damage, 10, 475);
                gs.drawString("Range: "+DataDisplayer.dd.range, 10, 515);
                if(DataDisplayer.dd.attackSpeed==0){
                    gs.drawString("Attack Spped: "+ DataDisplayer.dd.attackSpeed, 10, 555);
                }else{
                    gs.drawString("Attack Spped: "+ (6000/DataDisplayer.dd.attackSpeed)+" attacks per min", 10, 555);
                }
                gs.drawString("Price: "+DataDisplayer.dd.gold, 10, 595);
                if(DataDisplayer.dd.sellTower){
                    gs.setColor(Color.BLACK);
                    gs.fillRect(300, 550, 125, 50);
                    gs.setColor(Color.WHITE);
                    gs.drawString("Destroy Tower", 310, 575);
                }
            }
            
            
            
            for(int i=0;i<8;i++){
                gs.drawImage(imageManager.getTowerIconImage((2*i)+1), 440+(i*75), 470, this);
                gs.drawImage(imageManager.getTowerIconImage((2*i)+2), 440+(i*75), 545, this);
            }

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
                    ed=EnemyData.enemy.get(i);
                    ed.setPosition(ed.getPositionX(), ed.getPositionY());
                    gs.drawImage(ed.getUnitImage(), ed.getPositionX(), ed.getPositionY(), this);
                }
            }

            //Set Pathway

            if(!TowerData.tower.isEmpty()){
                for(int i=0;i<TowerData.tower.size();i++){
                    td=TowerData.tower.get(i);
                    gs.drawImage(td.getTowerHeadImage(),td.getTowerPositionX(), td.getTowerPositionY(), this);
                }
            }

            //Set HQ
            gs.drawImage(imageManager.getCastleImage(),PlayerData.player.castle.getPositionX(), PlayerData.player.castle.getPositionY(), this);
            
            //set Missiles
            if(!MissileData.md.isEmpty()){
                mc.moveMissile();
                for(int i=MissileData.md.size()-1;i>=0;i--){
                    md=MissileData.md.get(i);
                    if(md.getParticleX()>0&&md.getParticleTargetX()<WINDOW_WIDTH&&
                       md.getParticleY()>(10+TILE_HEIGHT)&&md.getParticleY()<(10+(TILE_HEIGHT*TILE_MAX_Y_NUM)))
                    gs.drawImage(md.getParticleIamage(),md.getParticleX(),md.getParticleY(), this);
                }
            }
            
            fps.checkFrame();
            fps.setFrame();
            gs.setColor(Color.WHITE);
            gs.drawString("FPS: "+fps.getFramePerSecond(), 10, 40);//Show Frame Per Second
            gs.setColor(Color.RED);
            gs.drawString("LIFE: "+PlayerData.player.life, 300, 40);//Show Frame Per Second
            gs.setColor(Color.YELLOW);
            gs.drawString("GOLD: "+PlayerData.player.gold, 600, 40);//Show Frame Per Second
            gs.setColor(Color.MAGENTA);
            gs.drawString("NEXT LEVEL: "+((EnemyInfo.levelDelay-levelDelay)*EnemyInfo.respondDelay)/1000+" (sec)", 900, 40);//Show Frame Per Second
        }

        
        
        
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

	private int resetMainMenu() {
		new MainFrame(true);
		MainMenuPage.displayMenuPage();
		keepGameAlive = false;
		start=false;
                return 2; // This means DISPOSE_ON_CLOSE
	}
}
