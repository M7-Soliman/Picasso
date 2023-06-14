package picasso.parser.tokens.operations;

/**
 * A marker interface
 * 
 */
public interface OperationInterface {
	public static final int GROUPING = 1; // parentheses
	public static final int ADD_OR_SUBTRACT = 3;
	public static final int MULTIPLY_OR_DIVIDE = 4;
	public static final int EXPONENTIAL = 5;
	public static final int EQUALS = 1;
	public static final int NEGATE = 2;

	
	
	public abstract int getOrder();
}
