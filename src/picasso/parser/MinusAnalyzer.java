package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Minus;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the Minus or "subtraction function".
 * 
 * @author Mohamed Elhussiny
 * 
 */
public class MinusAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the Minus token
		// the parameters are the remaining tokens on the stack.
		// But, they need to be processed
		// TODO: Need to finish
		
				
		ExpressionTreeNode expressionTreeNodeRight = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode expressionTreeNodeLeft = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return new Minus(expressionTreeNodeLeft, expressionTreeNodeRight);
	}

}
