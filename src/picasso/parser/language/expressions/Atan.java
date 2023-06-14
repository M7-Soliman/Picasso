package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * 
 * expresses the arc tangent function as an image
 */

public class Atan extends UnaryFunction {
	
	/**
	 *
	 * @param param
	 */
	public Atan(ExpressionTreeNode param) {
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
		double red = Math.atan(result.getRed());
		double green = Math.atan(result.getGreen());
		double blue = Math.atan(result.getBlue());

		return new RGBColor(red, green, blue);
	}
	
	@Override
    public String toString() {
        return "atan(" + this.param + ")";
    }

}