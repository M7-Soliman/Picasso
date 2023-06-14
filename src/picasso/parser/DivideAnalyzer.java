/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Divide;
import picasso.parser.tokens.Token;

/**
 * @author colinwhiting
 * Parses the divide token
 */
public class DivideAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the Divide token
		// the parameters are the remaining tokens on the stack.
		// But, they need to be processed
		// TODO: Need to finish
		
				
		ExpressionTreeNode expressionTreeNodeRight = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode expressionTreeNodeLeft = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return new Divide(expressionTreeNodeLeft, expressionTreeNodeRight);
	}

}
