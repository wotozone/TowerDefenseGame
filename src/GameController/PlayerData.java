/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameController;

import pathController.PathController;


/**
 *
 * @author Tae
 */
public class PlayerData {
    
    public int gold;
    public int life;
    public int score;//not gonna use it yet
    
    public PathController castle;
    
    public boolean gameover;
    
    
    public static PlayerData player = new PlayerData();
    
    public PlayerData(){
        initPlayerData();
    }
    
    public void initPlayerData(){
        gold = 300;
        life = 10;
        gameover=false;
    }
    
    public void loseLifePoint(){
        life--;
        if(life<=0){
            gameover=true;
        }
    }
    
    public void gainGold(int gold){
        this.gold+=gold;
    }
    
    public boolean useGold(int amount){
        if(amount>gold){
            System.out.println("NOT ENOUGH GOLD");
            return false;
        }else{
            gold-=amount;
            return true;
        }
    }
}
