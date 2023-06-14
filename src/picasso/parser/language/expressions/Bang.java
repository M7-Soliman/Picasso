package picasso.parser.language.expressions;

import picasso.parser.ExpressionTreeGenerator;

import picasso.parser.language.ExpressionTreeNode;

/**
 * 
 * Represents the Negate Operator in the Picasso language.
 *
 */
@SuppressWarnings("unused")
public class Bang extends UnaryOperation {

	/**
	 * @param expressionTreeNode
	 */
	public Bang(ExpressionTreeNode param_right) {
		super(param_right);
	}
	
	

	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param_right.evaluate(x, y);
		
		double red = - result.getRed();
		double green = - result.getGreen();
		double blue = - result.getBlue();

		return new RGBColor(red, green, blue);	
	}
	
	@Override
    public String toString() {
        return  " ! " + this.param_right;
    }
}