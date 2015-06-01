
package panels;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static main.MainFrame.mainFrame;

/**
 * @author Chris
 */
public class SplashScreen implements Runnable {
	
	private static final int  displayTime = 0; // 1000 miliseconds is 1 second
	
	public void run(){
		
		displaySplashScreen();
		boolean display = true;
		while (display){
			try {
				Thread.sleep(displayTime);
				display = false;
			} catch (InterruptedException ex) {
				System.out.println("Sleeping splash screen was not possible because: "+ex);
			}
		}
		closeSplashScreen();
	}
	
	private void displaySplashScreen(){
		mainFrame.resetFrame("","75%","50%",false,false);
		Image splashImage;
		try {
			splashImage = ImageIO.read(new File("Resources/tdc-logo-smaller.jpg"));
			JPanel tmp = new BackgroundPanel(splashImage);
			mainFrame.addContent(tmp,"push,grow");
		} catch (IOException ex) {
			System.out.println("Could not load splash screen background image! "+ex);
		}
		mainFrame.displayFrame();
	}

	private void closeSplashScreen() {
		LoginPage.displayLogin();
	}

}