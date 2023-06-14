package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.ImageToken;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.Image;

public class ImageAnalyzer implements SemanticAnalyzerInterface {

	public ImageAnalyzer() {
	}

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		ImageToken imageToken = (ImageToken) tokens.pop();
		String imageName = imageToken.getName();
		return new Image(imageName);
	}

}
