/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author colinwhiting
 *
 */
public class Mod extends BinaryOperation {

	/**
	 * @param param_left
	 * @param param_right
	 * Represents the mod operator in the Picasso language.
	 */
	public Mod(ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		super(param_left, param_right);
		// TODO Auto-generated constructor stub
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result_left = param_left.evaluate(x, y);
		RGBColor result_right = param_right.evaluate(x, y);
		
		double red = result_left.getRed() % result_right.getRed();
		double green = result_left.getGreen() % result_right.getGreen();
		double blue = result_left.getBlue() % result_right.getBlue();

		return new RGBColor(red, green, blue);
	}
	
	@Override
    public String toString() {
        return this.param_left + " % " + this.param_right;
    }

}
