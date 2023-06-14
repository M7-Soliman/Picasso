/**
 * 
 */
package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * @author colinwhiting
 *
 */
public class ExponentiateToken extends CharToken implements OperationInterface{

	/**
	 * 
	 */

	public ExponentiateToken() {
		super(CharConstants.CARET);
	}
		
	public int getOrder() {
		return EXPONENTIAL;
	}
	
}
