/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFrame;

import main.MainFrame;

/**
 *
 * @author Tae
 */
public class GameStart {
    
	public GameStart(){
		main(new String[] {"empty"});
	}
	
    public static void main(String[] args) {
		MainFrame.mainFrame.updateFrameSize();
		MainFrame.mainFrame.dispose();
        Thread t = new Thread(new MainPanel());
        t.start();
    }
}
