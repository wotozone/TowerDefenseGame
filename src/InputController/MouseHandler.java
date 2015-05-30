package InputController;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

	private final Object parent;

    public MouseHandler( Object parent ) {
		this.parent = parent;
	}
    
    public MouseHandler() {
		this( null );
	}

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("Mouse Clicked: "+(me.getX())+"//"+(me.getY()));
        MouseController.mc.MouseClicked(me.getX(), me.getY());
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}