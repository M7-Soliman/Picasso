/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.ImageToken;


/**
 * @author drewlarsen
 *
 */
public class ImageWrapAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the ImageWrap token
		
		ExpressionTreeNode param_right = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode param_left = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		if (tokens.peek() instanceof ImageToken) {
			ImageAnalyzer analyzer = new ImageAnalyzer();
			ExpressionTreeNode newimage = analyzer.generateExpressionTree(tokens);
			return new ImageWrap((Image) newimage, param_left, param_right);
		}
		else {
			throw new ParseException("No Image Token Found");
		}

	}

}
