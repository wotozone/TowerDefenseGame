
package panels;

import com.michael.api.Encoder;
import includes.GetPassword;
import includes.Validation;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.MainFrame;
import static main.MainFrame.mainFrame;
import net.miginfocom.swing.MigLayout;
import static towerdefenseproject.MysqlCon.connectionStatus;
import static towerdefenseproject.MysqlCon.createId;
import static towerdefenseproject.MysqlCon.createTableFromFile;
import static towerdefenseproject.MysqlCon.dbClose;
import static towerdefenseproject.MysqlCon.dbOpen;
import static towerdefenseproject.MysqlCon.numRows;
import static towerdefenseproject.MysqlCon.query;
import static towerdefenseproject.MysqlCon.tableExists;

/**
 * @author Chris
 */
public class SignUpPage implements ActionListener, Runnable {
	
	public static JTextField username;
	public static JTextField email;
	public static JTextField reEmail;
	public static JPasswordField password;
	public static JPasswordField rePassword;
	
	public static void displaySignUpPage(){
		username = new JTextField();
		email = new JTextField();
		reEmail = new JTextField();
		password = new JPasswordField();
		rePassword = new JPasswordField();
		mainFrame.resetFrame("Create Account","300","400",false,true);
		MainFrame.activeClass = "SignUpPage";
		JPanel container = new JPanel();
		container.setLayout(new MigLayout(mainFrame.debugCheck()+""));
		JLabel lbUsername = new JLabel("Username:");
		JLabel lbEmail = new JLabel("Email:");
		JLabel lbReEmail = new JLabel("Confirm Email:");
		JLabel lbPassword = new JLabel("Password:");
		JLabel lbRePassword = new JLabel("Confirm Password:");
		JButton btnCreate = new JButton("Create Account");
		btnCreate.setActionCommand("createAccount");
		btnCreate.addActionListener(new SignUpPage());
		JButton btnBack = new JButton("Go Back");
		btnBack.setActionCommand("goBack");
		btnBack.addActionListener(new SignUpPage());
		//
		Font font = lbEmail.getFont();
		Font boldFont = new Font(font.getFontName(),Font.BOLD,font.getSize()+3);
		Font largeFont = new Font(font.getFontName(),Font.PLAIN,font.getSize()+3);
		lbUsername.setFont(boldFont);
		lbEmail.setFont(boldFont);
		lbReEmail.setFont(boldFont);
		lbPassword.setFont(boldFont);
		lbRePassword.setFont(boldFont);
		username.setFont(largeFont);
		username.setName("username");
		email.setFont(largeFont);
		email.setName("email");
		reEmail.setFont(largeFont);
		reEmail.setName("conf-email");
		password.setFont(largeFont);
		password.setName("password");
		rePassword.setFont(largeFont);
		rePassword.setName("conf-password");
		btnCreate.setFont(largeFont);
		btnBack.setFont(largeFont);
		//
		container.add(lbUsername,"sg cells,wmax 80%,h 40px,center,pushx,growx,wrap");
		container.add(username,"sg cells,center,pushx,growx,wrap");
		container.add(lbEmail,"sg cells,center,pushx,growx,wrap");
		container.add(email,"sg cells,center,pushx,growx,wrap");
		container.add(lbReEmail,"sg cells,center,pushx,growx,wrap");
		container.add(reEmail,"sg cells,center,pushx,growx,wrap");
		container.add(lbPassword,"sg cells,center,pushx,growx,wrap");
		container.add(password,"sg cells,center,pushx,growx,wrap");
		container.add(lbRePassword,"sg cells,center,pushx,growx,wrap");
		container.add(rePassword,"sg cells,center,pushx,growx,wrap");
		container.add(btnCreate,"sg buttons,wmax 70%,h 40px,center,pushx,gaptop 10px,growx,wrap");
		container.add(btnBack,"sg buttons,center,pushx,gaptop 10px,growx,wrap");
		mainFrame.addContent(container,"push,grow");
		mainFrame.displayFrame();
		(new Thread (new SignUpPage())).start();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		switch(action){
			case "createAccount":
				createNewAccount();
				break;
			case "goBack":
				LoginPage.displayLogin();
				break;
		}
	}
	
	@Override
	public void run() {
		boolean flag = true;
		while (flag){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				System.out.println("Run away thread! Sleep will not fire on sign up page: "+ex);
			}
			
			if (MainFrame.activeClass!="SignUpPage"){
				flag = false;
			}
			
			runValidation();
		}
	}

	private void createNewAccount() {
		if (runValidation()){
			dbOpen();
			if (connectionStatus()){
				int check = numRows( "td_users", "email = '" + email.getText() + "'" );
				if (check==0){
					if ( !tableExists( "td_users" ) ) {
						createTableFromFile( "td_users" );
					}
					PreparedStatement state = query( "INSERT INTO td_users (id,username,email,password,created) VALUES (?,?,?,?,?)" );
					try {
						state.setString( 1, createId() );
						state.setString( 2, username.getText() );
						state.setString( 3, email.getText() );
						state.setString( 4, Encoder.getMd5( GetPassword.getPassword(password.getPassword()) ) );
						state.setLong( 5, System.currentTimeMillis() / 1000L );
						check = state.executeUpdate();
						if (check>0){
							dbClose();
							LoginPage.displayLogin();
							LoginPage.email.setText(email.getText());
							LoginPage.password.setText(GetPassword.getPassword(password.getPassword()));
						} else {
							JOptionPane.showMessageDialog( null, "We could not create your account, please try again.", "Error", 0 );
						}
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog( null, "Unknown fatal MySQL error occurced. Try again later.", "Fatal Error", 0 );
					}

				} else {
					JOptionPane.showMessageDialog( null, "There is a user with that email address already registered.", "Error", 2 );
				}
			} else {
				JOptionPane.showMessageDialog( null, "There is no database connection, you will not be able to play this game until this is fixed.", "Fatal Error", 0 );
			}
			dbClose();
		}
	}

	private boolean runValidation() {
		boolean valid = true;
		
		if (username.getText().length()>0) {
				valid = Validation.validateInput( username.getText(), username.getName() );
				if  (valid==false){ username.setBackground(Color.PINK); } else { username.setBackground(null); }
		} else {
			valid = false;
		}

		if (email.getText().length()>0) {
			valid = Validation.validateInput( email.getText(), email.getName() );
			if  (valid==false){ email.setBackground(Color.PINK); } else { email.setBackground(null); }
		} else {
			valid = false;
		}

		if (reEmail.getText().length()>0) {
			valid = Validation.validateInput( reEmail.getText(), reEmail.getName(), reEmail.getText() );
			if  (valid==false){ reEmail.setBackground(Color.PINK); } else { reEmail.setBackground(null); }
		} else {
			valid = false;
		}

		if (GetPassword.getPassword(password.getPassword()).length()>0) {
			valid = Validation.validateInput( GetPassword.getPassword(password.getPassword()), password.getName() );
			if  (valid==false){ password.setBackground(Color.PINK); } else { password.setBackground(null); }
		} else {
			valid = false;
		}

		if (GetPassword.getPassword(rePassword.getPassword()).length()>0) {
			valid = Validation.validateInput( GetPassword.getPassword(rePassword.getPassword()), rePassword.getName() );
			if  (valid==false){ rePassword.setBackground(Color.PINK); } else { rePassword.setBackground(null); }
		} else {
			valid = false;
		}
			
		return valid;
	}
}