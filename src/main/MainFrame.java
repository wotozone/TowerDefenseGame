
package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import panels.SplashScreen;

/**
 * Create Game Frame (Window)
 * @author Chris Keers
 */
public class MainFrame extends JFrame {
	
	/**
	 * Setup global variables used by the frame and parts of the game
	 */
	public static int frameWidth;
	public static int frameHeight;
	private static final boolean debug = false;
	public static MainFrame mainFrame;
	public static String activeClass;
	
	/**
	 * Create games frame
	 */
	public MainFrame(){
		setTitle("Tower Defense");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameWidth = screenSize.width;
		frameHeight = screenSize.height;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400,500));
		this.getContentPane().setLayout(new MigLayout(debugCheck()+"insets 0,gap 0!"));
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	public MainFrame(boolean restart){
		if (restart==false){
			new MainFrame();
		} else {
			resetFrame(true,true);
		}
	}
	
	public void resetFrame(boolean resizeable,boolean controlsOn){
		resetFrame("","","",resizeable,controlsOn);
	}
	
	public void resetFrame(String title,String width,String height,boolean resizeable,boolean controlsOn){
		// Work out new dimensions
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameWidth = screenSize.width;
		frameHeight = screenSize.height;
		// Hide frame
		activeClass = "";
		boolean fullScreen = false;
		mainFrame.dispose();
		float intWidth = 0, intHeight = 0, tmpNum;
		if (width==""){ 
			intWidth = frameWidth;
		} else if (width.contains("%")) {
			tmpNum = Integer.parseInt(width.replaceAll("[^\\d]",""));
			if (tmpNum>=5&&tmpNum<=99){
				intWidth = frameWidth*(tmpNum/100);
			} else {
				if (tmpNum==100){
					fullScreen = true;
				} else {
					intWidth = frameWidth;
				}
			}
		} else {
			intWidth = Integer.parseInt(width.replaceAll("[^\\d]",""));
		}
		if (height==""){ 
			intHeight = frameHeight;
		} else if (height.contains("%")) {
			tmpNum = Integer.parseInt(height.replaceAll("[^\\d]",""));
			if (tmpNum>=5&&tmpNum<=99){
				intHeight = frameHeight*(tmpNum/100);
			} else {
				if (tmpNum==100){
					fullScreen = true;
				} else {
					intHeight = frameHeight;
				}
			}
		} else {
			intHeight = Integer.parseInt(height.replaceAll("[^\\d]",""));
		}
		// Work out new title
		if(title==""){
			title = "Tower Defense";
		} else {
			title = "Tower Defense - "+title;
		}
		// Work out controls or not
		if(controlsOn==true){
			mainFrame.setUndecorated(false);
		} else {
			mainFrame.setUndecorated(true);
		}
		// Work out if resize is allowed
		if(resizeable==true){
			mainFrame.setResizable(true);
		} else {
			mainFrame.setResizable(false);
		}
		// Clear the old frame and display the new frame contents
		mainFrame.setTitle(title);
		mainFrame.getContentPane().removeAll();
		if (fullScreen==true){
			System.out.println("MADE IT");
			mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			mainFrame.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
			mainFrame.setLocation(0,0);
		} else {
			System.out.println("WHY?");
			mainFrame.setSize((int) intWidth,(int) intHeight);
			mainFrame.setLocationRelativeTo(null);
		}
//		mainFrame.setLocationRelativeTo(null);
	}
	
	public void addContent(JPanel content,String migLayoutConstraints){
		mainFrame.getContentPane().add(content,migLayoutConstraints);
	}
	
	public void displayFrame(){
		mainFrame.validate();
		mainFrame.repaint();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Debug check for MigLayout
	 * @return value needed to show or hide MigLayout debug
	 */
	public String debugCheck(){
		if (debug==true){
			return "debug 1000,";
		} else {
			return "";
		}
	}
	
	/**
	 * Record frames size so we can maintain it on frame updates
	 */
	public void updateFrameSize(){
		Dimension screenSize = mainFrame.getSize();
		frameWidth = screenSize.width;
		frameHeight = screenSize.height;
	}
	
	/**
	 * Record frames size so we can maintain it on frame updates
	 */
	public void updateFrameSizeNonStatic(){
		Dimension screenSize = mainFrame.getSize();
		frameWidth = screenSize.width;
		frameHeight = screenSize.height;
	}
	
	/**
	 * Main method
	 * @param args unused in this instance
	 */
	public static void main(String[] args){
		
		/**
		 * Attempt to change look and feel from default nimbus to beautyeye
		 */
		try {   
			BeautyEyeLNFHelper.frameBorderStyle =  BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated; 
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible",false);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		mainFrame = new MainFrame();
		
		(new Thread (new SplashScreen())).start();
	}
}