
package towerdefenseproject;

import com.michael.api.IO.IO;
import com.michael.api.security.AES;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;

/**
 * Start of a MySQL API
 *
 * @author Chris Keers
 */
public class MysqlCon {

	private static Connection con = null;
	private static Statement state = null;
	private static ResultSet result = null;
	private static PreparedStatement prep = null;
	private static Properties props = new Properties();
	private static DatabaseMetaData meta = null;
	private static String url;
	private static String username;
	private static String password;
	private static boolean conStatus = false;

	/**
	 * Returns boolean value of the MySQL connection
	 */
	public static boolean connectionStatus() {
		return conStatus;
	}

	/**
	 * Looks for database settings stored in a file and creates a MySQL connection
	 */
	public static void dbOpen() {
		if ( con == null ) {
			try {
				// Settings File
				//todo this needs to be in source path or wont work on other machines
				props.load( MysqlCon.class.getClassLoader().getResourceAsStream( "database.properties" ) );
				boolean liveDeploy = props.getProperty( "live" ).equals( "true" ) ? true : false;
				if ( liveDeploy ) {
					url = props.getProperty( "db.live.url" );
					username = props.getProperty( "db.live.username" );
					try {
						password = AES.decrypt( props.getProperty( "db.live.password" ) );
					} catch ( Exception e ){
						password = props.getProperty( "db.live.password" );
					}
				} else{
					url = props.getProperty( "db.url" );
					username = props.getProperty( "db.username" );
					password = props.getProperty( "db.password" );
				}
				// Connection Attempt
				con = DriverManager.getConnection( url, username, password );
				state = con.createStatement();
				result = state.executeQuery( "SELECT VERSION()" );
				if ( result.next() ) {
					conStatus = true;
					// Log This later on just for the heck of it
					System.out.println( "MySQL Notice: Connection established. Version: " + result.getString( 1 ) );
				}
			} catch ( SQLException ex ) {
				System.out.println( "MySQL Notice: [Severe] " + ex );
			} catch ( FileNotFoundException ex ) {
				System.out.println( "MySQL Notice: [Severe] " + ex );
			} catch ( IOException ex ) {
				System.out.println( "MySQL Notice: [Severe] " + ex );
			}
		} else {
			System.out.println( "MySQL Notice: The connection is already open, call to open new database connection ignored." );
		}
	}

	/**
	 * Will properly close down the MySQL connection
	 */
	public static void dbClose() {
		try {
			if ( result != null ) {
				result.close();
				result = null;
			}
			if ( state != null ) {
				state.close();
				state = null;
			}
			if ( con != null ) {
				con.close();
				con = null;
				conStatus = false;
				System.out.println( "MySQL Connection closed." );
			}
		} catch ( SQLException ex ) {
			System.out.println( "MySQL Error: There was trouble closing the database connection." );
		}
	}

	/**
	 * Creates a prepared MySQL statement
	 * <p/>
	 * To create a proper prepared statement your query should use ? in
	 * place of actual values. You will then need to use the appropriate
	 * .set on the return value prep to change the question marks to their
	 * actual values before executing the query.
	 * <p/>
	 * If you do not plan on using .set on the returned value and instead
	 * want to immediately execute it, you may send a normal query.
	 *
	 * @param query A prepared SQL query with ? in place of actual values. Normal queries are still accepted though.
	 * @return
	 */
	public static PreparedStatement query( String query ) {
		if ( conStatus == true ) {
			try {
				prep = con.prepareStatement( query );
				return prep;
			} catch ( SQLException ex ) {
				System.out.println( "MySQL Error: [Severe] " + ex );
			}
		} else {
			return null;
			// LOG ERROR, not connected
		}
		return null;
	}

