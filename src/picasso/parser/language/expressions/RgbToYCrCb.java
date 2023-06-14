package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * 
 * expresses a given color as luminance 
 */

public class RgbToYCrCb extends UnaryFunction {
	
	/**
	 *
	 * @param 
	 */
	public RgbToYCrCb(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * @param c - an inputed color
	 * @return the color of pixel
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor c = param.evaluate(x, y);
		double red = c.getRed() * 0.2989 + c.getGreen() * 0.5866 + c.getBlue() * 0.1145;
		double green = c.getRed() * -0.1687 + c.getGreen() * -0.3312 + c.getBlue() * 0.5;
		double blue = c.getRed() * 0.5000 + c.getGreen() * -0.4183 + c.getBlue() * -0.0816;
		return new RGBColor(red, green, blue);
	}
}