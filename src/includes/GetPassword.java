
package includes;

/**
 * @author Chris
 */
public class GetPassword {
	
	public static String getPassword(char[] pass){
		int length = pass.length;
		String returnPass = "";
		for (int x=0;x<length;x++){
			returnPass += pass[x];
			pass[x] = 0;
		}
		pass = null;
		length = 0;
		return returnPass;
	}
}
