/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;

/**
 * @author colinwhiting
 *
 */
public class BinaryOperatorAnalyzer implements SemanticAnalyzerInterface {
	
	ExpressionTreeNode expressionTreeNodeRight;
	ExpressionTreeNode expressionTreeNodeLeft;

	/**
	 * 
	 */
	public BinaryOperatorAnalyzer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		Token operator = tokens.pop(); // Remove the token
		// the parameters are the remaining tokens on the stack.
		// But, they need to be processed
		
		expressionTreeNodeRight = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		expressionTreeNodeLeft = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return null;//operator.getClass().getConstructor(expressionTreeNodeLeft, expressionTreeNodeRight);
	}

}
