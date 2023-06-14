package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.RgbToYCrCb;
import picasso.parser.tokens.Token;

/**
 * 
 * Parses the RgbToYCrCb token
 *
 */
public class RgbToYCrCbAnalyzer extends UnaryFunctionAnalyzer {


	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); 
		return new RgbToYCrCb(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}

}