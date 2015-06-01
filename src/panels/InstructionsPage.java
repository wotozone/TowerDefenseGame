
package panels;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static main.MainFrame.mainFrame;
import net.miginfocom.swing.MigLayout;

/**
 * @author Chris
 */
public class InstructionsPage {
	
	
	public static void displayInstructions(){
		mainFrame.resetFrame("Create Account","100%","100%",false,true);
		JPanel container = new JPanel();
		container.setLayout(new MigLayout(mainFrame.debugCheck()+""));
		
		JButton goBack = new JButton("Back to Main Menu");
		JScrollPane scrollWrapper = new JScrollPane();
		JEditorPane editorPane = new JEditorPane();
		editorPane.setLayout(new MigLayout());
		editorPane.setContentType( "text/html" );
		editorPane.setText(helpContent);
		editorPane.setCaretPosition(0);
		editorPane.setEditable(false);
		//
		scrollWrapper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollWrapper.getViewport().add(editorPane);
		//
		container.add(goBack,"push,grow,center,wmax 60%,h 40px,wrap");
		container.add(scrollWrapper,"push,grow,wrap");
		
		mainFrame.addContent(container,"grow,push");
		mainFrame.displayFrame();
	}
	
	private static String helpContent = "<html><body style=''>" + 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur semper pellentesque lacus vitae viverra. In hac habitasse platea dictumst. Donec tincidunt magna justo, et efficitur lacus vehicula eu. Nam laoreet at lorem nec lacinia. Pellentesque sit amet aliquet tellus, fermentum euismod enim. Pellentesque a tortor eget nisi congue condimentum eget quis elit. Etiam risus enim, dignissim varius eros eget, luctus scelerisque purus. Nulla elementum suscipit consectetur. Sed molestie orci nec elit fringilla posuere. Suspendisse erat lectus, fermentum nec augue hendrerit, sollicitudin gravida mi. Nunc et dui eu nibh tempus finibus. Donec eget tortor neque. Praesent iaculis pharetra pellentesque. Donec velit turpis, aliquet laoreet magna nec, tincidunt tincidunt massa. Pellentesque sed tempus enim, vel condimentum ex. Vivamus aliquet sapien vitae commodo tincidunt.<br />" +
"<br />" +
"Vivamus a imperdiet lectus. Aliquam at sapien ornare, porta velit quis, euismod dolor. Quisque est nunc, cursus et nunc ut, fermentum auctor dolor. Nam vulputate diam risus, in dignissim leo rhoncus ut. Sed a egestas nunc. Sed quam massa, dignissim iaculis nisi lacinia, finibus eleifend justo. Ut sed eros in arcu fringilla auctor id eu tortor. Praesent pulvinar, libero et feugiat convallis, leo arcu auctor erat, eget iaculis neque enim ac eros. In euismod ipsum eu ipsum bibendum pulvinar. Nam lacus sapien, efficitur id vehicula a, finibus et libero. Cras pharetra hendrerit dictum. Suspendisse magna nisl, lacinia in mattis at, ornare ac enim. Cras porta venenatis magna non hendrerit. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla auctor sodales est, quis consectetur metus imperdiet ac. Duis sed consequat justo.<br />" +
"<br />" +
"Nunc urna erat, hendrerit eget ante eu, dapibus iaculis massa. In dui arcu, vestibulum at hendrerit vel, dignissim sit amet sapien. Donec efficitur arcu non tellus pulvinar consequat. Nullam sit amet enim hendrerit, aliquet magna eu, fermentum velit. Phasellus nec convallis nunc, vel gravida purus. Mauris vitae volutpat tortor. Proin dignissim eget sapien nec vulputate. Nullam sed tortor sit amet tellus dignissim dictum id at urna. Maecenas volutpat lorem felis, a condimentum felis auctor eget. Nullam facilisis sem sem, a dapibus orci lacinia a. Donec faucibus neque eu justo accumsan faucibus et mattis nunc. Vestibulum lectus nisi, pulvinar non porttitor non, lobortis vitae nibh.<br />" +
"<br />" +
"Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean eget lacus consectetur, tincidunt tortor at, bibendum erat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Duis nunc libero, sollicitudin eget leo vel, elementum scelerisque mauris. Curabitur eu tincidunt lorem. Nunc luctus lacinia vestibulum. Morbi tincidunt porttitor nunc, consectetur volutpat justo lacinia in. Phasellus sed tempor enim. Sed dictum ut lacus sit amet cursus. Praesent viverra dictum eros in aliquet. Vivamus imperdiet luctus feugiat. Etiam eu odio mi. Sed malesuada ipsum nec dolor tempor sodales. Aliquam ac purus lacus.<br />" +
"<br />" +
"Integer in nibh venenatis, rhoncus mauris ut, dictum lectus. Donec efficitur nunc in vehicula vulputate. Nullam mattis nunc ut urna condimentum consectetur. Donec ultricies turpis vitae mi dapibus convallis. Aliquam ac lorem a dolor suscipit rutrum in quis nisi. Sed bibendum lobortis elit nec consequat. Sed condimentum mauris sem. Nulla accumsan neque non faucibus suscipit. Nam ac elementum turpis. Donec tortor mauris, hendrerit a lacus quis, porttitor malesuada est. Duis quis aliquet erat. Sed malesuada justo a eleifend tempor. Quisque ut velit vitae est tincidunt eleifend. Ut justo est, condimentum et massa vitae, blandit finibus turpis."
				+ "</body></html>";
}