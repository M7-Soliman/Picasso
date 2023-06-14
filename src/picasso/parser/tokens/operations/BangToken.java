package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the Bang sign token
 * 
 */
public class BangToken extends CharToken implements OperationInterface {
	public BangToken() {
		super(CharConstants.BANG);
	}
	
	public int getOrder() {
		return NEGATE;
	}
}
