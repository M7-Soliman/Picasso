package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

import picasso.parser.language.expressions.Assignment;

/**
 * Represents a variable
 * 
 * @author Sara Sprenkle
 *
 */
@SuppressWarnings("unused")
public class Variable extends ExpressionTreeNode {

	private String var;

	public Variable(String var) {
		this.var = var;
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		ExpressionTreeNode exp = Assignment.Assignment_Map.get(var);
		RGBColor result = exp.evaluate(x, y);
		return result;
	}
	
	
}
