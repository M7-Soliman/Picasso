package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * @author Mohamed Elhussiny
 * expresses given luminance as a given color
 */

public class YCrCbToRGB extends UnaryFunction {
	
	/**
	 *
	 * @param 
	 */
	public YCrCbToRGB(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * @param c - an inputed color
	 * @return the color of pixel
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor c = param.evaluate(x, y);
		double red = c.getRed() + c.getBlue() * 1.4022;
		double green = c.getRed() + c.getGreen() * -0.3456 + c.getBlue() * -0.7145;
		double blue = c.getRed() + c.getGreen() * 1.7710;
		return new RGBColor(red, green, blue);
	}
}