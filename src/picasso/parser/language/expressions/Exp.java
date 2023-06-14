package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * 
 * expresses the exponential function as an image
 */

public class Exp extends UnaryFunction {
	
	/**
	 *
	 * @param 
	 */
	public Exp(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * @param x - x-coordinate of pixel
	 * @param y - y-coordinate of pixel
	 * @return the color of pixel
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		
		
		RGBColor result = param.evaluate(x, y);
		double red = Math.exp(result.getRed());
		double green = Math.exp(result.getGreen());
		double blue = Math.exp(result.getBlue());
		
		if (red > 1) {
			red = 1;
		}
		if (green > 1) {
			green = 1;
		}
		if (blue > 1) {
			blue = 1;
		}

		return new RGBColor(red, green, blue);
	}
	
	@Override
    public String toString() {
        return "exp(" + this.param + ")";
    }

}