/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Sin;
import picasso.parser.tokens.Token;

/**
 * @author drewlarsen
 *
 */
public class SinAnalyzer extends UnaryFunctionAnalyzer {


	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the cosine token
		// the parameter is the next token on the stack.
		// But, it needs to be processed
		return new Sin(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}

}
