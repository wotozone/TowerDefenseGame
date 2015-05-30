/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowerController;

import java.awt.image.BufferedImage;
import unitController.EnemyData;

/**
 *
 * @author Tae
 */
public class MissileController {
    
    private MissileData output;
    private MissileData dummy;
    
    private EnemyData enemy;
    
    private double x1,y1,x2,y2;
    
    private double angle;
    
    private double ideal;
    
    
    public void launchMissile(BufferedImage image, int damage, int speed, EnemyData target,int startX, int startY){
        output=new MissileData();
        output.initParticle(image, damage, speed, target, startX, startY);
        addMissile();
    }
    
    public void launchMissile(BufferedImage image, int damage, int speed, EnemyData target, int splash, int special,int startX, int startY){
        output=new MissileData();
        output.initParticle(image, damage, speed, target, splash, special, startX, startY);
        addMissile();
    }
    
    public void launchMissile(BufferedImage image, int damage, int speed, int targetX, int targetY, int splash, int special,int startX, int startY){
        output=new MissileData();
        output.initParticle(image, damage, speed, targetX, targetY, splash, special, startX, startY);
        addMissile();
    }
    
    private void addMissile(){
        MissileData.md.add(output);
    }
    
    public void setTarget(TowerData tower){
        int pivotX=tower.getTowerPositionX();
        int pivotY=tower.getTowerPositionY();
        double maxRange = tower.getAttackRange();
        double deltaX;
        double deltaY;
        double dist;
        
        EnemyData primary = tower.getPrimaryTarget();
        EnemyData dummy=null;
        
        if(primary!=null){
            if(!primary.isEnemyDead()){
                dummy=primary;
                deltaX=pivotX-dummy.getPositionX();
                deltaY=pivotY-dummy.getPositionY();
                dist=Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
                if(dist>maxRange){
                    tower.setPrimaryTarget(null);
                    for(int i=0;i<EnemyData.enemy.size();i++){
                        dummy=EnemyData.enemy.get(i);
                        deltaX=pivotX-dummy.getPositionX();
                        deltaY=pivotY-dummy.getPositionY();
                        dist=Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
                        if(dist<=maxRange){
                            tower.setPrimaryTarget(dummy);
                            break;
                        }
                    }
                }
            }else{
                tower.setPrimaryTarget(null);
                for(int i=0;i<EnemyData.enemy.size();i++){
                    dummy=EnemyData.enemy.get(i);
                    deltaX=pivotX-dummy.getPositionX();
                    deltaY=pivotY-dummy.getPositionY();
                    dist=Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
                    if(dist<=maxRange){
                        tower.setPrimaryTarget(dummy);
                        break;
                    }
                }
            }
        }else{
            for(int i=0;i<EnemyData.enemy.size();i++){
                dummy=EnemyData.enemy.get(i);
                deltaX=pivotX-dummy.getPositionX();
                deltaY=pivotY-dummy.getPositionY();
                dist=Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
                if(dist<=maxRange){
                    tower.setPrimaryTarget(dummy);
                    break;
                }
            }
        }
    }
    
    public void moveMissile(){
        if(MissileData.md.size()>0){
            for(int i=0;i<MissileData.md.size();i++){
                dummy=MissileData.md.get(i);
                if(dummy.isHomming){
                    enemy=dummy.getParticleTarget();
                    setPositions(dummy.getParticleX(),dummy.getParticleY(),enemy.getPositionX(),enemy.getPositionY());
                }else{
                    setPositions(dummy.getParticleX(),dummy.getParticleY(),dummy.getParticleTargetX(),dummy.getParticleTargetY());
                }
                if(isArrived()){
                    setFinalPosition();
                    //AddHitEffect - if we can do
                    DamageEnemy();
                    MissileData.md.remove(i);
                }else{
                    setAngle();
                    setNewPosition();
                }
                enemy=null;
            }
        }
    }
    
    private void setPositions(int x1,int y1, int x2, int y2){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2+10;
        this.y2=y2+10;
    }
    
    private boolean isArrived(){
        double dist=Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
        if(dist<dummy.getParticleSpeed())return true;
        return false;
    }
    
    private void setAngle(){
        if((x2-x1)==0){
            angle = Math.PI/2;
            if((y2-y1)>0){
                ideal = 1;
            }else{
                ideal = -1;
            }
        }else if((y2-y1)==0){
            angle = Math.PI;
            if((x2-x1)>0){
                ideal = -1;
            }else{
                ideal = 1;
            }
        }else{
            if((x2-x1)>0){
                ideal = 1;
            }else{
                ideal = -1;
            }
            angle = Math.atan((y2-y1)/(x2-x1));
        }
        //double a = Math.atan(1/5);
        double a = Math.atan2(1,0);
    }
    
    private void setNewPosition(){
        x1+=ideal*dummy.getParticleSpeed()*Math.cos(angle);
        y1+=ideal*dummy.getParticleSpeed()*Math.sin(angle);
        dummy.setParticlePosition((int) x1,(int) y1);
    }
    
    private void setFinalPosition(){
        dummy.setParticlePosition(dummy.getParticleTargetX(), dummy.getParticleTargetY());
    }
    
    private void DamageEnemy(){//We will not cover splash damage thing right now
        if(enemy!=null){
            enemy.dmgHp(dummy.getParticleDamage());
        }
        
    }
    
}
