/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowerController;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import pathController.PathController;
import unitController.EnemyData;

/**
 *
 * @author Tae
 */
public class TowerData {
    
    public int damage=1;
    public int range=1;
    public int splash=0;//Splash is initially unlocked. Do not set splash value if tower attacks only one target
    public int attackSpeed=100;// attack per 1 sec (ex. 2000=attack per 2sec) <-CHANGED!! attack per (100/100)sec
    public int missileSpeed=1;//move 100 pixel per sec
    public String specialSkill="normal";
    
    public EnemyData primaryTarget;
    
    public boolean homing = true;//Tower fires homing missile initially.
    
    public BufferedImage towerBody=null;
    public BufferedImage towerHead=null;
    public BufferedImage towerMissile=null;
    
    public boolean rotatable=false;//set the tower head is rotatable
    
    public int towerAngle=0;//the angle where the tower looks at // unable to use atm
    public int particleX=0;//its all for dummy
    public int particleY=0;
    public int positionX=0;
    public int positionY=0;
    public PathController position=null;
    
    public int price=10;
    
    public int attackTimer=0;
    
    public static ArrayList<TowerData> tower = new ArrayList<TowerData>();
    
    public void initTowerData(int atk, int range, int splash, int atkSpeed, int missileSpeed,int price){
        setAttackDamage(atk);
        setAttackRange(range);
        setAttackSplash(splash);
        setAttackSpeed(atkSpeed);
        setMissileSpeed(missileSpeed);
        setPrice(price);
    }
    
    public void initTowerData(int atk, int range, int atkSpeed, int missileSpeed,int price){
        setAttackDamage(atk);
        setAttackRange(range);
        setAttackSpeed(atkSpeed);
        setMissileSpeed(missileSpeed);
        setPrice(price);
    }
    
    public void setPrice(int amount){
        price = amount;
    }
    
    public void setAttackTimer(){
        attackTimer++;
    }
    
    public void setAttackTimer(int value){
        attackTimer=value;
    }
    
    public void setAttackDamage(int value){
        damage = value;
    }
    
    public void setAttackRange(int value){
        range = value;
    }
    
    public void setAttackSplash(int value){
        splash = value;
    }
    
    public void setAttackSpeed(int value){
        attackSpeed=value;
    }
    
    public void setMissileSpeed(int value){
        missileSpeed=value;
    }
    
    public void setTowerImage(BufferedImage body, BufferedImage head, BufferedImage missile, boolean rotatable){
        towerBody=body;
        towerHead=head;
        towerMissile=missile;
        this.rotatable=rotatable;
    }
    
    public void setTowerImage(BufferedImage head, BufferedImage missile){
        towerBody=null;
        towerHead=head;
        towerMissile=missile;
    }
    
    public void setTowerPosition(PathController path, int x, int y, int missileX, int missileY){
        position=path;
        positionX=position.getPositionX();
        positionY=position.getPositionY();
        particleX=missileX;
        particleY=missileY;
    }
    
    public void setTowerPosition(PathController path, int x, int y){
        position=path;
        positionX=position.getPositionX()+x;
        positionY=position.getPositionY()+y;
        particleX=positionX;
        particleY=positionY;
    }
    
    public void setPrimaryTarget(EnemyData enemy){
        primaryTarget=enemy;
    }
    
    
    
    
    public int getAttackDamage(){
        return damage;
    }
    
    public int getPrice(){
        return price;
    }
    
    public int getAttackRange(){
        return range;
    }
    
    public int getAttackSplash(){
        return splash;
    }
    
    public int getAttackSpeed(){
        return attackSpeed;
    }
    
    public int getMissileSpeed(){
        return missileSpeed;
    }
    
    public BufferedImage getTowerHeadImage(){
        return towerHead;
    }
    public BufferedImage getTowerBodyImage(){
        return towerBody;
    }
    public BufferedImage getTowerMissileImage(){
        return towerMissile;
    }
    
    public PathController getTowerPath(){
        return position;
    }
    
    public int getTowerPositionX(){
        return positionX;
    }
    
    public int getTowerPositionY(){
        return positionY;
    }
    
    public int getTowerMissileX(){
        return particleX;
    }
    
    public int getTowerMissileY(){
        return particleY;
    }
    
    public EnemyData getPrimaryTarget(){
        return primaryTarget;
    }
    
    public void clearData(){
        damage=1;
        range=1;
        splash=0;//Splash is initially unlocked. Do not set splash value if tower attacks only one target
        attackSpeed=100;// attack per 1 sec (ex. 2000=attack per 2sec)
        missileSpeed=1;//move 100 pixel per sec
        specialSkill="normal";

        homing = true;//Tower fires homing missile initially.

        towerBody=null;
        towerHead=null;
        towerMissile=null;

        rotatable=false;//set the tower head is rotatable

        towerAngle=0;//the angle where the tower looks at // unable to use atm
        particleX=0;//its all for dummy
        particleY=0;
        positionX=0;
        positionY=0;
        position=null;
        
        price = 10;
    }
}
