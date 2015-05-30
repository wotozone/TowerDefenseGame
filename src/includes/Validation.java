package includes;

import com.michael.api.IO.IO;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Risher
 * Date: 4/15/15
 * Time: 3:01 PM
 */
public class Validation {
	
	public static boolean validateInput( String text, String name ){
		return validateInput( text, name, null, false );
	}

	public static boolean validateInput( String text, String name, String sibling ){
		return validateInput( text, name, sibling, true );
	}

	public static boolean validateInput( String text, String name, String sibling, boolean hasSibling ){
		if ( hasSibling ) {
			if ( !text.equals( sibling ) ) {
				return false;
			}
		}
		if ( name.startsWith( "conf-" ) ) {
			name = name.split( "conf-" )[1];
		}

		if ( name.equals( "username" ) ) {
			return RegEx.username( text );
		}
		else if ( name.equals( "email" ) ) {
			return RegEx.email( text );
		}
		else if ( name.equals( "password" ) ) {
			return RegEx.password( text );
		}

		IO.printlnErr( "uncaught validation" );
		return false;
	}
}