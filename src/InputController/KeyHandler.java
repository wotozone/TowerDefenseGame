package InputController;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyHandler implements KeyListener {

	private final Object parent;

    public KeyHandler( Object parent ) {
		this.parent = parent;
	}
    
    public KeyHandler() {
		this( null );
	}

    @Override
	public void keyPressed(KeyEvent k) {
		int keyCode = k.getKeyCode();
		System.out.println( "Your pressing keyboard key: " + keyCode );
        boolean actionRan = false;
        switch( keyCode ){
            case 27: // Esc
                break;
            case 32: // Spacebar
                
                break;
            case 122: // F11 (Fullscreen) //TODO fix this maybe
            case 127: // Delete
            case 8: // Backspace
            case 10: // Enter
            case 16: // Shift
            case 77: // M
            case 80: // P
            case 48: // 0
            case 49: // 1
            case 50: // 2
            case 51: // 3
            case 52: // 4
            case 53: // 5
            case 54: // 6
            case 55: // 7
            case 56: // 8
            case 57: // 9
            case 96: // Number pad 0
            case 97: // Number pad 1
            case 98: // Number pad 2
            case 99: // Number pad 3
            case 100: // Number pad 4
            case 101: // Number pad 5
            case 102: // Number pad 6
            case 103: // Number pad 7
            case 104: // Number pad 8
            case 105: // Number pad 9
                break;
            case 68: // D
                break;
        }
        if ( !actionRan ) {
            System.out.println( "Unhandled key event" );
        }
        /*
		// Assign keys to their classes	
		if ( keyCode == 122 ) {
			this.keyTyped.keyF11();        // F11 (Fullscreen)
		}
        */
	}

    @Override
	public void keyReleased( KeyEvent k ) {
		// Unused
	}

    @Override
	public void keyTyped( KeyEvent k ) {
		// Unused
	}
}