package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Tan;
import picasso.parser.tokens.Token;

/**
 * @author Yoseph Wolde
 * 
 * Parses the tangent token
 *
 */
public class TanAnalyzer extends UnaryFunctionAnalyzer {


	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the tangent token
		// the parameter is the next token on the stack.
		// But, it needs to be processed
		return new Tan(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}

}
