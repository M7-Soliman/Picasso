/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Exponentiate;
import picasso.parser.tokens.Token;

/**
 * @author colinwhiting
 *
 */
public class ExponentiateAnalyzer implements SemanticAnalyzerInterface {

	ExpressionTreeNode expressionTreeNodeLeft;
	ExpressionTreeNode expressionTreeNodeRight;
	String name;
	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {	
		tokens.pop();
			
		ExpressionTreeNode expressionTreeNodeRight = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode expressionTreeNodeLeft = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
			
		return new Exponentiate(expressionTreeNodeLeft, expressionTreeNodeRight);
		}
}
