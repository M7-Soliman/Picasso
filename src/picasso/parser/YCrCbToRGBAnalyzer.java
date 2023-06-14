package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.RgbToYCrCb;
import picasso.parser.language.expressions.YCrCbToRGB;
import picasso.parser.tokens.Token;

/**
 * 
 * Parses the arc YCrCbToRGBA token
 * @author Mohamed Elhussiny
 */
public class YCrCbToRGBAnalyzer extends UnaryFunctionAnalyzer {


	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); 
		return new YCrCbToRGB(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}

}