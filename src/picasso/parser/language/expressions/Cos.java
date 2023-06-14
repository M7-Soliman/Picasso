package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the cosine function in the Picasso language.
 * 
 * @author Colin Whiting
 * 
 */

public class Cos extends UnaryFunction {
	
	/**
	 * Create a Cosine expression that takes as a parameter the given expression
	 * 
	 * @param param the expression used in cosine function
	 */
	public Cos(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the cosine of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the floor of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.cos(result.getRed());
		double green = Math.cos(result.getGreen());
		double blue = Math.cos(result.getBlue());

		return new RGBColor(red, green, blue);
	}
	
	@Override
    public String toString() {
        return "cos(" + this.param + ")";
    }

}


