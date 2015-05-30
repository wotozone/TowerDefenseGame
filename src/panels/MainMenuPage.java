
package panels;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static main.MainFrame.mainFrame;
import mainFrame.GameStart;
import net.miginfocom.swing.MigLayout;

/**
 * @author Chris
 */
public class MainMenuPage implements ActionListener, MouseListener {
	
	private static JLabel leftArrow;
	private static JLabel rightArrow;
	private static JPanel levelImage;
	private static int choosenLevel = 1;
	
	public static void displayMenuPage(){
		mainFrame.resetFrame("Create Account","50%","400",false,true);
		JPanel container = new JPanel();
		container.setLayout(new MigLayout(mainFrame.debugCheck()+""));
		//
		leftArrow = new JLabel();
		leftArrow.setIcon(new javax.swing.ImageIcon(MainMenuPage.class.getResource("/arrow-left.png")));
		rightArrow = new JLabel();
		rightArrow.setIcon(new javax.swing.ImageIcon(MainMenuPage.class.getResource("/arrow-right.png")));
		Image img;
		try {
			img = ImageIO.read(new File("Resources/level-1.jpg"));
			levelImage = new BackgroundPanel(img);
		} catch (IOException ex) {
			levelImage = new JPanel();
			System.out.println("Could not load level image! "+ex);
		}
		//
		Font font = leftArrow.getFont();
		Font boldFont = new Font(font.getFontName(),Font.BOLD,font.getSize()+4);
		//
		JButton play = new JButton("Play Level");
		play.setFont(boldFont);
		JButton scores = new JButton("Highscores");
		scores.setFont(boldFont);
		JButton help = new JButton("Instructions");
		help.setFont(boldFont);
		JButton goBack = new JButton("Change Account");
		goBack.setFont(boldFont);
		leftArrow.setHorizontalAlignment(SwingConstants.RIGHT);
		leftArrow.setName("menuLeftArrow");
		leftArrow.addMouseListener(new MainMenuPage());
		container.add(leftArrow,"w 20%,right,grow,push");
		container.add(levelImage,"w 80%,center,grow,push");
		rightArrow.setName("menuRightArrow");
		rightArrow.addMouseListener(new MainMenuPage());
		container.add(rightArrow,"w 20%,left,grow,push,wrap");
		play.setActionCommand("play");
		play.addActionListener(new MainMenuPage());
		container.add(play,"sg btn,span3,w 60%,h 40px,gap 0 0 10px 10px,center,wrap");
		scores.setActionCommand("scores");
		scores.addActionListener(new MainMenuPage());
		container.add(scores,"sg btn,span3,gap 0 0 8px 8px,center,wrap");
		help.setActionCommand("help");
		help.addActionListener(new MainMenuPage());
		container.add(help,"sg btn,span3,gap 0 0 8px 8px,center,wrap");
		goBack.setActionCommand("goBack");
		goBack.addActionListener(new MainMenuPage());
		container.add(goBack,"sg btn,span3,gap 0 0 8px 8px,center,wrap");
		//
		mainFrame.addContent(container,"push,grow,center");
		mainFrame.displayFrame();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		switch(action){
			case "play":
				int score = 25000;
				boolean flag = false;
				switch(choosenLevel){
					case 1:
						// Start actual game
						new GameStart();
						break;
					case 2:
						flag = true;
						score = score*choosenLevel;
						break;
					case 3:
						flag = true;
						score = score*choosenLevel;
						break;
					case 4:
						flag = true;
						score = score*choosenLevel;
						break;
					case 5:
						flag = true;
						score = score*choosenLevel;
						break;
				}
				if (flag==true){
					JOptionPane.showMessageDialog(null,"<html><body style='font-size:112%;'>This level is locked. You must score "+score+" points or higher on level "+(choosenLevel-1)+" to unlock this level.</body></html>","Level Locked",1);
				}
				break;
			case "scores":
				break;
			case "help":
				break;
			case "goBack":
				LoginPage.displayLogin();
				break;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		String action = me.getComponent().getName(); // This is NOT normal, work around to act like action listener
		if (action=="menuLeftArrow"){
			if(choosenLevel==1){
				choosenLevel = 5;
			} else {
				choosenLevel -= 1;
			}
			updateImage(choosenLevel);
		}
		if (action=="menuRightArrow"){
			if(choosenLevel==5){
				choosenLevel = 1;
			} else {
				choosenLevel += 1;
			}
			updateImage(choosenLevel);
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {}

	@Override
	public void mouseReleased(MouseEvent me) {}

	@Override
	public void mouseEntered(MouseEvent me) {
		String action = me.getComponent().getName(); // This is NOT normal, work around to act like action listener
		if (action=="menuLeftArrow"){
			JLabel tmp = (JLabel) me.getSource();
			tmp.setIcon(new javax.swing.ImageIcon(MainMenuPage.class.getResource("/arrow-left-hover.png")));
		}
		if (action=="menuRightArrow"){
			JLabel tmp = (JLabel) me.getSource();
			tmp.setIcon(new javax.swing.ImageIcon(MainMenuPage.class.getResource("/arrow-right-hover.png")));
		}
	}

	@Override
	public void mouseExited(MouseEvent me) {
		String action = me.getComponent().getName(); // This is NOT normal, work around to act like action listener
		if (action=="menuLeftArrow"){
			JLabel tmp = (JLabel) me.getSource();
			tmp.setIcon(new javax.swing.ImageIcon(MainMenuPage.class.getResource("/arrow-left.png")));
		}
		if (action=="menuRightArrow"){
			JLabel tmp = (JLabel) me.getSource();
			tmp.setIcon(new javax.swing.ImageIcon(MainMenuPage.class.getResource("/arrow-right.png")));
		}
	}
	
	private void updateImage(int level){
		Image img;
		BackgroundPanel panel = (BackgroundPanel) levelImage;
		try {
			img = ImageIO.read(new File("Resources/level-"+level+".jpg"));
			panel.setImage(img);
		} catch (IOException ex) {
			System.out.println("There was an error tryng to change the level image: "+ex);
		}
		mainFrame.validate();
		mainFrame.repaint();
	}
}