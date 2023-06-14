package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Abs;
import picasso.parser.tokens.Token;

/**
 * 
 * Parses the absolute value token
 *
 */
public class AbsAnalyzer extends UnaryFunctionAnalyzer {


	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); 
		
		return new Abs(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}

}