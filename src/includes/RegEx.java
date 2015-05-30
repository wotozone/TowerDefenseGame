
package includes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author minjikim
 */
public class RegEx {

	public static String[] users;
	public static int totalUsersNum = 0;

	/**
	 * Check if entered date is valid
	 * @deprecated 
	 * @param s String of date
	 * @return true id the date is valid
	 */
	public static boolean checkDate( String s ) {
		Pattern p = Pattern.compile( "([0][1-9]|[1][0-2])-([0][1-9]|[1][0-9]|[2][0-9]|[3][0-2])-([1-2][0-9][0-9][0-9])" );
		Matcher m = p.matcher( s );
		boolean b = m.matches();
		if ( b ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check if entered ID is valid
	 * @deprecated 
	 * @param s	string of the id
	 * @return true if id is valid
	 */
	public static boolean checkID( String s ) {
		Pattern p = Pattern.compile( "^[a-zA-Z0-9]{4,10}$" );
		Matcher m = p.matcher( s );
		if ( m.matches() == true ) return true;
		return false;
	}

	/**
	 * Check if email is valid
	 * @param s string to check
	 * @return true if valid
	 */
	public static boolean email( String s ){
		Pattern p = Pattern.compile( "[a-zA-Z0-9]+(?:(\\.|_)[A-Za-z0-9!#$%&'*+/=?^`{|}~-]+)*@(?!([a-zA-Z0-9]*\\.[a-zA-Z0-9]*\\.[a-zA-Z0-9]*\\.))(?:[A-Za-z0-9](?:[a-zA-Z0-9-]*[A-Za-z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?" );
		return ( ( p.matcher( s ).matches() ) ? ( true ) : ( false ) );
	}

	/**
	 * Check username for alphanumerical input 3 - 50 characters in length
	 * @param s string to check
	 * @return true if valid
	 */
	public static boolean username( String s ){
		Pattern p = Pattern.compile( "^(?:[A-Za-z0-9]{3,50})$" );
		return ( ( p.matcher( s ).matches() ) ? ( true ) : ( false ) );
	}

	/**
	 * Check if password is valid
	 * contain at least 6 characters in length
	 * contain at least 1 upper or 1 lower case letter or 1 number
	 * can contain at least 1 special characters "!#$%&? "
	 * @param s string to check
	 * @return true if valid
	 */
	public static boolean password( String s ) {
		Pattern p = Pattern.compile( "^.*(?=.{6,})(?=.*\\w)(?=.*[!&$%&? \"])?.*$" );
		return ( ( p.matcher( s ).matches() ) ? ( true ) : ( false ) );
	}
}
