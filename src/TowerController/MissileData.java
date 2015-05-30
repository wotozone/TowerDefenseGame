/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowerController;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import unitController.EnemyData;

/**
 *
 * @author Tae
 */
public class MissileData {
    
    private BufferedImage particleImage = null;
    
    private int particleSpeed = 1;
    
    private int particleDamage = 1;
    
    private int x=800;
    private int y=800;
    
    
    private EnemyData targetUnit = null;
    
    private int targetX;
    private int targetY;
    
    private int splash = 0;// 0 if it has no splash damage & always it needs to be 1 if its not homming
    
    private int specialEffect = 0;//NOT GONNA USE IT
    
    public boolean isHomming = true;
    
    public static ArrayList<MissileData> md = new ArrayList<MissileData>();
    
    public void initParticle(BufferedImage image, int damage, int speed, EnemyData target,int startX, int startY){
        setParticleIamage(image);
        setParticlePosition(startX,startY);
        setParticleDamage(damage);
        setParticleSpeed(speed);
        setParticleTarget(target);
    }
    
    public void initParticle(BufferedImage image, int damage, int speed, EnemyData target, int splash, int special,int startX, int startY){
        setParticleIamage(image);
        setParticlePosition(startX,startY);
        setParticleDamage(damage);
        setParticleSpeed(speed);
        setParticleTarget(target);
        setParticleSplashRange(splash);
    }
    
    public void initParticle(BufferedImage image, int damage, int speed, int targetX, int targetY, int splash, int special,int startX, int startY){
        setParticleIamage(image);
        setParticlePosition(startX,startY);
        setParticleDamage(damage);
        setParticleSpeed(speed);
        setParticleTarget(targetX,targetY);
        if(splash<1)splash=1;
        setParticleSplashRange(splash);
        setSpecialEffect(special);
        isHomming=false;
    }
    
    
    
    
    
    public void setParticleIamage(BufferedImage image){
        particleImage = image;
    }
    
    public void setParticlePosition(int startX,int startY){
        x=startX;
        y=startY;
    }
    
    public void setParticleDamage(int damage){
        particleDamage=damage;
    }
    
    public void setParticleSpeed(int speed){
        particleSpeed=speed;
    }
    
    public void setParticleTarget(EnemyData unit){
        targetUnit = unit;
    }
    
    public void setParticleTarget(int targetX,int targetY){
        this.targetX=targetX;
        this.targetY=targetY;
    }
    
    public void setParticleSplashRange(int splash){
        this.splash=splash;
    }
    
    public void setSpecialEffect(int special){
        specialEffect=special;
    }
    
    
    
    
    
    public BufferedImage getParticleIamage(){
        return particleImage;
    }
    
    public int getParticleX(){
        return x;
    }
    
    public int getParticleY(){
        return y;
    }
    
    public int getParticleDamage(){
        return particleDamage;
    }
    
    public int getParticleSpeed(){
        return particleSpeed;
    }
    
    public EnemyData getParticleTarget(){
        return targetUnit;
    }
    
    public int getParticleTargetX(){
        return targetX;
    }
    
    public int getParticleTargetY(){
        return targetY;
    }
    
    public int getParticleSplashRange(){
        return splash;
    }
    
    public int getSpecialEffect(){
        return specialEffect;
    }
    
}
