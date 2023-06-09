/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author colinwhiting
 *
 */
public class Exponentiate extends BinaryOperation {

	/**
	 * @param expressionTreeNode
	 */
	public Exponentiate(ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		super(param_left, param_right);
	}
	
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result_left = param_left.evaluate(x, y);
		RGBColor result_right = param_right.evaluate(x, y);
		
		double red = Math.pow(result_left.getRed(),result_right.getRed());
		double green = Math.pow(result_left.getGreen(),result_right.getGreen());
		double blue = Math.pow(result_left.getBlue(),result_right.getBlue());

		return new RGBColor(red, green, blue);	
	}
	
	@Override
    public String toString() {
        return this.param_left + " ^ " + this.param_right;
    }
}
