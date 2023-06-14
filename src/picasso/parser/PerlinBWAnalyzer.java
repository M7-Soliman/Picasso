/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.PerlinBW;
import picasso.parser.tokens.Token;

/**
 * @author drewlarsen
 *
 */
public class PerlinBWAnalyzer implements SemanticAnalyzerInterface {



	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		
		ExpressionTreeNode expressionTreeNodeRight = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode expressionTreeNodeLeft = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return new PerlinBW(expressionTreeNodeLeft, expressionTreeNodeRight);
	}

}
