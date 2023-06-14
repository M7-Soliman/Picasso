package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Random;
import picasso.parser.tokens.Token;

public class RandomAnalyzer implements SemanticAnalyzerInterface {

	public RandomAnalyzer() {
		
	}
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the Random token
		// the parameter is the next token on the stack.
		// But, it needs to be processed
		return new Random();
		}
	}
