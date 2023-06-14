/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Assignment;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.IdentifierToken;

/**
 * @author colinwhiting
 *
 */
public class AssignmentAnalyzer implements SemanticAnalyzerInterface {
	
	ExpressionTreeNode expressionTreeNodeLeft;
	ExpressionTreeNode expressionTreeNodeRight;
	String name;

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the Assignment token
		//Remove assignment token
		// the parameters are the remaining tokens on the stack.
		// But, they need to be processed
		// TODO: Need to finish
				
		expressionTreeNodeRight = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		if (tokens.peek() instanceof IdentifierToken) {
			name = ((IdentifierToken) tokens.peek()).getName();
			expressionTreeNodeLeft = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		}
		
		
		return new Assignment(name, expressionTreeNodeRight);
	}

}