	/**
	 * Count the amount of expected rows returned from a MySQL query
	 * <p/>
	 * This function should only be called before you run a query where you will need to know
	 * the specific amount of rows being returned or to check if simple
	 * information exists in the database already, such as a username.
	 * <p/>
	 * This function is a platform and environment independent solution but can be expensive on large queries.
	 *
	 * @param table The table to look at in the database. If this is the only parameter it will count all rows in the table.
	 * @param par   This string is used as the SQL's WHERE clause. Format is: "color = '"+colorAnswer+"' AND age = '"+userAge+"'"
	 * @return
	 */
	public static int numRows( String table, String par ) {
		if ( conStatus == true ) {
			try {
				if ( par.length() <= 0 ) {
					prep = con.prepareStatement( "SELECT COUNT(*) FROM " + table );
				} else {
					prep = con.prepareStatement( "SELECT COUNT(*) FROM " + table + " WHERE " + par );
				}
				result = prep.executeQuery();
				result.first();
				return result.getInt( 1 );
			} catch ( SQLException ex ) {
				System.out.println( "MySQL Error: [Severe] " + ex );
			}
		} else {
			System.out.println( "MySQL Error: Can not count rows, no database connection." );
			return 0;
		}
		return 0;
	}

	/**
	 * Check if a tables exists
	 *
	 * @param table name of the table to check
	 * @return boolean
	 */
	public static boolean tableExists( String table ) {
		if ( conStatus == true ) {
			try {
				meta = con.getMetaData();
				result = meta.getTables( null, null, table, null );
				if ( result.next() ) {
					result.close();
					return true;
				} else {
					result.close();
					return false;
				}
			} catch ( SQLException ex ) {
				System.out.println( "MySQL Error: [Severe] Can not access connection meta data. " + ex );
			}

		}
		return false;
	}

	/**
	 * Create a table
	 *
	 * @param table table name
	 * @param query string of table create query
	 * @param check boolean to check if table exists first
	 * @return true if created
	 */
	public static boolean createTable( String table, String query, boolean check ) {
		if ( conStatus == true ) {
			if ( check == true ) {
				if ( tableExists( table ) ) {
					System.out.println( "MySQL Notice: Table not created, table already exists" );
					return false;
				}
			}
			try {
				state = con.createStatement();
				state.executeUpdate( query );
				state.executeUpdate( "FLUSH TABLES" );
				return true;
			} catch ( SQLException ex ) {
				System.out.println( "MySQL Error: [Severe] " + ex );
				return false;
			}
		}
		return false;
	}

	/**
	 * creates tables from file
	 * @param table table name
	 * @return if successfully made table
	 */
	public static boolean createTableFromFile( String table ){
		try {
//			FileInputStream inFile = new FileInputStream( MysqlCon.class.getClassLoader().getResourceAsStream( "/createTable.props" ) );
			Properties properties = new Properties();
			properties.load( MysqlCon.class.getClassLoader().getResourceAsStream( "createTable.props" ) );
			String query =  properties.getProperty( table, "" );
			return createTable( table, query, true );
		} catch ( IOException e ){
			IO.println( "IO Error: Failed to read file");
			return false;
		}
	}

	/**
	 * Delete a table and its content from the database
	 *
	 * @param table
	 * @return
	 */
	public static boolean dropTable( String table ) {
		if ( conStatus == true ) {
			// TODO FINISH
		}
		return false;
	}

	/**
	 * Create a 20 character unique id string
	 * <p/>
	 * ID is created from a timestamp and random string of numbers. This system is especially
	 * useful in a EVA environment for primary unique keys.
	 *
	 * @return
	 */
	public static String createId() {
		String id = "";
		DateFormat dateFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
		java.util.Date day = new java.util.Date();
		id = dateFormat.format( day ) + ":" + randomNum( 10000, 99999 );
		return id;
	}

	/**
	 * Generate a random number including and between a minimum and maximum parameter
	 *
	 * @param min int used for the low end
	 * @param max int used for the high end
	 * @return random int pulled from specified range
	 */
	public static int randomNum( int min, int max ) {
		Random rand = new Random();
		int num = rand.nextInt( ( max - min ) + 1 ) + min;
		return num;
	}
}