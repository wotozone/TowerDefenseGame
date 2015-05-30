/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static main.MainFrame.mainFrame;
import net.miginfocom.swing.MigLayout;

/**
 * @author Chris
 */
public class LoginPage implements ActionListener {
	
	public static JTextField email = new JTextField();
	public static JPasswordField password = new JPasswordField();
	
	public static void displayLogin(){
		mainFrame.resetFrame("Login","300","400",false,true);
		JPanel container = new JPanel();
		container.setLayout(new MigLayout(mainFrame.debugCheck()+""));
		JLabel lbEmail = new JLabel("Email:");
		JLabel lbPassword = new JLabel("Password:");
		JButton btnLogin = new JButton("Login");
		btnLogin.setActionCommand("signIn");
		btnLogin.addActionListener(new LoginPage());
		JButton btnSignUp = new JButton("New Account");
		btnSignUp.setActionCommand("addAccount");
		btnSignUp.addActionListener(new LoginPage());
		Font font = lbEmail.getFont();
		Font boldFont = new Font(font.getFontName(),Font.BOLD,font.getSize()+4);
		Font largeFont = new Font(font.getFontName(),Font.PLAIN,font.getSize()+4);
		lbEmail.setFont(boldFont);
		lbPassword.setFont(boldFont);
		email.setFont(largeFont);
		password.setFont(largeFont);
		btnLogin.setFont(largeFont);
		btnSignUp.setFont(largeFont);
		//
		Image splashImage;
		try {
			splashImage = ImageIO.read(new File("Resources/login-logo.jpg"));
			JPanel tmp = new BackgroundPanel(splashImage);
			container.add(tmp,"pushx,growx,hmin 20%,hmax 20%,wrap");
		} catch (IOException ex) {
			System.out.println("Could not load splash screen background image! "+ex);
		}
		container.add(lbEmail,"sg cells,wmax 80%,h 40px,center,pushx,growx,wrap");
		container.add(email,"sg cells,center,pushx,growx,wrap");
		container.add(lbPassword,"sg cells,center,pushx,growx,wrap");
		container.add(password,"sg cells,center,pushx,growx,wrap");
		container.add(btnLogin,"sg buttons,wmax 70%,h 40px,center,pushx,gaptop 10px,growx,wrap");
		container.add(btnSignUp,"sg buttons,center,pushx,gaptop 10px,growx,wrap");
		//
		mainFrame.addContent(container,"push,grow,center");
		mainFrame.displayFrame();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		switch(action){
			case "signIn":
				MainMenuPage.displayMenuPage();
				break;
			case "addAccount":
				SignUpPage.displaySignUpPage();
				break;
		}
	}
}
