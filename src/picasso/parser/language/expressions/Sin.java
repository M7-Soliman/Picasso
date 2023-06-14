/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author drewlarsen
 *
 */
public class Sin extends UnaryFunction {

	/**
	 * @param param
	 */
	public Sin(ExpressionTreeNode param) {
		super(param);
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.sin(result.getRed());
		double green =Math.sin(result.getGreen());
		double blue = Math.sin(result.getBlue());

		return new RGBColor(red, green, blue);
	}
	
	@Override
    public String toString() {
        return "sin(" + this.param + ")";
    }

}
