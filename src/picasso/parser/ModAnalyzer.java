/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Mod;
import picasso.parser.tokens.Token;

/**
 * @author colinwhiting
 *
 */
public class ModAnalyzer implements SemanticAnalyzerInterface {

	/**
	 * 
	 */
	public ModAnalyzer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		
		ExpressionTreeNode expressionTreeNodeRight = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode expressionTreeNodeLeft = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return new Mod(expressionTreeNodeLeft, expressionTreeNodeRight);
	}

}
