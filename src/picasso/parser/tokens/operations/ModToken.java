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
public class ModToken extends CharToken implements OperationInterface{

	/**
	 * 
	 */
	public ModToken() {
		super(CharConstants.MOD);
	}
	public int getOrder() {
		return MULTIPLY_OR_DIVIDE;
	}

}
