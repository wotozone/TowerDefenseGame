/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import com.michael.api.Encoder;
import includes.GetPassword;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static main.MainFrame.mainFrame;
import net.miginfocom.swing.MigLayout;
import static towerdefenseproject.MysqlCon.connectionStatus;
import static towerdefenseproject.MysqlCon.dbClose;
import static towerdefenseproject.MysqlCon.dbOpen;
import static towerdefenseproject.MysqlCon.query;

/**
 * @author Chris
 */
public class LoginPage implements ActionListener {
	
	public static JTextField email;
	public static JPasswordField password;
	
	public static void displayLogin(){
		email =  new JTextField();
		password = new JPasswordField();
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
				LoginUser();
				break;
			case "addAccount":
				SignUpPage.displaySignUpPage();
				break;
		}
	}

	private void LoginUser() {
		MainMenuPage.displayMenuPage();
		dbOpen();
		if (connectionStatus()){
			String enteredEmail = email.getText();
			String enteredPassword = GetPassword.getPassword(password.getPassword());
			PreparedStatement state = query("SELECT id FROM td_users WHERE email = ? AND password = ?");
			try {
				state.setString(1,enteredEmail);
				state.setString(2,Encoder.getMd5(enteredPassword));
				ResultSet result = state.executeQuery();
				if (result.next()!=false){
					// todo save this userId, seralize?
					String userId = result.getString("id");
					dbClose();
					MainMenuPage.displayMenuPage();
				} else {
					JOptionPane.showMessageDialog(null,"The credentials you provided were incorrect, please try again.","Error Logging In",2);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null,"There is an error with your SQL commands, you will not be able to play this game until this is fixed.","Fatal Error",0);
			}
		} else {
			JOptionPane.showMessageDialog(null,"There is no database connection, you will not be able to play this game until this is fixed.","Fatal Error",0);
		}
		dbClose();
	}
}
