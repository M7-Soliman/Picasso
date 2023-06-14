/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author drewlarsen
 *
 */
public class Log extends UnaryFunction {

	/**
	 * @param param
	 * Represents the log function in the Picasso language.
	 */
	public Log(ExpressionTreeNode param) {
		super(param);
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.log(Math.abs(result.getRed()));
		double green = Math.log(Math.abs(result.getGreen()));
		double blue = Math.log(Math.abs(result.getBlue()));

		if (red > 1) {
			red = 1;
		}
		if (green > 1) {
			green = 1;
		}
		if (blue > 1) {
			blue = 1;
		}

		if (red < -1) {
			red = -1;
		}
		if (green < -1) {
			green = -1;
		}
		if (blue < -1) {
			blue = -1;
		}
		
		return new RGBColor(red, green, blue);
	}
	
	@Override
    public String toString() {
        return "log(" + this.param + ")";
    }

}
