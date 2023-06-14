/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Bang;
import picasso.parser.language.expressions.Divide;
import picasso.parser.tokens.Token;

/**
 * @author Mohamed Elhussiny
 *
 */
public class BangAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); 
		// Remove the Bang token
		// the parameters are the remaining tokens on the stack.
		// But, they need to be processed
		
				
		ExpressionTreeNode expressionTreeNode = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return new Bang(expressionTreeNode);
	}

}
